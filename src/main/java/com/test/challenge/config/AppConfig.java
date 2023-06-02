package com.test.challenge.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties (prefix = "app-config", ignoreUnknownFields=false)
public class AppConfig {

    //@Value("categories")
    private List<Category> categories;
    private List<User> users;

}
