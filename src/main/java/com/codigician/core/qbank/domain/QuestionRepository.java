package com.codigician.core.qbank.domain;

import java.util.Optional;

public interface QuestionRepository {
    Optional<Question> findById(String id);

    Question save(Question question);
}
