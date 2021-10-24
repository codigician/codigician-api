package com.codigician.core.qbank.domain;

import java.util.Optional;

public interface QuestionRepository {
    Optional<AlgorithmQuestion> findById(String id);

    AlgorithmQuestion save(AlgorithmQuestion algorithmQuestion);
}
