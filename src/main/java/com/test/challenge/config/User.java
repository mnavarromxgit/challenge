package com.test.challenge.config;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {
    private String id;
    private String name;
    private String email;
    private String phone;
    private List<String> channels;
}
