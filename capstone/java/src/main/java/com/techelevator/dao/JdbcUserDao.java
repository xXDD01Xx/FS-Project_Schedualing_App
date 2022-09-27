package com.techelevator.dao;

import java.util.*;

import com.techelevator.model.UserNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.techelevator.model.User;

@Component
public class JdbcUserDao implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int findIdByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");

        int userId;
        try {
            userId = jdbcTemplate.queryForObject("select user_id from users where username = ?", int.class, username);
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("User " + username + " was not found.");
        }

        return userId;
    }

    @Override
    public User getUserById(int userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()) {
            return mapRowToUser(results);
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT u.user_id, u.username, u.password_hash, u.role, us.user_status_desc " +
                "FROM users u " +
                "JOIN user_status us ON u.user_status_id = us.user_status_id;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }

        return users;
    }

    @Override
    public User findByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");

        for (User user : this.findAll()) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }

    @Override
    public boolean create(String username, String password, String role) {
        String insertUserSql = "insert into users (username,password_hash,role) values (?,?,?)";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        String ssRole = role.toUpperCase().startsWith("ROLE_") ? role.toUpperCase() : "ROLE_" + role.toUpperCase();

        return jdbcTemplate.update(insertUserSql, username, password_hash, ssRole) == 1;
    }

    @Override
    public boolean changeUserPassword(String username, String password) {
        String updateUserSql = "UPDATE users SET password_hash = ? WHERE username = ?;";
        String password_hash = new BCryptPasswordEncoder().encode(password);

        return jdbcTemplate.update(updateUserSql, password_hash, username) == 1;
    }

    @Override
    public boolean changeUserStatus(String username, String status) {
        String updateUserSql = "UPDATE users SET user_status_id = " +
                "(SELECT MAX(user_status.user_status_id) " +
                "FROM user_status where " +
                "user_status.user_status_desc = ?) " +
                "WHERE users.username = ?;";

        return jdbcTemplate.update(updateUserSql, status, username) == 1;
    }

    public HashSet<String> getUserStatusValues() {
        String sql = "select user_status.user_status_desc from user_status;";
        SqlRowSet statusRowSet = jdbcTemplate.queryForRowSet(sql);
        HashSet<String> userStatusSet = new HashSet<>();
        while (statusRowSet.next()) {
            userStatusSet.add(statusRowSet.getString(1));
        }
        return userStatusSet;
    }



    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(Objects.requireNonNull(rs.getString("role")));
        user.setActivated(true);
        user.setStatus(rs.getString("user_status_desc"));
        return user;
    }
}
