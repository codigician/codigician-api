package com.codigician.core.codexec.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ContainerMetrics implements MetricRecorder {
    private final Map<String, SingleExecutionMetricRecorder> metricRecorderMap = new ConcurrentHashMap<>();
    private final AtomicInteger activeRecordings = new AtomicInteger();

    public SingleExecutionMetricRecorder startSingleExecution() {
        return SingleExecutionMetricRecorder.start(this);
    }

    @Override
    public void record(String recordId) {
        activeRecordings.incrementAndGet();
    }

    @Override
    public void done(String recordId) {
        activeRecordings.decrementAndGet();
    }

    public int getActiveTaskCount() {
        return activeRecordings.get();
    }
}
