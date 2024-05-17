package com.service.quiz.controllers;


import com.service.quiz.entities.Quiz;
import com.service.quiz.services.QuizService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {


    private final QuizService quizService;


    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@Valid @RequestBody Quiz quiz) {
        Quiz savedQuiz = quizService.saveQuiz(quiz);
        return new ResponseEntity<>(savedQuiz, HttpStatus.CREATED);
    }

    @GetMapping

    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }


    @GetMapping("/{quizId}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Integer quizId) {
        Optional<Quiz> optionalQuiz = quizService.getQuizById(quizId);
        return optionalQuiz.map(quiz -> new ResponseEntity<>(quiz, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{quizId}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Integer quizId) {
        quizService.deleteQuiz(quizId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{quizId}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Integer quizId, @RequestBody Quiz updatedQuiz) {
        Quiz quiz = quizService.updateQuiz(quizId, updatedQuiz);
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Quiz>> updateQuiz(@PathVariable String keyword) {
        return new ResponseEntity<>(quizService.searchQuiz(keyword), HttpStatus.OK);
    }

}
