package com.example.firstjobapp.job.implementation;

import com.example.firstjobapp.companies.Company;
import com.example.firstjobapp.companies.CompanyRepository;
import com.example.firstjobapp.job.Job;
import com.example.firstjobapp.job.JobRepository;
import com.example.firstjobapp.job.JobService;
import com.example.firstjobapp.job.dto.createJobRequestDto;
import com.example.firstjobapp.job.dto.userResponseDTO;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.firstjobapp.job.dto.LocationCount;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImplementation implements JobService {

    JobRepository jobRepository;
    CompanyRepository companyRepository;

    public JobServiceImplementation(JobRepository jobRepository, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<userResponseDTO> findAll() {
        List<userResponseDTO> userResponse=new ArrayList<userResponseDTO>();
        List<Job> jobs=jobRepository.findAll();
        for(Job job:jobs)
        {
            userResponseDTO response=new userResponseDTO();
            response.setId(job.getId());
            response.setTitle(job.getTitle());
            response.setMaxSalary(job.getMaxSalary());
            response.setLocation(job.getLocation());
            userResponse.add(response);
        }
        return userResponse;
    }

    // METHOD MODIFIED
    @Override
    public void createJob(createJobRequestDto createRequest) {
        Job job = new Job();
        job.setTitle(createRequest.getTitle());
        job.setDescription(createRequest.getDescription());
        job.setMinSalary(createRequest.getMinSalary());
        job.setMaxSalary(createRequest.getMaxSalary());
        job.setLocation(createRequest.getLocation());

        // LOGIC CHANGED: Now throws an error if company not found
        Company company = companyRepository.findById(createRequest.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + createRequest.getCompanyId()));

        job.setCompany(company); // Set the relationship

        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // METHOD MODIFIED
    @Override
    public boolean updateJob(Long id, createJobRequestDto updatedJob) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if (optionalJob.isPresent()) {
            Job job = optionalJob.get();

            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());

            // LOGIC CHANGED: Now throws an error if company not found
            Company company = companyRepository.findById(updatedJob.getCompanyId())
                    .orElseThrow(() -> new RuntimeException("Company not found with id: " + updatedJob.getCompanyId()));

            job.setCompany(company); // Set the relationship

            jobRepository.save(job);
            return  true;
        }
        return false;
    }

    @Override
    public List<Job> findjobswithSorting(String field) {
        return jobRepository.findAll(Sort.by(Sort.Direction.DESC,field));
    }

    @Override
    public List<Job> findJobsByLocation(String location) {
        return jobRepository.findByLocation(location);
    }

    @Override
    public List<Job> findJobsByMinSalaryGreaterThan(Integer salary) {
        return jobRepository.findByMinSalaryGreaterThan(salary);
    }

    @Override
    public List<Job> searchJobs(String query) {
        return jobRepository.searchJobs(query);
    }

    @Override
    public List<LocationCount> getLocationCounts() {
        return jobRepository.getLocationCounts();
    }
}