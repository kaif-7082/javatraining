package com.example.firstjobapp.review;

import com.example.firstjobapp.review.dto.reviewRequestDto;
import com.example.firstjobapp.review.dto.reviewResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewService {
    List<reviewResponseDto> getAllReviews(Long companyId);

    boolean createReview(Long companyId, reviewRequestDto reviewDto);

    reviewResponseDto getReviewById(Long companyId, Long reviewId);

    boolean updateReview(Long companyId, Long reviewId, reviewRequestDto reviewDto);

    boolean deleteReview(Long companyId, Long reviewId);
}