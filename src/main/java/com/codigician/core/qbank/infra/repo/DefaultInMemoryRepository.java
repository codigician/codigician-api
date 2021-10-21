package com.codigician.core.qbank.infra.repo;

import com.codigician.core.qbank.domain.BaseEntity;

import java.util.HashMap;
import java.util.Map;

public class DefaultInMemoryRepository<T extends BaseEntity> {
    private final Map<String, T> objects = new HashMap<>();

    public T find(String id) {
        return objects.get(id);
    }

    public void save(T object) {
        objects.put(object.getId(), object);
    }

    public void update(T object) {
        objects.put(object.getId(), object);
    }
}
