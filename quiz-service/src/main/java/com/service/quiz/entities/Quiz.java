package com.service.quiz.entities;

import com.service.quiz.dto.QuestionDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "quiz_app")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer quizId;
    private String quizTitle;
    private String quizDescription;
    private String maxMarks;
    private String duration;
    private String questions;

    @Transient
    private List<QuestionDTO> questionDTOList;
}
