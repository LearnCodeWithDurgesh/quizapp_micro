package com.service.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {



    private Integer id;
    private String question;
    private List<String> options = new ArrayList<>();
    private String answer;
    private Integer quizId;
}
