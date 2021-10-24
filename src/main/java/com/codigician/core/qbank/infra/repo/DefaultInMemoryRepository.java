package com.codigician.core.qbank.infra.repo;

import com.codigician.core.qbank.domain.AlgorithmQuestion;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DefaultInMemoryRepository<T extends AlgorithmQuestion> {
    private final Map<String, T> objects = new HashMap<>();

    public Optional<T> findById(String id) {
        return Optional.of(objects.get(id));
    }

    public T save(T object) {
        return objects.put(object.getId(), object);
    }
}
