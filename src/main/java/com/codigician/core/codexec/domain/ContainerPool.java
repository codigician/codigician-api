package com.codigician.core.codexec.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContainerPool {
    private ContainerPoolSettings containerPoolSettings;
    private Map<String, GenericContainer> containers = new HashMap<>();
    private Map<String, Thread> activeContainerThreads = new ConcurrentHashMap<>();

    public GenericContainer getAvailableContainer() {
        return null;
    }

    public GenericContainer getContainer(String id) {
        return containers.get(id);
    }

    public static class ContainerPoolSettings {

    }
}
