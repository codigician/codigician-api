package com.codigician.core.qbank.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class AlgorithmQuestionTest {

    @Test
    void update() {
        AlgorithmQuestion algorithmQuestionToUpdate = AlgorithmQuestion.create(new Author(), "old", "oldPropmt").build();
        LocalDateTime oldUpdatedAt = algorithmQuestionToUpdate.getUpdatedAt();
        boolean oldIsVerified = algorithmQuestionToUpdate.isVerified();
        AlgorithmQuestion newAlgorithmQuestion = AlgorithmQuestion.create(new Author(), "newTitle", "newPrompt")
                .hints(Arrays.asList("hitn1", "hint2"))
                .editorial("newEditorial")
                .build();
        algorithmQuestionToUpdate.update(newAlgorithmQuestion);

        assertThat(algorithmQuestionToUpdate.getTitle()).isEqualTo(newAlgorithmQuestion.getTitle());
        assertThat(algorithmQuestionToUpdate.getPrompt()).isEqualTo(newAlgorithmQuestion.getPrompt());
        assertThat(algorithmQuestionToUpdate.getHints()).isEqualTo(newAlgorithmQuestion.getHints());
        assertThat(algorithmQuestionToUpdate.getEditorial()).isEqualTo(newAlgorithmQuestion.getEditorial());
        assertThat(algorithmQuestionToUpdate.isVerified()).isEqualTo(oldIsVerified);
        assertThat(algorithmQuestionToUpdate.getUpdatedAt()).isAfter(oldUpdatedAt);
    }
}