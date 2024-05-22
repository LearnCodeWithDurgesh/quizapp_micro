package com.service.questions.dto;



import com.service.questions.enties.Quiz;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizEventt {


    private String message;
    private String status;
    private int quizId;

}


