package com.codigician.core.qbank.infra.http;

import com.codigician.core.qbank.domain.Author;

import java.util.List;

public record CreateQuestionRequest(Author author, String title, String prompt, String editorial, List<String> hints) {
}
