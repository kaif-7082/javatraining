package com.kaif.reviewms.review.messaging;

import com.kaif.reviewms.review.dto.ReviewMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageProducer {

    private final KafkaTemplate<String, ReviewMessage> kafkaTemplate;

    public ReviewMessageProducer(KafkaTemplate<String, ReviewMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(ReviewMessage message) {
        // Topic name: companyRatingQueue
        kafkaTemplate.send("companyRatingQueue", message);
    }
}