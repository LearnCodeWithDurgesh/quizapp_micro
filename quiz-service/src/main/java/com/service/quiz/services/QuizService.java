package com.service.quiz.services;

import com.service.quiz.entities.Quiz;

import java.util.List;
import java.util.Optional;

public interface QuizService {

    Quiz saveQuiz(Quiz quiz);

    List<Quiz> getAllQuizzes();

    Optional<Quiz> getQuizById(Integer quizId);

    void deleteQuiz(Integer quizId);

    Quiz updateQuiz(Integer quizId, Quiz updatedQuiz);

    List<Quiz> searchQuiz(String title);
}
