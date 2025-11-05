package com.example.firstjobapp.job;

import com.example.firstjobapp.job.dto.LocationCount;
import com.example.firstjobapp.job.dto.createJobRequestDto;
import com.example.firstjobapp.job.dto.userResponseDTO;

import java.util.List;

public interface JobService {


    List<userResponseDTO> findAll();


    void createJob(createJobRequestDto createRequest);

    Job getJobById(Long id);

    boolean deleteJobById(Long id);


    boolean updateJob(Long id, createJobRequestDto updatedJob);


    List<Job> findjobswithSorting(String field);

    List<Job> findJobsByLocation(String location);
    List<Job> findJobsByMinSalaryGreaterThan(Integer salary);
    List<Job> searchJobs(String query);
    List<LocationCount> getLocationCounts();
}