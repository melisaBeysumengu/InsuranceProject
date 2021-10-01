package com.example.insuranceproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponseDTO {
    private String message;
    private Object content;

    public MessageResponseDTO(String message) {
        this.message = message;
    }

    public MessageResponseDTO(String message, Object content) {
        this.message = message;
        this.content = content;
    }
}