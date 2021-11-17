package com.codigician.core.qbank.infra;

import com.codigician.core.qbank.domain.QuestionFacade;
import com.codigician.core.qbank.domain.QuestionRepository;

public class ObjectFactory {
    private static QuestionFacade questionFacade;

    public static QuestionFacade getQuestionFacade(QuestionRepository questionRepository) {
        if (questionFacade == null) {
            questionFacade = new QuestionFacade(questionRepository);
        }

        return questionFacade;
    }
}
