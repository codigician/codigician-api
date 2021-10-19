package com.codigician.core;

import com.codigician.core.qbank.model.QuestionFactory;
import com.codigician.core.qbank.repo.InMemoryQuestionRepository;
import com.codigician.core.qbank.service.CreateQuestionRequest;
import com.codigician.core.qbank.service.QuestionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CoreApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }

    @GetMapping("/health")
    public void health() {
        System.out.println("health check");
    }

    @Override
    public void run(String... args) throws Exception {
        var questionRepository = new InMemoryQuestionRepository();
        var questionFactory = new QuestionFactory();
        var questionService = new QuestionService(questionFactory, questionRepository);

        var createQuestionRequest = new CreateQuestionRequest("my new prompt", "editorial");
        var question = questionService.create(createQuestionRequest);

        System.out.println("Question created..");
        System.out.println(question);
    }
}
