package com.codigician.core.qbank.domain;

import com.codigician.core.qbank.infra.repo.InMemoryQuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AlgorithmQuestionFacadeTest {
    private static final Author AUTHOR = new Author();
    private QuestionFacade questionFacade;

    @BeforeEach
    void setup() {
        questionFacade = new QuestionFacade(new InMemoryQuestionRepository());
    }

    @Test
    void createQuestion() {
        QuestionFacade.QuestionDto questionDto = new QuestionFacade.QuestionDto("title",
                "prompt",
                "editorial",
                List.of("hints1"));

        AlgorithmQuestion algorithmQuestion = questionFacade.createQuestion(AUTHOR, questionDto);

        assertThat(algorithmQuestion.getTitle()).isEqualTo(questionDto.title());
        assertThat(algorithmQuestion.getPrompt()).isEqualTo(questionDto.prompt());
        assertThat(algorithmQuestion.getEditorial()).isEqualTo(questionDto.editorial());
        assertThat(algorithmQuestion.getHints()).isEqualTo(questionDto.hints());
        assertThat(algorithmQuestion.isVerified()).isFalse();
    }

    @Test
    void updateQuestion() {

    }
}