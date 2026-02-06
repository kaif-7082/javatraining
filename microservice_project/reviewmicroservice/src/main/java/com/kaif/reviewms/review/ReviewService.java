package com.kaif.reviewms.review;

import com.kaif.reviewms.review.dto.ReviewRequestDto;
import com.kaif.reviewms.review.dto.ReviewResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewService {

    List<ReviewResponseDto> getAllReviews(Long companyId);

    boolean createReview(ReviewRequestDto reviewDto);

    ReviewResponseDto getReviewById(Long reviewId);

    boolean updateReview(Long reviewId, ReviewRequestDto reviewDto);

    boolean deleteReview(Long reviewId);

    Page<ReviewResponseDto> getReviewsPaginated(Long companyId, int page, int pageSize);

    List<ReviewResponseDto> getReviewsSorted(Long companyId, String field);

    List<ReviewResponseDto> getReviewsByRatingGreaterThan(Long companyId, double minRating);

    Double getAverageRating(Long companyId);
}