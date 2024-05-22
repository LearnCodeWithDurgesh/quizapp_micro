package com.service.quiz.kafka;

import com.service.quiz.dto.QuizEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.internals.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class QuizProducer {
    private NewTopic topic;
    private KafkaTemplate<String, QuizEvent> kafkaTemplate;
    public QuizProducer(NewTopic topic, KafkaTemplate<String, QuizEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }
    private Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
    public void sendEvent(QuizEvent event) {
        logger.info("Quiz Event : {} ",event.toString());
        Message<QuizEvent> build = MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(build);


    }
}
