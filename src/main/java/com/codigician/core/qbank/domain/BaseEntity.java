package com.codigician.core.qbank.domain;

import java.util.UUID;

public abstract class BaseEntity {
    protected final String id;

    public BaseEntity() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }
}
