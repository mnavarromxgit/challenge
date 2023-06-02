package com.test.challenge.service;

import com.test.challenge.config.AppConfig;
import com.test.challenge.config.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    AppConfig appConfig;

    @Autowired
    public CategoryService(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public Optional<Category> getCategoryByName(String categoryName) {
        return appConfig.getCategories().stream()
                .filter(c -> c.getName().equals(categoryName))
                .findFirst();
    }

    public boolean isValidCategory(String category) {
        return appConfig.getCategories().stream()
                .filter(c -> c.getName().equals(category))
                .findFirst().isPresent();
    }
}
