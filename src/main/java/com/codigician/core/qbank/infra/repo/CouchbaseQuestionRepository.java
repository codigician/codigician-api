package com.codigician.core.qbank.infra.repo;

import com.codigician.core.qbank.domain.AlgorithmQuestion;
import com.codigician.core.qbank.domain.QuestionRepository;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouchbaseQuestionRepository extends CouchbaseRepository<AlgorithmQuestion, String>, QuestionRepository {
}
