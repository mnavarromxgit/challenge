package com.lambda;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambda.model.Body;
import com.lambda.model.Response;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.regex.Pattern;

public class LambdaApiGateway implements RequestHandler<APIGatewayProxyRequestEvent, Response> {

    private static final String OK = "200";
    private static final String BAD_REQUEST = "400";
    private static final String AT_LEAST_ONE_NUMBER_REGEX = ".*[0-9].*";
    private static final String SPECIAL_CHARS_REGEX = ".*[^A-Za-z0-9].*";

    public Response handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        if (!input.getHttpMethod().equals("POST")) {
            return generateResponse("Method not supported", "", BAD_REQUEST);
        }
        String str = input.getBody();
        if (Optional.ofNullable(str).orElse("").length() < 8) {
            String message = "Input should be at least 8 characters";
            return generateResponse(message, "", BAD_REQUEST);
        } else {
            if (!matchesPattern(str, AT_LEAST_ONE_NUMBER_REGEX)) {
                String message = "Input should contain at least a number";
                return generateResponse(message, "", BAD_REQUEST);
            } else if (!matchesPattern(str, SPECIAL_CHARS_REGEX)) {
                String message = "Input should contain at least a special character";
                return generateResponse(message, "", BAD_REQUEST);
            }
        }
        String hash = generateHash(str);
        return generateResponse("SUCCESS", hash, OK);
    }

    private boolean matchesPattern(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input).matches();
    }

    private Response generateResponse(String message, String result, String statusCode) {
        try {
            return new Response(new ObjectMapper().writeValueAsString(new Body(result, message)), statusCode);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    private String generateHash(String input) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] encodedhash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (byte h : encodedhash) {
                String hex = Integer.toHexString(0xff & h);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
