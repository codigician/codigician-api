package com.codigician.core.qbank.model;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder(builderMethodName = "create")
public class Question {
    private final String prompt;
    private final String editorial;
    private final Author author;
    private List<String> hints;

    private boolean verified;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

//    public Question(Author author, String prompt, String editorial) {
//        this.author = author;
//        this.prompt = prompt;
//        this.editorial = editorial;
//        this.hints = new ArrayList<>();
//
//        verified = false;
//
//        createdAt = LocalDateTime.now();
//        updatedAt = LocalDateTime.now();
//    }

    public boolean isVerified() {
        return verified;
    }
}
