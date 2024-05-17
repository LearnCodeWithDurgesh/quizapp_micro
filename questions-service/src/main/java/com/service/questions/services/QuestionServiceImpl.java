package com.service.questions.services;

import com.service.questions.dto.QuestionDTO;
import com.service.questions.enties.Question;
import com.service.questions.repositories.QuestionRepo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepo questionRepository;


    public QuestionServiceImpl(QuestionRepo questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public QuestionDTO saveQuestion(QuestionDTO questionDTO) {
        Question question = mapToEntity(questionDTO);
        question = questionRepository.save(question);
        return mapToDTO(question);
    }

    @Override
    public List<QuestionDTO> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<QuestionDTO> getQuestionById(Integer id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        return optionalQuestion.map(this::mapToDTO);
    }

    @Override
    public List<QuestionDTO> getQuestionsByQuizId(Integer quizId) {
        List<Question> questions = questionRepository.findByQuizId(quizId);
        return questions.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteQuestion(Integer id) {
        questionRepository.deleteById(id);
    }

    @Override
    public QuestionDTO updateQuestion(Integer id, QuestionDTO updatedQuestionDTO) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isPresent()) {
            Question existingQuestion = optionalQuestion.get();
            Question updatedQuestion = mapToEntity(updatedQuestionDTO);
            existingQuestion.setQuestion(updatedQuestion.getQuestion());
            existingQuestion.setOptions(updatedQuestion.getOptions());
            existingQuestion.setAnswer(updatedQuestion.getAnswer());
            existingQuestion.setQuizId(updatedQuestion.getQuizId());
            // Set other properties as needed
            existingQuestion = questionRepository.save(existingQuestion);
            return mapToDTO(existingQuestion);
        } else {
            throw new IllegalArgumentException("Question not found with ID: " + id);
        }
    }

    public QuestionDTO mapToDTO(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setQuestion(question.getQuestion());
        questionDTO.setOptions(question.getOptions());
        questionDTO.setAnswer(question.getAnswer());
        questionDTO.setQuizId(question.getQuizId());
        return questionDTO;
    }

    public Question mapToEntity(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setId(questionDTO.getId());
        question.setQuestion(questionDTO.getQuestion());
        question.setOptions(questionDTO.getOptions());
        question.setAnswer(questionDTO.getAnswer());
        question.setQuizId(questionDTO.getQuizId());
        return question;
    }
}
