package com.codigician.core.qbank.infra.repo;

import com.codigician.core.qbank.domain.AlgorithmQuestion;
import com.codigician.core.qbank.domain.QuestionRepository;

public class InMemoryQuestionRepository extends DefaultInMemoryRepository<AlgorithmQuestion> implements QuestionRepository {
}
