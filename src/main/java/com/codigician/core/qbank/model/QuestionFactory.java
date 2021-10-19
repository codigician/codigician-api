package com.codigician.core.qbank.model;

import java.util.Arrays;

public class QuestionFactory {
    public Question createQuestion(Author author, String prompt, String editorial, String ...hints) {
        Question question = new Question(author, prompt, editorial);
        question.setHints(Arrays.asList(hints));
        return question;
    }
}
