package com.service.quiz.external;

import com.service.quiz.dto.QuestionDTO;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;



@FeignClient(name = "QUESTIONS-SERVICE")
public interface QuestionsFeignService {


    @GetMapping("/api/questions/quiz/{quizId}")
    List<QuestionDTO> getQuestionsOfQuiz(@PathVariable("quizId") Integer quizId);

}
