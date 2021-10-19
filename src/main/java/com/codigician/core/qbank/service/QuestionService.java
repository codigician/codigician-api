package com.codigician.core.qbank.service;

import com.codigician.core.qbank.model.Author;
import com.codigician.core.qbank.model.Question;

import java.util.Arrays;

public class QuestionService {

    public Question create(CreateQuestionRequest request) {
        Author author = getAuthorInformation();
        Question question = createQuestion(author, request);
        saveQuestionToWaitingVerifyStage(question);
        return question;
    }

    public Question update(UpdateQuestionRequest request) {
        Author author = getAuthorInformation();
        Question question = getQuestion(request.questionId());
        return null;
    }

    private void saveQuestionToWaitingVerifyStage(Question question) {

    }

    private Author getAuthorInformation() {
        return new Author();
    }

    private Question createQuestion(Author author, CreateQuestionRequest request) {
        return Question.create()
                .author(author)
                .prompt(request.prompt())
                .editorial(request.editorial())
                .hints(Arrays.asList(request.hints()))
                .verified(false)
                .build();
    }

    private Question getQuestion(String questionId) {
        return null;
    }
}
