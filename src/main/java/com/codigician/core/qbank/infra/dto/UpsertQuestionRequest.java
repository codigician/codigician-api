package com.codigician.core.qbank.infra.dto;

import com.codigician.core.qbank.domain.Author;
import com.codigician.core.qbank.domain.Expectation;

import java.util.List;

public record UpsertQuestionRequest(Author author,
                                    String title,
                                    String prompt,
                                    String editorial,
                                    List<String> hints,
                                    List<Expectation> expectations) {
}
