package com.codigician.core.qbank.domain;

import com.codigician.core.qbank.infra.repo.InMemoryQuestionRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class QuestionFacadeTest {
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

        Question question = questionFacade.createQuestion(AUTHOR, questionDto);

        assertThat(question.getTitle()).isEqualTo(questionDto.title());
        assertThat(question.getPrompt()).isEqualTo(questionDto.prompt());
        assertThat(question.getEditorial()).isEqualTo(questionDto.editorial());
        assertThat(question.getHints()).isEqualTo(questionDto.hints());
        assertThat(question.isVerified()).isFalse();
    }

    @Test
    void updateQuestion() {

    }
}