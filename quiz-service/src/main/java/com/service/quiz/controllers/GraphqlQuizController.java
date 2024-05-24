package com.service.quiz.controllers;


import com.service.quiz.dto.QuizEvent;
import com.service.quiz.entities.Quiz;
import com.service.quiz.kafka.QuizProducer;
import com.service.quiz.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphqlQuizController {

    @Autowired
    private QuizProducer producer;

    @Autowired
    private QuizService quizService;

    @MutationMapping
    public Quiz createQuiz(
            @Argument String quizTitle,
            @Argument String quizDescription,
            @Argument String maxMarks,
            @Argument String duration,
            @Argument String questions
    ) {


        Quiz quiz = new Quiz();
        quiz.setQuizTitle(quizTitle);
        quiz.setQuizDescription(quizDescription);
        quiz.setMaxMarks(maxMarks);
        quiz.setDuration(duration);
        quiz.setQuestions(questions);


        Quiz quiz1 = quizService.saveQuiz(quiz);

        QuizEvent quizEvent = new QuizEvent();
        quizEvent.setQuizId(quiz.getQuizId());
        quizEvent.setMessage("Quiz is Created using graphql");
        quizEvent.setStatus("SUCCESS");
        producer.sendEvent(quizEvent);
        return quiz1;


    }

    @QueryMapping
    public List<Quiz> getQuizzes() {
        return quizService.getAllQuizzes();

    }

    @QueryMapping
    public Quiz getQuiz(@Argument int quizId) {
        return quizService.getQuizById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found !!"));
    }

}
