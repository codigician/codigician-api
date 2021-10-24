package com.codigician.core.qbank.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    @Test
    void update() {
        Question questionToUpdate = Question.create(new Author(), "old", "oldPropmt").build();
        LocalDateTime oldUpdatedAt = questionToUpdate.getUpdatedAt();
        boolean oldIsVerified = questionToUpdate.isVerified();
        Question newQuestion = Question.create(new Author(), "newTitle", "newPrompt")
                .hints(Arrays.asList("hitn1", "hint2"))
                .editorial("newEditorial")
                .build();
        questionToUpdate.update(newQuestion);

        assertThat(questionToUpdate.getTitle()).isEqualTo(newQuestion.getTitle());
        assertThat(questionToUpdate.getPrompt()).isEqualTo(newQuestion.getPrompt());
        assertThat(questionToUpdate.getHints()).isEqualTo(newQuestion.getHints());
        assertThat(questionToUpdate.getEditorial()).isEqualTo(newQuestion.getEditorial());
        assertThat(questionToUpdate.isVerified()).isEqualTo(oldIsVerified);
        assertThat(questionToUpdate.getUpdatedAt()).isAfter(oldUpdatedAt);
    }
}