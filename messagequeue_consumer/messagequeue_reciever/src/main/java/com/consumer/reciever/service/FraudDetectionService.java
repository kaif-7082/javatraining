package com.consumer.reciever.service;

import com.consumer.reciever.aop.LogExecution;
import com.consumer.reciever.dto.UserActivityEvent;
import com.consumer.reciever.model.SecurityLog;
import com.consumer.reciever.repository.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class FraudDetectionService {

    @Autowired
    private SecurityRepository repository;

    // This method is now visible to AOP!
    @LogExecution
    public void processEvent(UserActivityEvent event) {
        System.out.println("Processing event: " + event.getType() + " for " + event.getUserId());

        // 1. Log to DB
        repository.save(new SecurityLog(event.getUserId(), event.getType(), LocalDateTime.now()));

        // 2. Security Logic
        if ("LOGIN_FAILURE".equals(event.getType())) {
            checkForBruteForce(event.getUserId());
        }
    }

    private void checkForBruteForce(String userId) {
        LocalDateTime fiveMinutesAgo = LocalDateTime.now().minusMinutes(5);
        long failures = repository.countByUserIdAndEventTypeAndTimestampAfter(
                userId, "LOGIN_FAILURE", fiveMinutesAgo
        );

        if (failures >= 3) {
            System.err.println(" CRITICAL ALERT: Brute Force detected for user " + userId);
        }
    }
}