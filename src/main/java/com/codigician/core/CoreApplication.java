package com.codigician.core;

import com.codigician.core.qbank.model.Question;
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
        Question question = Question.create()
                .prompt("prompt")
                .editorial("editorial")
                .build();
    }
}
