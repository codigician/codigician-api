package com.codigician.core.qbank.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AlgorithmQuestion {
    private final String id;
    private final Author author;
    private String title;
    private String prompt;
    private String editorial;
    private List<String> hints;

    private List<Expectation> expectations;

    private boolean verified;

    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private AlgorithmQuestion(Author author, String title, String prompt) {
        this.id = UUID.randomUUID().toString();
        this.author = author;
        this.title = title;
        this.prompt = prompt;
        this.hints = new ArrayList<>();

        this.expectations = new ArrayList<>();

        this.verified = false;

        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public static Builder create(Author author, String title, String prompt) {
        return new Builder(author, title, prompt);
    }

    public void update(AlgorithmQuestion other) {
        this.title = other.title;
        this.prompt = other.prompt;
        this.editorial = other.editorial;
        this.hints = new ArrayList<>(other.hints);

        this.updatedAt = LocalDateTime.now();
    }

    public void verify() {
        this.verified = true;
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

    public Author getAuthor() {
        return author;
    }

    public List<Expectation> getExpectations() {
        return expectations;
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
        private final AlgorithmQuestion algorithmQuestion;

        public Builder(Author author, String title, String prompt) {
            algorithmQuestion = new AlgorithmQuestion(author, title, prompt);
        }

        public Builder editorial(String editorial) {
            algorithmQuestion.editorial = editorial;
            return this;
        }

        public Builder hints(List<String> hints) {
            algorithmQuestion.hints = new ArrayList<>(hints);
            return this;
        }

        public Builder expectations(List<Expectation> expectations) {
            algorithmQuestion.expectations = expectations;
            return this;
        }

        public AlgorithmQuestion build() {
            return algorithmQuestion;
        }
    }


}
