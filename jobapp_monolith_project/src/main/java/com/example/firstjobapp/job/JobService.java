package com.example.firstjobapp.job;

import com.example.firstjobapp.job.dto.LocationCount;
import com.example.firstjobapp.job.dto.createJobRequestDto;
import com.example.firstjobapp.job.dto.userResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JobService {



    List<userResponseDTO> findAll(Long companyId);

    void createJob(Long companyId, createJobRequestDto createRequest);

    userResponseDTO getJobById(Long companyId, Long jobId);

    boolean deleteJobById(Long companyId, Long jobId);

    boolean updateJob(Long companyId, Long jobId, createJobRequestDto updatedJob);

    List<Job> findjobswithSorting(Long companyId, String field);

    List<Job> findJobsByLocation(Long companyId, String location);

    List<Job> findJobsByMinSalaryGreaterThan(Long companyId, Integer salary);

    List<Job> searchJobs(Long companyId, String query);

    Page<Job> findJobsWithPagination(Long companyId, int page, int pageSize);


    List<LocationCount> getLocationCounts();
}