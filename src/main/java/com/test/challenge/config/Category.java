package com.test.challenge.config;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Category {
    private String name;
    private List<String> subscribers;

}
