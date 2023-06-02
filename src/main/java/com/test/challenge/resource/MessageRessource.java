package com.test.challenge.resource;

import com.test.challenge.model.MessageRequest;
import com.test.challenge.service.CategoryService;
import com.test.challenge.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class MessageRessource {

    private CategoryService categoryService;
    private MessageService messageService;

    public MessageRessource(CategoryService categoryService, MessageService messageService) {
        this.categoryService = categoryService;
        this.messageService = messageService;
    }

    @PostMapping ("/api/v1/messages")
    public ResponseEntity publishMessage(@Valid @RequestBody MessageRequest request) {
        if (!categoryService.isValidCategory(request.getCategory())) {
            return new ResponseEntity("Not a valid category", HttpStatus.BAD_REQUEST);
        }
        messageService.publishMessage(request);
        return new ResponseEntity(HttpStatus.OK);

    }

}
