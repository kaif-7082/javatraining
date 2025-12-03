package com.kaif.reviewms.review.dto;
import lombok.Data;

@Data
public class ReviewMessage {
    private Long id;
    private String description;
    private double rating;
    private Long companyId;
}