package com.test.challenge.service;

import com.test.challenge.config.AppConfig;
import com.test.challenge.config.Category;
import com.test.challenge.config.User;
import com.test.challenge.model.MessageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class MessageService {

    AppConfig appConfig;
    UserService userService;
    CategoryService categoryService;
    public MessageService(AppConfig appConfig, UserService userService, CategoryService categoryService) {
        this.appConfig = appConfig;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    public void publishMessage(MessageRequest request) {
        Optional<Category> optCategory = categoryService.getCategoryByName(request.getCategory());
        if (optCategory.isPresent()) {
            Category category = optCategory.get();
            category.getSubscribers().stream().forEach(
                    subscriber -> {
                            User user = userService.findUserById(subscriber);
                            user.getChannels().forEach( channel ->
                            {
                                log.info("Sending message through {} for category {}, userId:{}, userName:{}. Message: {}", channel,
                                        category.getName(), user.getId(), user.getName(),request.getMessage());
                            });
                    }
            );
        }
    }
}
