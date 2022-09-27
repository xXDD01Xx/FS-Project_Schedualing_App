package com.techelevator.validation;

import com.techelevator.dao.UserDao;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserValidations {
    private UserDao userDao;

    public UserValidations(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean validUserStatus(String status) {
        Set<String> validStatusList = userDao.getUserStatusValues();
        return validStatusList.contains(status);
    }
}
