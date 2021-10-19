package com.codigician.core.qbank.service;

public record CreateQuestionRequest(String prompt, String editorial, String ...hints) {
}
