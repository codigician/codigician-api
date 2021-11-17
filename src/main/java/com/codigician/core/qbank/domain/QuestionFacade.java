package com.codigician.core.qbank.domain;

import java.util.List;

public class QuestionFacade {
    private final QuestionRepository questionRepository;

    public QuestionFacade(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public AlgorithmQuestion createQuestion(Author author, QuestionDto questionDto) {
        AlgorithmQuestion algorithmQuestion = questionFrom(author, questionDto);
        questionRepository.save(algorithmQuestion);
        return algorithmQuestion;
    }

    public void updateQuestion(String id, QuestionDto questionDto) {
        AlgorithmQuestion algorithmQuestionToUpdate = questionRepository.findById(id).orElseThrow();
        AlgorithmQuestion newAlgorithmQuestion = questionFrom(algorithmQuestionToUpdate.getAuthor(), questionDto);
        algorithmQuestionToUpdate.update(newAlgorithmQuestion);
        questionRepository.save(algorithmQuestionToUpdate);
    }

    public void verify(String questionId) {
        AlgorithmQuestion algorithmQuestion = questionRepository.findById(questionId).orElseThrow();
        algorithmQuestion.verify();
        questionRepository.save(algorithmQuestion);
    }

    private AlgorithmQuestion questionFrom(Author author, QuestionDto questionDto) {
        return AlgorithmQuestion.create(author, questionDto.title, questionDto.prompt)
                .editorial(questionDto.editorial)
                .hints(questionDto.hints)
                .build();
    }

    public static record QuestionDto(String title,
                                     String prompt,
                                     String editorial,
                                     List<String> hints,
                                     List<Expectation> expectations) {
    }
}
