package com.codigician.core.qbank.service;

import com.codigician.core.qbank.dto.CreateQuestionRequest;
import com.codigician.core.qbank.dto.UpdateQuestionRequest;
import com.codigician.core.qbank.model.*;

import java.util.List;

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

    public List<Question> filterQuestions(List<Tag> tags) {
        QuestionBank questionBank = loadQuestionBank();
        return questionBank.filter(tags.toArray(new Tag[0]));
    }

    private Author getAuthorInformation() {
        return new Author();
    }

    private QuestionBank loadQuestionBank() {
        return new QuestionBank();
    }
}
