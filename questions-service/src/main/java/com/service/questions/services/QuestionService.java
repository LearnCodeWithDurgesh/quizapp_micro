package com.service.questions.services;

import com.service.questions.enties.Question;

import java.util.List;
import java.util.Optional;
import com.service.questions.dto.QuestionDTO;
public interface QuestionService {

    QuestionDTO saveQuestion(QuestionDTO questionDTO);

    List<QuestionDTO> getAllQuestions();

    Optional<QuestionDTO> getQuestionById(Integer id);

    List<QuestionDTO> getQuestionsByQuizId(Integer quizId);

    void deleteQuestion(Integer id);

    QuestionDTO updateQuestion(Integer id, QuestionDTO updatedQuestionDTO);
    public QuestionDTO mapToDTO(Question question);
    public Question mapToEntity(QuestionDTO questionDTO);
}
