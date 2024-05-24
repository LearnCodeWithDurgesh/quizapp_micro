package com.service.questions.kafka;

import com.service.questions.dto.QuestionDTO;
import com.service.questions.dto.QuizEventt;
import com.service.questions.enties.Question;
import com.service.questions.services.QuestionService;
import com.service.quiz.dto.QuizEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class KafkaConsumer {


    public static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private QuestionService questionService;

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"

    )
    public void consume(QuizEvent event)
    {

        logger.info("Consuming event {} ", event.toString());

        // Sample questions with options

        QuestionDTO question = new QuestionDTO();
        question.setQuestion("What is collection in java ?");
        question.setAnswer("API");
        question.setOptions(List.of("API", "Service", "None"));
        question.setQuizId(event.getQuizId());

        questionService.saveQuestion(question);

        System.out.println("New question created for quiz");


    }

}
