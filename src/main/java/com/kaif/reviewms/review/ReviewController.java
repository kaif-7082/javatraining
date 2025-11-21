package com.kaif.reviewms.review;

import com.kaif.reviewms.review.dto.ReviewRequestDto;
import com.kaif.reviewms.review.dto.ReviewResponseDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize; // <-- ADD IMPORT

@Slf4j
@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ReviewResponseDto>> getAllReviews(@RequestParam(required = false) Long companyId) {
        log.info("GET /reviews?companyId={}", companyId);
        List<ReviewResponseDto> reviews = reviewService.getAllReviews(companyId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    // A USER or an ADMIN can create a review
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<String> createReview(@Valid @RequestBody ReviewRequestDto reviewDto) {
        log.info("POST /reviews - Request to create review: {}", reviewDto.getTitle());
        boolean created = reviewService.createReview(reviewDto);
        // Exception handling will catch CompanyNotFoundException
        log.info("POST /reviews - Review created successfully");
        return new ResponseEntity<>("Review created successfully", HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDto> getReviewById(@PathVariable Long reviewId) {
        log.info("GET /reviews/{}", reviewId);
        ReviewResponseDto reviewDto = reviewService.getReviewById(reviewId);
        if (reviewDto != null) {
            return new ResponseEntity<>(reviewDto, HttpStatus.OK);
        }
        log.warn("GET /reviews/{} - Review not found", reviewId);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Only ADMIN can update any review
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,
                                               @Valid @RequestBody ReviewRequestDto reviewDto) {
        log.info("PUT /reviews/{} - Request to update review", reviewId);
        boolean updated = reviewService.updateReview(reviewId, reviewDto);
        if (updated) {
            log.info("PUT /reviews/{} - Review updated successfully", reviewId);
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        }
        log.warn("PUT /reviews/{} - Review not found", reviewId);
        return new ResponseEntity<>("Review not updated", HttpStatus.NOT_FOUND);
    }

    // Only ADMIN can delete any review
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        log.info("DELETE /reviews/{} - Request to delete review", reviewId);
        boolean deleted = reviewService.deleteReview(reviewId);
        if (deleted) {
            log.info("DELETE /reviews/{} - Review deleted successfully", reviewId);
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        }
        log.warn("DELETE /reviews/{} - Review not found", reviewId);
        return new ResponseEntity<>("Review not deleted", HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/pagination/{page}/{pageSize}")
    public ResponseEntity<Page<ReviewResponseDto>> getReviewsPaginated(@RequestParam Long companyId,
                                                                       @PathVariable int page,
                                                                       @PathVariable int pageSize) {
        log.info("GET /reviews/pagination/{}/{}?companyId={}", page, pageSize, companyId);
        Page<ReviewResponseDto> reviewPage = reviewService.getReviewsPaginated(companyId, page, pageSize);
        return new ResponseEntity<>(reviewPage, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/sorted/{field}")
    public ResponseEntity<List<ReviewResponseDto>> getReviewsSorted(@RequestParam Long companyId,
                                                                    @PathVariable String field) {
        log.info("GET /reviews/sorted/{}?companyId={}", field, companyId);
        List<ReviewResponseDto> sortedReviews = reviewService.getReviewsSorted(companyId, field);
        return new ResponseEntity<>(sortedReviews, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/filter")
    public ResponseEntity<List<ReviewResponseDto>> getReviewsByMinRating(@RequestParam Long companyId,
                                                                         @RequestParam double minRating) {
        log.info("GET /reviews/filter?minRating={}&companyId={}", minRating, companyId);
        List<ReviewResponseDto> reviews = reviewService.getReviewsByRatingGreaterThan(companyId, minRating);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/stats/average-rating")
    public ResponseEntity<Map<String, Double>> getAverageRating(@RequestParam Long companyId) {
        log.info("GET /reviews/stats/average-rating?companyId={}", companyId);
        Double avgRating = reviewService.getAverageRating(companyId);
        return new ResponseEntity<>(Map.of("averageRating", avgRating), HttpStatus.OK);
    }
}