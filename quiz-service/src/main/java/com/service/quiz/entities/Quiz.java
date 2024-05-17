package com.service.quiz.entities;

import com.service.quiz.dto.QuestionDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "quiz_app")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer quizId;

    @NotBlank(message = "Name is required")
    @Size(max = 10, min = 2, message = "Size must be between 2 to 10.")
    private String quizTitle;
    @NotBlank(message = "Description is required")
    private String quizDescription;
    @NotBlank(message = "Marks is required")
    private String maxMarks;
    @NotBlank(message = "Duration is required")
    private String duration;
    private String questions;

    @Transient
    private List<QuestionDTO> questionDTOList;
}
