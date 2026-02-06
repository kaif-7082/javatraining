package com.example.firstjobapp.job;

import com.example.firstjobapp.job.dto.createJobRequestDto;
import com.example.firstjobapp.job.dto.userResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/companies/{companyId}/jobs")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    @GetMapping
    public ResponseEntity<List<userResponseDTO>> findAll(@PathVariable Long companyId) {
        log.info("GET /companies/{}/jobs", companyId);
        return ResponseEntity.ok(jobService.findAll(companyId));
    }


    @GetMapping("/sorted/{field}")
    public ResponseEntity<List<Job>> findSortedJobs(@PathVariable Long companyId, @PathVariable String field) {
        log.info("GET /companies/{}/jobs/sorted/{}", companyId, field);
        List<Job> sortedJobs = jobService.findjobswithSorting(companyId, field);
        return ResponseEntity.ok(sortedJobs);
    }


    @PostMapping
    public ResponseEntity<String> createJob(@PathVariable Long companyId, @Valid @RequestBody createJobRequestDto createRequest) {
        log.info("POST /companies/{}/jobs - Request to create new job: {}", companyId, createRequest.getTitle());
        jobService.createJob(companyId, createRequest);
        log.info("POST /companies/{}/jobs - Job created successfully", companyId);
        return new ResponseEntity<>("Job created", HttpStatus.CREATED);
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<userResponseDTO> findJobById(@PathVariable Long companyId, @PathVariable Long jobId) {
        log.info("GET /companies/{}/jobs/{} - Request to get job by id", companyId, jobId);
        userResponseDTO jobDto = jobService.getJobById(companyId, jobId);
        if (jobDto != null)
            return new ResponseEntity<>(jobDto, HttpStatus.OK);

        log.warn("GET /companies/{}/jobs/{} - Job not found", companyId, jobId);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long companyId, @PathVariable Long jobId) {
        log.info("DELETE /companies/{}/jobs/{} - Request to delete job", companyId, jobId);
        boolean deleted = jobService.deleteJobById(companyId, jobId);
        if (deleted) {
            log.info("DELETE /companies/{}/jobs/{} - Job deleted successfully", companyId, jobId);
            return new ResponseEntity<>("Job deleted", HttpStatus.OK);
        }
        log.warn("DELETE /companies/{}/jobs/{} - Job not found", companyId, jobId);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/{jobId}")
    public ResponseEntity<String> updateJob(@PathVariable Long companyId, @PathVariable Long jobId, @Valid @RequestBody createJobRequestDto updatedJob) {
        log.info("PUT /companies/{}/jobs/{} - Request to update job", companyId, jobId);
        boolean updated = jobService.updateJob(companyId, jobId, updatedJob);
        if (updated) {
            log.info("PUT /companies/{}/jobs/{} - Job updated successfully", companyId, jobId);
            return new ResponseEntity<>("Job updated", HttpStatus.OK);
        }
        log.warn("PUT /companies/{}/jobs/{} - Job not found", companyId, jobId);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<Job>> findJobsByLocation(@PathVariable Long companyId, @PathVariable String location) {
        log.info("GET /companies/{}/jobs/location/{} - Request to find jobs by location", companyId, location);
        List<Job> jobs = jobService.findJobsByLocation(companyId, location);
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/salary")
    public ResponseEntity<List<Job>> findJobsByMinSalaryGreaterThan(@PathVariable Long companyId, @RequestParam Integer min) {
        log.info("GET /companies/{}/jobs/salary - Request to find jobs by min salary > {}", companyId, min);
        List<Job> jobs = jobService.findJobsByMinSalaryGreaterThan(companyId, min);
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Job>> searchJobs(@PathVariable Long companyId, @RequestParam String query) {
        log.info("GET /companies/{}/jobs/search - Request to search jobs with query: {}", companyId, query);
        List<Job> jobs = jobService.searchJobs(companyId, query);
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/pagination/{page}/{pageSize}")
    public ResponseEntity<Page<Job>> getJobsWithPagination(@PathVariable Long companyId, @PathVariable int page, @PathVariable int pageSize) {
        log.info("GET /companies/{}/jobs/pagination/{}/{} - Request to get paginated jobs", companyId, page, pageSize);
        Page<Job> jobs = jobService.findJobsWithPagination(companyId, page, pageSize);
        return ResponseEntity.ok(jobs);
    }


}