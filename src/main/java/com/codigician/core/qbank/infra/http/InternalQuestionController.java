package com.codigician.core.qbank.infra.http;

import com.codigician.core.qbank.domain.QuestionFacade;
import com.codigician.core.qbank.infra.ObjectFactory;
import com.codigician.core.qbank.infra.dto.UpsertQuestionRequest;
import com.codigician.core.qbank.infra.repo.CouchbaseQuestionRepository;
import org.springframework.web.bind.annotation.*;

import static com.codigician.core.qbank.infra.ObjectMapper.toQuestionDto;

@RestController
@RequestMapping("/internal/api/v1/questions")
// Expect admin users
public class InternalQuestionController {
    private final QuestionFacade questionFacade;

    public InternalQuestionController(CouchbaseQuestionRepository couchbaseQuestionRepository) {
        questionFacade = ObjectFactory.getQuestionFacade(couchbaseQuestionRepository);
    }

    @PutMapping("/{id}/verify")
    public void verifyQuestion(@PathVariable String id) {
        questionFacade.verify(id);
        // get the author information and send email about the confirmation
        // or make this verify function to subscribe some events
    }

    @PutMapping("/{id}")
    public void updateQuestion(@PathVariable String id, @RequestBody UpsertQuestionRequest request) {
        questionFacade.updateQuestion(id, toQuestionDto(request));
    }
}
