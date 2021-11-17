package com.codigician.core.qbank.infra;

import com.codigician.core.qbank.domain.AlgorithmQuestion;
import com.codigician.core.qbank.domain.QuestionFacade;
import com.codigician.core.qbank.infra.dto.UpsertQuestionRequest;
import com.codigician.core.qbank.infra.dto.UpsertQuestionResponse;

public class ObjectMapper {

    public static QuestionFacade.QuestionDto toQuestionDto(UpsertQuestionRequest request) {
        return new QuestionFacade.QuestionDto(request.title(), request.prompt(), request.editorial(), request.hints(), request.expectations());
    }

    public static UpsertQuestionResponse toResponse(AlgorithmQuestion algorithmQuestion) {
        return new UpsertQuestionResponse(algorithmQuestion.getId());
    }
}
