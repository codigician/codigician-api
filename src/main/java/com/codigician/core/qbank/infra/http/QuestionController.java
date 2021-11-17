package com.codigician.core.qbank.infra.http;

import com.codigician.core.qbank.domain.AlgorithmQuestion;
import com.codigician.core.qbank.domain.QuestionFacade;
import com.codigician.core.qbank.infra.ObjectFactory;
import com.codigician.core.qbank.infra.dto.UpsertQuestionRequest;
import com.codigician.core.qbank.infra.dto.UpsertQuestionResponse;
import com.codigician.core.qbank.infra.repo.CouchbaseQuestionRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.codigician.core.qbank.infra.ObjectMapper.toQuestionDto;
import static com.codigician.core.qbank.infra.ObjectMapper.toResponse;

@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController {
    private final QuestionFacade questionFacade;

    public QuestionController(CouchbaseQuestionRepository couchbaseQuestionRepository) {
        questionFacade = ObjectFactory.getQuestionFacade(couchbaseQuestionRepository);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UpsertQuestionResponse createQuestion(@RequestBody UpsertQuestionRequest request) {
        AlgorithmQuestion algorithmQuestion = questionFacade.createQuestion(request.author(), toQuestionDto(request));
        return toResponse(algorithmQuestion);
    }
}
