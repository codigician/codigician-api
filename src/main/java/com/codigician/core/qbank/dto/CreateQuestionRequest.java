package com.codigician.core.qbank.dto;

public record CreateQuestionRequest(String prompt, String editorial, String... hints) {
}
