package com.kaif.jobms.job;

import com.kaif.jobms.job.dto.LocationCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface JobRepository extends JpaRepository<Job, Long> {

    // Base find for a specific company
    List<Job> findByCompanyId(Long companyId);

    // For Sorting:
    List<Job> findByCompanyId(Long companyId, Sort sort);

    // For Pagination:
    Page<Job> findByCompanyId(Long companyId, Pageable pageable);

    // For Custom Filtering:
    List<Job> findByCompanyIdAndLocation(Long companyId, String location);


    List<Job> findByCompanyIdAndMinSalaryGreaterThan(Long companyId, Integer salary);


    @Query("SELECT j FROM Job j " +
            "WHERE j.companyId = :companyId AND (" +
            "j.title LIKE %:query% " +
            "OR j.description LIKE %:query% " +
            "OR j.location LIKE %:query%)")
    List<Job> searchJobsByCompany(@Param("companyId") Long companyId, @Param("query") String query);


    // For Global Stats
    @Query("SELECT new com.kaif.jobms.job.dto.LocationCount(j.location, COUNT(j.location)) " +
            "FROM Job j GROUP BY j.location")
    List<LocationCount> getLocationCounts();
}