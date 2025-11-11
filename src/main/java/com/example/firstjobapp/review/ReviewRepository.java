package com.example.firstjobapp.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCompanyId(Long companyId);

    // For Sorting:
    List<Review> findByCompanyId(Long companyId, Sort sort);

    // For Pagination:
    Page<Review> findByCompanyId(Long companyId, Pageable pageable);

    // For Custom Filtering:
    List<Review> findByCompanyIdAndRatingGreaterThan(Long companyId, double rating);
    List<Review> findByCompanyIdAndRating(Long companyId, double rating);


    // For Custom Stats:
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.company.id = :companyId")
    Double findAverageRatingByCompanyId(@Param("companyId") Long companyId);
}