package com.codigician.core.qbank.infra.http;

import com.codigician.core.qbank.domain.AlgorithmQuestion;
import com.codigician.core.qbank.domain.QuestionFacade;
import com.codigician.core.qbank.infra.dto.CreateQuestionRequest;
import com.codigician.core.qbank.infra.dto.CreateQuestionResponse;
import com.codigician.core.qbank.infra.repo.CouchbaseQuestionRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/question")
public class QuestionController {
    private final QuestionFacade questionFacade;

    public QuestionController(CouchbaseQuestionRepository couchbaseQuestionRepository) {
        questionFacade = new QuestionFacade(couchbaseQuestionRepository);
    }

    @PostMapping
    public CreateQuestionResponse createQuestion(@RequestBody CreateQuestionRequest request) {
        AlgorithmQuestion algorithmQuestion = questionFacade.createQuestion(request.author(), toQuestionDto(request));
        return toResponse(algorithmQuestion);
    }

    private QuestionFacade.QuestionDto toQuestionDto(CreateQuestionRequest request) {
        return new QuestionFacade.QuestionDto(request.title(), request.prompt(), request.editorial(), request.hints());
    }

    private CreateQuestionResponse toResponse(AlgorithmQuestion algorithmQuestion) {
        return new CreateQuestionResponse();
    }
}
