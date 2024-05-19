package com.service.quiz.services;

import com.service.quiz.dto.QuestionDTO;
import com.service.quiz.entities.Quiz;
import com.service.quiz.external.QuestionsFeignService;
import com.service.quiz.repositories.QuizRepo;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
    @CircuitBreaker(name = "BACKEND", fallbackMethod = "allFallbackMethod")
    public List<Quiz> getAllQuizzes() {
        List<Quiz> all = quizRepository.findAll();
        return all.stream().map(quiz -> {
            //calling question service to get the questions of the quiz
            //setting questionDTOList to quiz
            quiz.setQuestionDTOList(questionsFeignService.getQuestionsOfQuiz(quiz.getQuizId()));
            return quiz;
        }).collect(Collectors.toList());

    }

    //call when question service fails

    public List<Quiz> allFallbackMethod(Exception ex) {
        ex.printStackTrace();
        System.out.println("Fallback method executed");
        return List.of(
                Quiz.builder()
                        .quizId(14)
                        .quizTitle("Fallback Quiz")
                        .quizDescription("This is fallback quiz created for working when question service is down")
                        .maxMarks("0")
                        .duration("0")
                        .questions("0")
                        .build()
        );
    }

    @Override
    public Optional<Quiz> getQuizById(Integer quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found !!"));
       // getting questions list from question service
        List<QuestionDTO> questionsOfQuiz = questionsFeignService.getQuestionsOfQuiz(quiz.getQuizId());
       //setting questions to quiz
        quiz.setQuestionDTOList(questionsOfQuiz);
        return Optional.of(quiz);

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
            throw new RuntimeException("Quiz not found with ID: " + quizId);
        }
    }

    @Override
    public List<Quiz> searchQuiz(String title) {
        return quizRepository.searchQuery(title);
    }
}
