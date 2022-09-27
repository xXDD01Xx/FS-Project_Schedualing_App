package com.techelevator.controller;

import javax.validation.Valid;

import com.techelevator.model.*;
import com.techelevator.validation.UserValidations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techelevator.dao.UserDao;
import com.techelevator.security.jwt.JWTFilter;
import com.techelevator.security.jwt.TokenProvider;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class AuthenticationController {
    private final static String STATUS_ACTIVE_USER = "Active";

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserValidations validate;
    private UserDao userDao;

    public AuthenticationController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserValidations validate, UserDao userDao) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.validate = validate;
        this.userDao = userDao;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginDTO loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, false);

        User user = userDao.findByUsername(loginDto.getUsername());

        if (user.getStatus().equals(STATUS_ACTIVE_USER)) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
            return new ResponseEntity<>(new LoginResponse(jwt, user), httpHeaders, HttpStatus.OK);
        } else {
            throw new UserNotActiveException();
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@Valid @RequestBody RegisterUserDTO newUser) {
        try {

            if (validate.validUserStatus(newUser.getStatus())) {
                User user = userDao.findByUsername(newUser.getUsername());
                throw new UserAlreadyExistsException();
            } else {
                throw new UserInvalidStatusException();
            }
        } catch (UsernameNotFoundException e) {
            userDao.create(newUser.getUsername(), newUser.getPassword(), newUser.getRole(), newUser.getStatus());
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping(value = "/changepassword")
    public void changePassword(@RequestBody LoginDTO newPassword, Principal principal) {
        if (principal.getName().equals(newPassword.getUsername())) {
            userDao.changeUserPassword(newPassword.getUsername(), newPassword.getPassword());
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/updateuserstatus")
    public void updateUserStatus(@RequestBody ChangeUserStatusDTO[] updateStatus) {
        for (ChangeUserStatusDTO status : updateStatus) {
            if (validate.validUserStatus(status.getStatus())) {
                userDao.changeUserStatus(status.getUsername(), status.getStatus());
            }
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/getallusers")
    public List<User> getAllUsers() {
        return userDao.findAll();
    }


    /**
     * Object to return as body in JWT Authentication.
     */
    static class LoginResponse {

        private String token;
        private User user;

        LoginResponse(String token, User user) {
            this.token = token;
            this.user = user;
        }

        @JsonProperty("token")
        String getToken() {
            return token;
        }

        void setToken(String token) {
            this.token = token;
        }

        @JsonProperty("user")
        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }
}

