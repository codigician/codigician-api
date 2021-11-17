package com.codigician.core.codexec.domain;

public interface MetricRecorder {
    void record(String recordId);
    void done(String recordId);
}
