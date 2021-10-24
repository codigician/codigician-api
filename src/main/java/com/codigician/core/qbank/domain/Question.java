package com.codigician.core.qbank.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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

    public static Builder create(Author author, String title, String prompt) {
        return new Builder(author, title, prompt);
    }

    public void update(Question other) {
        this.title = other.title;
        this.prompt = other.prompt;
        this.editorial = other.editorial;
        this.hints = new ArrayList<>(other.hints);

        this.updatedAt = LocalDateTime.now();
    }

    public void setHints(List<String> hints) {
        this.hints = hints;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public boolean isVerified() {
        return verified;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPrompt() {
        return prompt;
    }

    public String getEditorial() {
        return editorial;
    }

    public List<String> getHints() {
        return hints;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
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


    public static class Builder {
        private final Question question;

        public Builder(Author author, String title, String prompt) {
            question = new Question(author, title, prompt);
        }

        public Builder editorial(String editorial) {
            this.question.editorial = editorial;
            return this;
        }

        public Builder hints(String... hints) {
            this.question.hints = Arrays.asList(hints);
            return this;
        }

        public Question build() {
            return question;
        }
    }


}
