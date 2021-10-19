package com.codigician.core.qbank.service;

import com.codigician.core.qbank.model.Author;
import com.codigician.core.qbank.model.Question;
import com.codigician.core.qbank.model.QuestionFactory;
import com.codigician.core.qbank.model.QuestionRepository;


public class QuestionService {
    private final QuestionFactory questionFactory;
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionFactory questionFactory, QuestionRepository questionRepository) {
        this.questionFactory = questionFactory;
        this.questionRepository = questionRepository;
    }

    public Question create(CreateQuestionRequest request) {
        Author author = getAuthorInformation();
        Question question = questionFactory.createQuestion(author, request.prompt(), request.editorial(), request.hints());
        questionRepository.save(question);
        return question;
    }

    public Question update(UpdateQuestionRequest request) {
        Author author = getAuthorInformation();
        Question question = questionRepository.find(request.questionId());
        return null;
    }

    private Author getAuthorInformation() {
        return new Author();
    }
}
