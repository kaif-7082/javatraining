package com.consumer.reciever.repository;

import com.consumer.reciever.model.SecurityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
public interface SecurityRepository extends JpaRepository<SecurityLog, Long> {
    long countByUserIdAndEventTypeAndTimestampAfter(String userId, String eventType, LocalDateTime timestamp);
}