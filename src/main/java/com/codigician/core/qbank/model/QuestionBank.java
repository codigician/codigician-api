package com.codigician.core.qbank.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionBank {
    private final List<Question> questions;
    private final Map<Tag, List<Question>> tagQuestionMap;

    public QuestionBank() {
        questions = new ArrayList<>();
        tagQuestionMap = new HashMap<>();
    }

    public Question getRandomQuestion() {
        return null;
    }

    public List<Question> filter(Tag... tags) {
        var filteredQuestions = new ArrayList<Question>();
        var questionFilterCount = new HashMap<Question, Integer>();
        for (var tag : tags) {
            var questions = tagQuestionMap.getOrDefault(tag, new ArrayList<>());
            for (var question : questions) {
                questionFilterCount.putIfAbsent(question, 0);
                questionFilterCount.compute(question, (q, count) -> count + 1);
            }
        }

        int tagCount = tags.length;
        for (var mapEntry : questionFilterCount.entrySet()) {
            if (mapEntry.getValue() == tagCount) {
                filteredQuestions.add(mapEntry.getKey());
            }
        }

        return filteredQuestions;
    }
}
