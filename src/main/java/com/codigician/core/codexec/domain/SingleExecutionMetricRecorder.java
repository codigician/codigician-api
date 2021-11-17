package com.codigician.core.codexec.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class SingleExecutionMetricRecorder {
    private final String recordId;
    private final MetricRecorder recorder;
    private final long startTime;
    private long elapsedTime;

    private SingleExecutionMetricRecorder(MetricRecorder recorder) {
        this.recordId = UUID.randomUUID().toString();
        this.recorder = recorder;
        this.startTime = Timestamp.valueOf(LocalDateTime.now()).getTime();
        recorder.record(recordId);
    }

    public static SingleExecutionMetricRecorder start(MetricRecorder recorder) {
        return new SingleExecutionMetricRecorder(recorder);
    }

    public void end() {
        elapsedTime = Timestamp.valueOf(LocalDateTime.now()).getTime() - startTime;
        recorder.done(recordId);
    }

    public long getElapsedTime() {
        return elapsedTime;
    }
}
