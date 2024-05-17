package com.service.quiz.services;

import com.service.quiz.entities.Quiz;
import com.service.quiz.external.QuestionsFeignService;
import com.service.quiz.repositories.QuizRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepo quizRepository;

    private final QuestionsFeignService questionsFeignService;


    public QuizServiceImpl(QuizRepo quizRepository, QuestionsFeignService questionsFeignService) {
        this.quizRepository = quizRepository;
        this.questionsFeignService = questionsFeignService;
    }

    @Override
    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> getAllQuizzes() {

        List<Quiz> all = quizRepository.findAll();

        return all.stream().map(quiz -> {
            quiz.setQuestionDTOList(questionsFeignService.getQuestionsOfQuiz(quiz.getQuizId()));
            return quiz;
        }).collect(Collectors.toList());

    }

    @Override
    public Optional<Quiz> getQuizById(Integer quizId) {
        return quizRepository.findById(quizId);
    }

    @Override
    public void deleteQuiz(Integer quizId) {
        quizRepository.deleteById(quizId);
    }

    @Override
    public Quiz updateQuiz(Integer quizId, Quiz updatedQuiz) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);
        if (optionalQuiz.isPresent()) {
            Quiz quiz = optionalQuiz.get();
            quiz.setQuizTitle(updatedQuiz.getQuizTitle());
            quiz.setQuizDescription(updatedQuiz.getQuizDescription());
            quiz.setMaxMarks(updatedQuiz.getMaxMarks());
            quiz.setDuration(updatedQuiz.getDuration());
            quiz.setQuestions(updatedQuiz.getQuestions());
            // Set other properties as needed
            return quizRepository.save(quiz);
        } else {
            throw new IllegalArgumentException("Quiz not found with ID: " + quizId);
        }
    }
}
