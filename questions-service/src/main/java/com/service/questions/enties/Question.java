package com.service.questions.enties;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "questions_app")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String question;
    @ElementCollection
    private List<String> options = new ArrayList<>();
    private String answer;
    private Integer quizId;
}
