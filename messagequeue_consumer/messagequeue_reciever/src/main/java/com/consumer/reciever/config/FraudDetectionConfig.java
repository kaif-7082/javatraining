package com.consumer.reciever.config;

import com.consumer.reciever.dto.UserActivityEvent;
import com.consumer.reciever.service.FraudDetectionService; // Import the new service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.function.Consumer;

@Configuration
public class FraudDetectionConfig {

    @Autowired
    private FraudDetectionService fraudDetectionService;

    @Bean
    public Consumer<UserActivityEvent> fraudDetector() {
        // Just pass the ball to the Service
        return event -> fraudDetectionService.processEvent(event);
    }
}