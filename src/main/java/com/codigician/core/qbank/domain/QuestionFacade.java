package com.codigician.core.qbank.domain;

public class QuestionFacade {
    private final QuestionRepository questionRepository;

    public QuestionFacade(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question createQuestion(Author author, QuestionDto questionDto) {
        Question question = new Question(author, questionDto.title, questionDto.prompt);
        questionRepository.save(question);
        return question;
    }

    public void updateQuestion(String id, QuestionDto questionDto) {
        Question question = questionRepository.find(id);
        Question newQuestion = new Question();
        question.update(newQuestion);
        questionRepository.save(question);
    }


    public static record QuestionDto(String title, String prompt) {
    }
}
