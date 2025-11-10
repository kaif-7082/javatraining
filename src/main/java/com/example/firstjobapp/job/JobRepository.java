package com.example.firstjobapp.job;

import com.example.firstjobapp.job.dto.LocationCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface JobRepository extends JpaRepository<Job, Long> {


    List<Job> findByCompanyId(Long companyId);

    List<Job> findByCompanyId(Long companyId, Sort sort);

    Page<Job> findByCompanyId(Long companyId, Pageable pageable);

    List<Job> findByCompanyIdAndLocation(Long companyId, String location);

    List<Job> findByCompanyIdAndMinSalaryGreaterThan(Long companyId, Integer salary);


    @Query("SELECT j FROM Job j LEFT JOIN j.company c " +
            "WHERE j.company.id = :companyId AND (" +
            "j.title LIKE %:query% " +
            "OR j.description LIKE %:query% " +
            "OR j.location LIKE %:query% " +
            "OR c.name LIKE %:query%)")
    List<Job> searchJobsByCompany(@Param("companyId") Long companyId, @Param("query") String query);



    @Query("SELECT new com.example.firstjobapp.job.dto.LocationCount(j.location, COUNT(j.location)) " +
            "FROM Job j GROUP BY j.location")
    List<LocationCount> getLocationCounts();
}