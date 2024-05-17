package com.service.quiz;

import com.service.quiz.entities.Quiz;
import com.service.quiz.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class QuizServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(QuizServiceApplication.class, args);
    }

    @Autowired
    private QuizService quizService;

    @Override
    public void run(String... args) throws Exception {
        insertDefaultQuizzes();
        System.out.println("quiz created");
    }

    private void insertDefaultQuizzes() {
        List<Quiz> defaultQuizzes = createDefaultQuizzes();
        for (Quiz quiz : defaultQuizzes) {
            quizService.saveQuiz(quiz);
        }
    }

    private List<Quiz> createDefaultQuizzes() {
        List<Quiz> defaultQuizzes = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            Quiz quiz = new Quiz();
            quiz.setQuizTitle("Quiz " + i);
            quiz.setQuizDescription("Description for Quiz " + i);
            quiz.setMaxMarks("10");
            quiz.setDuration("30");
            quiz.setQuestions("10"); // Example questions
            defaultQuizzes.add(quiz);
        }

        return defaultQuizzes;
    }
}
