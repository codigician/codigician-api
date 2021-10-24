package com.codigician.core.qbank.infra.http;

import com.codigician.core.qbank.domain.Question;
import com.codigician.core.qbank.domain.QuestionFacade;
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
        Question question = questionFacade.createQuestion(request.author(), toQuestionDto(request));
        return toResponse(question);
    }

    private QuestionFacade.QuestionDto toQuestionDto(CreateQuestionRequest request) {
        return new QuestionFacade.QuestionDto(request.title(), request.prompt(), request.editorial(), request.hints());
    }

    private CreateQuestionResponse toResponse(Question question) {
        return new CreateQuestionResponse();
    }
}
