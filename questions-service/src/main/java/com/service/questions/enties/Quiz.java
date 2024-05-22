package com.service.questions.enties;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
    private Integer quizId;
    private String quizTitle;
    private String quizDescription;
    private String maxMarks;
    private String duration;
    private String questions;


}
