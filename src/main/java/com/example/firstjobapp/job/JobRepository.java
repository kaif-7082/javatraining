package com.example.firstjobapp.job;

import com.example.firstjobapp.job.dto.LocationCount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByLocation(String location);

    List<Job> findByMinSalaryGreaterThan(Integer salary);

    @Query("SELECT j FROM Job j LEFT JOIN j.company c " +
            "WHERE j.title LIKE %:query% " +
            "OR j.description LIKE %:query% " +
            "OR j.location LIKE %:query% " +
            "OR c.name LIKE %:query%")
    List<Job> searchJobs(@Param("query") String query);

    @Query("SELECT new com.example.firstjobapp.job.dto.LocationCount(j.location, COUNT(j.location)) " +
            "FROM Job j GROUP BY j.location")
    List<LocationCount> getLocationCounts();
}
//we dont have to write any code for the repository.spring data jpa will automatically will generate implementation at runtime and we can use it in our application(service class) to interact with database