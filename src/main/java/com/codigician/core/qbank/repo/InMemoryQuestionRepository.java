package com.codigician.core.qbank.repo;

import com.codigician.core.qbank.model.Question;
import com.codigician.core.qbank.model.QuestionRepository;

public class InMemoryQuestionRepository extends DefaultInMemoryRepository<Question> implements QuestionRepository {
}
