package com.kaif.reviewms.review.messaging;

import com.kaif.reviewms.review.dto.ReviewMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
public class ReviewMessageProducer {

    private final KafkaTemplate<String, ReviewMessage> kafkaTemplate;

    public ReviewMessageProducer(KafkaTemplate<String, ReviewMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    @Value("${spring.kafka.topic.name}")
    private String topicName;

    public void sendMessage(ReviewMessage message) {

        kafkaTemplate.send(topicName, message);    }
}