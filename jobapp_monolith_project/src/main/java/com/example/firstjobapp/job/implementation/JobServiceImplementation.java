package com.example.firstjobapp.job.implementation;

import com.example.firstjobapp.companies.Company;
import com.example.firstjobapp.companies.CompanyRepository;
import com.example.firstjobapp.job.Job;
import com.example.firstjobapp.job.JobRepository;
import com.example.firstjobapp.job.JobService;
import com.example.firstjobapp.job.dto.createJobRequestDto;
import com.example.firstjobapp.job.dto.userResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.firstjobapp.job.dto.LocationCount;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JobServiceImplementation implements JobService {

    JobRepository jobRepository;
    CompanyRepository companyRepository;

    public JobServiceImplementation(JobRepository jobRepository, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }


    @Override
    public List<userResponseDTO> findAll(Long companyId) {
        log.info("Executing findAll jobs for companyId: {}", companyId);
        List<Job> jobs = jobRepository.findByCompanyId(companyId);
        log.info("Found {} jobs for company: {}", jobs.size(), companyId);
        return jobs.stream().map(this::mapToUserResponseDto).collect(Collectors.toList());
    }


    @Override
    public void createJob(Long companyId, createJobRequestDto createRequest) {
        log.info("Attempting to create job for company: {}", companyId);
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + companyId));

        Job job = mapToEntity(createRequest);
        job.setCompany(company); // Set the relationship from path variable

        jobRepository.save(job);
        log.info("Successfully created job with id: {}", job.getId());
    }

    @Override
    public userResponseDTO getJobById(Long companyId, Long jobId) {
        log.info("Finding job by id: {} for company: {}", jobId, companyId);
        Job job = jobRepository.findById(jobId)
                .orElse(null);

        if (job == null || !job.getCompany().getId().equals(companyId)) {
            log.warn("Job not found: {} for company: {}", jobId, companyId);
            return null;
        }
        return mapToUserResponseDto(job);
    }

    @Override
    public boolean deleteJobById(Long companyId, Long jobId) {
        log.info("Attempting to delete job with id: {} for company: {}", jobId, companyId);

        Optional<Job> jobOpt = jobRepository.findById(jobId);
        if (jobOpt.isEmpty()) {
            log.warn("Job not found: {}", jobId);
            return false;
        }

        Job job = jobOpt.get();
        if (!job.getCompany().getId().equals(companyId)) {
            log.warn("Job {} does not belong to company {}", jobId, companyId);
            return false;
        }

        jobRepository.delete(job);
        log.info("Successfully deleted job with id: {}", jobId);
        return true;
    }


    @Override
    public boolean updateJob(Long companyId, Long jobId, createJobRequestDto updatedJob) {
        log.info("Attempting to update job with id: {} for company: {}", jobId, companyId);

        Optional<Job> jobOpt = jobRepository.findById(jobId);
        if (jobOpt.isEmpty()) {
            log.warn("Job not found: {}", jobId);
            return false;
        }

        Job job = jobOpt.get();
        if (!job.getCompany().getId().equals(companyId)) {
            log.warn("Job {} does not belong to company {}", jobId, companyId);
            return false;
        }

        // Update fields
        job.setTitle(updatedJob.getTitle());
        job.setDescription(updatedJob.getDescription());
        job.setMinSalary(updatedJob.getMinSalary());
        job.setMaxSalary(updatedJob.getMaxSalary());
        job.setLocation(updatedJob.getLocation());
        // Company relationship remains unchanged

        jobRepository.save(job);
        log.info("Successfully updated job with id: {}", jobId);
        return true;
    }

    @Override
    public List<Job> findjobswithSorting(Long companyId, String field) {
        log.info("Finding jobs for company {} with sorting on field: {}", companyId, field);
        Sort sort = Sort.by(Sort.Direction.DESC, field);
        return jobRepository.findByCompanyId(companyId, sort);
    }

    @Override
    public List<Job> findJobsByLocation(Long companyId, String location) {
        log.info("Finding jobs for company {} by location: {}", companyId, location);
        return jobRepository.findByCompanyIdAndLocation(companyId, location);
    }

    @Override
    public List<Job> findJobsByMinSalaryGreaterThan(Long companyId, Integer salary) {
        log.info("Finding jobs for company {} with min salary > {}", companyId, salary);
        return jobRepository.findByCompanyIdAndMinSalaryGreaterThan(companyId, salary);
    }

    @Override
    public List<Job> searchJobs(Long companyId, String query) {
        log.info("Searching jobs for company {} with query: {}", companyId, query);
        return jobRepository.searchJobsByCompany(companyId, query);
    }

    @Override
    public Page<Job> findJobsWithPagination(Long companyId, int page, int pageSize) {
        log.info("Finding jobs for company {} with pagination - page: {}, pageSize: {}", companyId, page, pageSize);
        Pageable pageable = PageRequest.of(page, pageSize);
        return jobRepository.findByCompanyId(companyId, pageable);
    }


    @Override
    public List<LocationCount> getLocationCounts() {
        log.info("Getting global location stats");
        return jobRepository.getLocationCounts();
    }



    private userResponseDTO mapToUserResponseDto(Job job) {
        userResponseDTO response = new userResponseDTO();
        response.setId(job.getId());
        response.setTitle(job.getTitle());
        response.setMaxSalary(job.getMaxSalary());
        response.setLocation(job.getLocation());
        return response;
    }

    private Job mapToEntity(createJobRequestDto createRequest) {
        Job job = new Job();
        job.setTitle(createRequest.getTitle());
        job.setDescription(createRequest.getDescription());
        job.setMinSalary(createRequest.getMinSalary());
        job.setMaxSalary(createRequest.getMaxSalary());
        job.setLocation(createRequest.getLocation());
        return job;
    }
}