package com.example.insuranceproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponse {
    private String message;
    private Object content;

    public MessageResponse(String message) {
        this.message = message;
    }

    public MessageResponse(String message, Object content) {
        this.message = message;
        this.content = content;
    }
}