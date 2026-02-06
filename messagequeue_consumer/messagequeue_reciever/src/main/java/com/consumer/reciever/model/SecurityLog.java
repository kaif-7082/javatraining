package com.consumer.reciever.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "security_logs")
@Data
@NoArgsConstructor
public class SecurityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String eventType;
    private LocalDateTime timestamp;

    public SecurityLog(String userId, String eventType, LocalDateTime timestamp) {
        this.userId = userId;
        this.eventType = eventType;
        this.timestamp = timestamp;
    }
}