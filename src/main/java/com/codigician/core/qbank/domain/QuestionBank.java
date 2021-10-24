package com.codigician.core.qbank.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionBank {
    private final List<AlgorithmQuestion> algorithmQuestions;
    private final Map<Tag, List<AlgorithmQuestion>> tagQuestionMap;

    public QuestionBank() {
        algorithmQuestions = new ArrayList<>();
        tagQuestionMap = new HashMap<>();
    }

    public AlgorithmQuestion getRandomQuestion() {
        return null;
    }

    public List<AlgorithmQuestion> filter(Tag... tags) {
        var filteredQuestions = new ArrayList<AlgorithmQuestion>();
        var questionFilterCount = new HashMap<AlgorithmQuestion, Integer>();
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
