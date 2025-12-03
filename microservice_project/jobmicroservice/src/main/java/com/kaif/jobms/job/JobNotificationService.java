package com.kaif.jobms.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JobNotificationService {

    // This method will run in a separate thread
    @Async
    public void notifyCompany(String jobTitle, Long companyId) {
        try {
            log.info("--- [Background Thread] Sending email notification for: {} ---", jobTitle);

            Thread.sleep(5000);

            log.info("--- [Background Thread] Email sent to Company ID {} ---", companyId);
        } catch (InterruptedException e) {
            log.error("Error sending email notification", e);
        }
    }
}