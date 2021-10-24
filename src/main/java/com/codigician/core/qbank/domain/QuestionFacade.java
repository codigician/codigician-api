package com.codigician.core.qbank.domain;

import java.util.List;

public class QuestionFacade {
    private final QuestionRepository questionRepository;

    public QuestionFacade(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question createQuestion(Author author, QuestionDto questionDto) {
        Question question = questionFrom(author, questionDto);
        questionRepository.save(question);
        return question;
    }

    public void updateQuestion(String id, QuestionDto questionDto) {
        Question questionToUpdate = questionRepository.findById(id).orElseThrow();
        Question newQuestion = questionFrom(questionToUpdate.getAuthor(), questionDto);
        questionToUpdate.update(newQuestion);
        questionRepository.save(questionToUpdate);
    }

    public void verify(String questionId) {
        Question question = questionRepository.findById(questionId).orElseThrow();
        question.verify();
        questionRepository.save(question);
    }

    private Question questionFrom(Author author, QuestionDto questionDto) {
        return Question.create(author, questionDto.title, questionDto.prompt)
                .editorial(questionDto.editorial)
                .hints(questionDto.hints)
                .build();
    }

    public static record QuestionDto(String title,
                                     String prompt,
                                     String editorial,
                                     List<String> hints) {
    }
}
