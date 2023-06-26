package com.lambda.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class Response {
    private String body;
    private String statusCode;
}
