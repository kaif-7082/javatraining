package com.kaif.reviewms.review;

import com.kaif.reviewms.review.dto.ReviewRequestDto;
import com.kaif.reviewms.review.dto.ReviewResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.kaif.reviewms.review.ReviewConstants.*;

@Slf4j
@RestController
@RequestMapping("/reviews")
@Tag(name = "Review Controller", description = "Endpoints for managing reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Operation(summary = "Get All Reviews", description = "Fetches reviews. Can filter by Company ID.")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ReviewResponseDto>> getAllReviews(@RequestParam(required = false) Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @Operation(summary = "Create Review", description = "Posts a new review for a company. Validates Company ID via Feign.")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<String> createReview(@Valid @RequestBody ReviewRequestDto reviewDto) {
        reviewService.createReview(reviewDto);
        return new ResponseEntity<>("Review created successfully", HttpStatus.CREATED);
    }

    @Operation(summary = "Get Review by ID", description = "Fetches a specific review by its ID.")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(ID_PATH)
    public ResponseEntity<ReviewResponseDto> getReviewById(@PathVariable Long reviewId) {
        ReviewResponseDto reviewDto = reviewService.getReviewById(reviewId);
        if (reviewDto != null) return new ResponseEntity<>(reviewDto, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Update Review", description = "Updates review details. Requires ADMIN role.")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(ID_PATH)
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId, @Valid @RequestBody ReviewRequestDto reviewDto) {
        boolean updated = reviewService.updateReview(reviewId, reviewDto);
        if (updated) return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        return new ResponseEntity<>("Review not updated", HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Delete Review", description = "Deletes a review by its ID. Requires ADMIN role.")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(ID_PATH)
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        boolean deleted = reviewService.deleteReview(reviewId);
        if (deleted) return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        return new ResponseEntity<>("Review not deleted", HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Get Paginated Reviews", description = "Fetches reviews in pages.")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(PAGINATION_PATH)
    public ResponseEntity<Page<ReviewResponseDto>> getReviewsPaginated(@RequestParam Long companyId, @PathVariable int page, @PathVariable int pageSize) {
        return ResponseEntity.ok(reviewService.getReviewsPaginated(companyId, page, pageSize));
    }

    @Operation(summary = "Get Sorted Reviews", description = "Fetches reviews sorted by a specific field.")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(SORTED_PATH)
    public ResponseEntity<List<ReviewResponseDto>> getReviewsSorted(@RequestParam Long companyId, @PathVariable String field) {
        return ResponseEntity.ok(reviewService.getReviewsSorted(companyId, field));
    }

    @Operation(summary = "Filter Reviews by Rating", description = "Fetches reviews with a rating higher than specified.")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(FILTER_PATH)
    public ResponseEntity<List<ReviewResponseDto>> getReviewsByMinRating(@RequestParam Long companyId, @RequestParam double minRating) {
        return ResponseEntity.ok(reviewService.getReviewsByRatingGreaterThan(companyId, minRating));
    }

    @Operation(summary = "Get Average Rating", description = "Calculates average rating for a company. Used internally by CompanyMS.")
    @GetMapping(STATS_AVG_PATH)
    public ResponseEntity<Map<String, Double>> getAverageRating(@RequestParam Long companyId) {
        Double avgRating = reviewService.getAverageRating(companyId);
        return new ResponseEntity<>(Map.of("averageRating", avgRating), HttpStatus.OK);
    }
}