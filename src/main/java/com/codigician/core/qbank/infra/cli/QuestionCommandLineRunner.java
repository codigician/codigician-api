package com.codigician.core.qbank.infra.cli;

import com.codigician.core.qbank.domain.Author;
import com.codigician.core.qbank.domain.AlgorithmQuestion;
import com.codigician.core.qbank.domain.QuestionFacade;

import java.util.List;

public class QuestionCommandLineRunner {
    private final QuestionFacade questionFacade;

    public QuestionCommandLineRunner(QuestionFacade questionFacade) {
        this.questionFacade = questionFacade;
    }

    public AlgorithmQuestion createQuestion(String title, String prompt, String editorial) {
        var questionDto = new QuestionFacade.QuestionDto(title, prompt, editorial, List.of(""));
        AlgorithmQuestion algorithmQuestion = questionFacade.createQuestion(getAuthor(), questionDto);
        return toCreateQuestionResponse(algorithmQuestion);
    }

    public void updateQuestion(String id, String title, String prompt, String editorial) {
        var questionDto = new QuestionFacade.QuestionDto(title, prompt, editorial, List.of(""));
        questionFacade.updateQuestion(id, questionDto);
    }

    private Author getAuthor() {
        return new Author();
    }

    private AlgorithmQuestion toCreateQuestionResponse(AlgorithmQuestion algorithmQuestion) {
        return algorithmQuestion;
    }
}
