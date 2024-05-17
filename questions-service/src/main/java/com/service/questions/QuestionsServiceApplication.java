package com.service.questions;

import com.service.questions.dto.QuestionDTO;
import com.service.questions.repositories.QuestionRepo;
import com.service.questions.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class QuestionsServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(QuestionsServiceApplication.class, args);
    }

    private List<QuestionDTO> createDefaultQuestions() {
        List<QuestionDTO> defaultQuestions = new ArrayList<>();

        // Sample questions with options
        List<String> options1 = Arrays.asList(
                "A programming language",
                "A coffee brand",
                "An operating system",
                "A type of car");
        QuestionDTO question1 = new QuestionDTO(null, "What is Java?", options1, "A programming language", 1);

        List<String> options2 = Arrays.asList(
                "A blueprint for an object",
                "A type of car",
                "A programming language",
                "A drink");
        QuestionDTO question2 = new QuestionDTO(null, "What is a class?", options2, "A blueprint for an object", 2);

        List<String> options3 = Arrays.asList(
                "A class with multiple inheritance",
                "A class with single inheritance",
                "A collection of abstract methods",
                "A type of car");
        QuestionDTO question3 = new QuestionDTO(null, "What is an interface?", options3, "A collection of abstract methods", 3);

        List<String> options4 = Arrays.asList(
                "A lightweight framework for building applications",
                "A type of shoe",
                "A language for database queries",
                "A programming language");
        QuestionDTO question4 = new QuestionDTO(null, "What is Spring Boot?", options4, "A lightweight framework for building applications", 4);

        List<String> options5 = Arrays.asList(
                "An object-relational mapping tool",
                "A type of animal",
                "A programming language",
                "A programming concept");
        QuestionDTO question5 = new QuestionDTO(null, "What is Hibernate?", options5, "An object-relational mapping tool", 5);

        defaultQuestions.add(question1);
        defaultQuestions.add(question2);
        defaultQuestions.add(question3);
        defaultQuestions.add(question4);
        defaultQuestions.add(question5);

        return defaultQuestions;
    }


    private void insertDefaultQuestions() {
        List<QuestionDTO> defaultQuestions = createDefaultQuestions();
        for (QuestionDTO question : defaultQuestions) {
            questionRepo.save(this.service.mapToEntity(question));
        }
    }


    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private QuestionService service;

    @Override
    public void run(String... args) throws Exception {
        insertDefaultQuestions();
    }
}
