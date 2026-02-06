package com.example.firstjobapp.review;

import com.example.firstjobapp.review.dto.reviewRequestDto;
import com.example.firstjobapp.review.dto.reviewResponseDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/companies/{companyId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<reviewResponseDto>> getAllReviews(@PathVariable Long companyId) {
        log.info("GET /companies/{}/reviews", companyId);
        List<reviewResponseDto> reviews = reviewService.getAllReviews(companyId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createReview(@PathVariable Long companyId,
                                               @Valid @RequestBody reviewRequestDto reviewDto) {
        log.info("POST /companies/{}/reviews - Request to create review: {}", companyId, reviewDto.getTitle());
        boolean created = reviewService.createReview(companyId, reviewDto);
        if (created) {
            log.info("POST /companies/{}/reviews - Review created successfully", companyId);
            return new ResponseEntity<>("Review created successfully", HttpStatus.CREATED);
        }
        log.warn("POST /companies/{}/reviews - Company not found", companyId);
        return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<reviewResponseDto> getReviewById(@PathVariable Long companyId,
                                                           @PathVariable Long reviewId) {
        log.info("GET /companies/{}/reviews/{}", companyId, reviewId);
        reviewResponseDto reviewDto = reviewService.getReviewById(companyId, reviewId);
        if (reviewDto != null) {
            return new ResponseEntity<>(reviewDto, HttpStatus.OK);
        }
        log.warn("GET /companies/{}/reviews/{} - Review not found", companyId, reviewId);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId,
                                               @Valid @RequestBody reviewRequestDto reviewDto) {
        log.info("PUT /companies/{}/reviews/{} - Request to update review", companyId, reviewId);
        boolean updated = reviewService.updateReview(companyId, reviewId, reviewDto);
        if (updated) {
            log.info("PUT /companies/{}/reviews/{} - Review updated successfully", companyId, reviewId);
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        }
        log.warn("PUT /companies/{}/reviews/{} - Review or Company not found", companyId, reviewId);
        return new ResponseEntity<>("Review not updated", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId) {
        log.info("DELETE /companies/{}/reviews/{} - Request to delete review", companyId, reviewId);
        boolean deleted = reviewService.deleteReview(companyId, reviewId);
        if (deleted) {
            log.info("DELETE /companies/{}/reviews/{} - Review deleted successfully", companyId, reviewId);
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        }
        log.warn("DELETE /companies/{}/reviews/{} - Review not found or does not belong to company", companyId, reviewId);
        return new ResponseEntity<>("Review not deleted", HttpStatus.NOT_FOUND);
    }
    @GetMapping("/pagination/{page}/{pageSize}")
    public ResponseEntity<Page<reviewResponseDto>> getReviewsPaginated(@PathVariable Long companyId,
                                                                       @PathVariable int page,
                                                                       @PathVariable int pageSize) {
        log.info("GET /companies/{}/reviews/pagination/{}/{}", companyId, page, pageSize);
        Page<reviewResponseDto> reviewPage = reviewService.getReviewsPaginated(companyId, page, pageSize);
        return new ResponseEntity<>(reviewPage, HttpStatus.OK);
    }

    @GetMapping("/sorted/{field}")
    public ResponseEntity<List<reviewResponseDto>> getReviewsSorted(@PathVariable Long companyId,
                                                                    @PathVariable String field) {
        log.info("GET /companies/{}/reviews/sorted/{}", companyId, field);
        List<reviewResponseDto> sortedReviews = reviewService.getReviewsSorted(companyId, field);
        return new ResponseEntity<>(sortedReviews, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<reviewResponseDto>> getReviewsByMinRating(@PathVariable Long companyId,
                                                                         @RequestParam double minRating) {
        log.info("GET /companies/{}/reviews/filter?minRating={}", companyId, minRating);
        List<reviewResponseDto> reviews = reviewService.getReviewsByRatingGreaterThan(companyId, minRating);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/stats/average-rating")
    public ResponseEntity<Map<String, Double>> getAverageRating(@PathVariable Long companyId) {
        log.info("GET /companies/{}/reviews/stats/average-rating", companyId);
        Double avgRating = reviewService.getAverageRating(companyId);
        return new ResponseEntity<>(Map.of("averageRating", avgRating), HttpStatus.OK);
    }
}
