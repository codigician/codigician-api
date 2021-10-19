package com.codigician.core.qbank.model;

public interface QuestionRepository {
    Question find(String id);

    void save(Question question);

    void update(Question question);
}
