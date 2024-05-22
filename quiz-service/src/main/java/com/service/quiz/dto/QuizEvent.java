package com.service.quiz.dto;


import com.service.quiz.entities.Quiz;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuizEvent {


    private String message;
    private String status;
    private int  quizId;

}


