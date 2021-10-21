package com.codigician.core.qbank.infra.cli;

import com.codigician.core.qbank.domain.QuestionFacade;
import com.codigician.core.qbank.infra.repo.InMemoryQuestionRepository;

import java.util.Scanner;

public class QuestionCommandLineDisplay {
    private final Scanner scanner = new Scanner(System.in);
    private final QuestionCommandLineRunner commandLineRunner;

    public QuestionCommandLineDisplay() {
        var questionRepository = new InMemoryQuestionRepository();
        var questionFacade = new QuestionFacade(questionRepository);

        commandLineRunner = new QuestionCommandLineRunner(questionFacade);
    }

    public void displayCreateQuestionMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Create question menu");

        System.out.println("Please enter question name: ");
        String questionTitle = scanner.next();

        System.out.println("Please enter question prompt");
        String questionPrompt = scanner.next();

        var question = commandLineRunner.createQuestion(questionTitle, questionPrompt);
        System.out.println(question);
    }
}
