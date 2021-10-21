package com.codigician.core.qbank.infra.repo;

import com.codigician.core.qbank.domain.Question;
import com.codigician.core.qbank.domain.QuestionRepository;

public class InMemoryQuestionRepository extends DefaultInMemoryRepository<Question> implements QuestionRepository {
}
