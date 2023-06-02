package com.test.challenge.service;

import com.test.challenge.config.AppConfig;
import com.test.challenge.config.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService  {

    AppConfig appConfig;
    public UserService(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public User findUserById(String userId) {
        return appConfig.getUsers().stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst().get();
    }

}
