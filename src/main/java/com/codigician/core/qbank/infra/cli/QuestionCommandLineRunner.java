package com.codigician.core.qbank.infra.cli;

import com.codigician.core.qbank.domain.Author;
import com.codigician.core.qbank.domain.Question;
import com.codigician.core.qbank.domain.QuestionFacade;

public class QuestionCommandLineRunner {
    private final QuestionFacade questionFacade;

    public QuestionCommandLineRunner(QuestionFacade questionFacade) {
        this.questionFacade = questionFacade;
    }

    public Question createQuestion(String title, String prompt) {
        var questionDto = new QuestionFacade.QuestionDto(title, prompt);
        Question question = questionFacade.createQuestion(getAuthor(), questionDto);
        return toCreateQuestionResponse(question);
    }

    public void updateQuestion(String id, String title, String prompt) {
        var questionDto = new QuestionFacade.QuestionDto(title, prompt);
        questionFacade.updateQuestion(id, questionDto);
    }

    private Author getAuthor() {
        return new Author();
    }

    private Question toCreateQuestionResponse(Question question) {
        return question;
    }
}
