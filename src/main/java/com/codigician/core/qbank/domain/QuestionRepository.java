package com.codigician.core.qbank.domain;

public interface QuestionRepository {
    Question find(String id);

    void save(Question question);

    void update(Question question);
}
