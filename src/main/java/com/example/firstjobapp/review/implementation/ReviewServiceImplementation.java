package com.example.firstjobapp.review.implementation;

import com.example.firstjobapp.companies.Company;
import com.example.firstjobapp.companies.CompanyRepository;
import com.example.firstjobapp.review.Review;
import com.example.firstjobapp.review.ReviewRepository;
import com.example.firstjobapp.review.ReviewService;
import com.example.firstjobapp.review.dto.reviewRequestDto;
import com.example.firstjobapp.review.dto.reviewResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReviewServiceImplementation implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyRepository companyRepository; // Injected to find the Company

    public ReviewServiceImplementation(ReviewRepository reviewRepository, CompanyRepository companyRepository) {
        this.reviewRepository = reviewRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<reviewResponseDto> getAllReviews(Long companyId) {
        log.info("Executing getAllReviews for companyId: {}", companyId);
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        List<reviewResponseDto> dtos = new ArrayList<>();
        for (Review review : reviews) {
            dtos.add(mapToDto(review));
        }
        log.info("Found {} reviews for companyId: {}", dtos.size(), companyId);
        return dtos;
    }

    @Override
    public boolean createReview(Long companyId, reviewRequestDto reviewDto) {
        log.info("Attempting to create review for company: {}", companyId);
        Optional<Company> companyOpt = companyRepository.findById(companyId);
        if (companyOpt.isEmpty()) {
            log.warn("Company not found: {}", companyId);
            return false;
        }

        Review review = mapToEntity(reviewDto);
        review.setCompany(companyOpt.get());
        reviewRepository.save(review);
        log.info("Review created successfully with id: {}", review.getId());
        return true;
    }

    @Override
    public reviewResponseDto getReviewById(Long companyId, Long reviewId) {
        log.info("Finding review {} for company {}", reviewId, companyId);
        // We fetch by companyId first to ensure the review belongs to the company
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        Optional<Review> reviewOpt = reviews.stream()
                .filter(r -> r.getId().equals(reviewId))
                .findFirst();

        if (reviewOpt.isEmpty()) {
            log.warn("Review not found: {} for company: {}", reviewId, companyId);
            return null;
        }
        return mapToDto(reviewOpt.get());
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, reviewRequestDto reviewDto) {
        log.info("Attempting to update review {} for company {}", reviewId, companyId);

        // Ensure company exists
        if (!companyRepository.existsById(companyId)) {
            log.warn("Company not found: {}", companyId);
            return false;
        }

        Optional<Review> reviewOpt = reviewRepository.findById(reviewId);
        if (reviewOpt.isEmpty()) {
            log.warn("Review not found: {}", reviewId);
            return false;
        }

        Review review = reviewOpt.get();
        // Authorize that this review belongs to the correct company
        if (!review.getCompany().getId().equals(companyId)) {
            log.warn("Review {} does not belong to company {}", reviewId, companyId);
            return false;
        }

        review.setTitle(reviewDto.getTitle());
        review.setDescription(reviewDto.getDescription());
        review.setRating(reviewDto.getRating());
        // Company association remains the same

        reviewRepository.save(review);
        log.info("Review updated successfully: {}", reviewId);
        return true;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        log.info("Attempting to delete review {} for company {}", reviewId, companyId);

        Optional<Review> reviewOpt = reviewRepository.findById(reviewId);
        if (reviewOpt.isEmpty()) {
            log.warn("Review not found: {}", reviewId);
            return false;
        }

        Review review = reviewOpt.get();
        // Authorize that this review belongs to the correct company
        if (!review.getCompany().getId().equals(companyId)) {
            log.warn("Review {} does not belong to company {}", reviewId, companyId);
            return false;
        }

        reviewRepository.delete(review);
        log.info("Review deleted successfully: {}", reviewId);
        return true;
    }

    private reviewResponseDto mapToDto(Review review) {
        reviewResponseDto dto = new reviewResponseDto();
        dto.setId(review.getId());
        dto.setTitle(review.getTitle());
        dto.setDescription(review.getDescription());
        dto.setRating(review.getRating());
        return dto;
    }

    private Review mapToEntity(reviewRequestDto dto) {
        Review review = new Review();
        review.setTitle(dto.getTitle());
        review.setDescription(dto.getDescription());
        review.setRating(dto.getRating());
        return review;
    }
}
