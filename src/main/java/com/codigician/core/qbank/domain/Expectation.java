package com.codigician.core.qbank.domain;

public class Expectation {
    private String input;
    private String output;
    private int timeLimit;

    public Expectation(String input, String output) {
        this.input = input;
        this.output = output;
        this.timeLimit = -1;
    }

    public Expectation(String input, String output, int timeLimit) {
        this(input, output);
        this.timeLimit = timeLimit;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }
}
