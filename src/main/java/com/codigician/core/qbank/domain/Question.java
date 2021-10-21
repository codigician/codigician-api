package com.codigician.core.qbank.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question extends BaseEntity {
    private String title;
    private String prompt;
    private String editorial;
    private Author author;
    private List<String> hints;

    private boolean verified;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    Question() {
    }

    Question(Author author, String title, String prompt) {
        super();
        this.author = author;
        this.title = title;
        this.prompt = prompt;
        this.hints = new ArrayList<>();

        this.verified = false;

        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void update(Question other) {
        this.title = other.title;
        this.prompt = other.prompt;

        this.updatedAt = LocalDateTime.now();
    }

    public void setHints(List<String> hints) {
        this.hints = hints;
    }

    public boolean isVerified() {
        return verified;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", prompt='" + prompt + '\'' +
                ", editorial='" + editorial + '\'' +
                ", author=" + author +
                ", hints=" + hints +
                ", verified=" + verified +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
