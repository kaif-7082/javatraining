package com.kaif.jobms.job;

import com.kaif.jobms.job.dto.JobDTO;
import com.kaif.jobms.job.dto.LocationCount;
import com.kaif.jobms.job.dto.createJobRequestDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
@Slf4j
@RestController
@RequestMapping("/jobs")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<JobDTO>> findAll(@RequestParam(required = false) Long companyId) {
        log.info("GET /jobs?companyId={}", companyId);
        return ResponseEntity.ok(jobService.findAll(companyId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<String> createJob(@Valid @RequestBody createJobRequestDto createRequest) {
        log.info("POST /jobs - Request to create new job: {}", createRequest.getTitle());
        jobService.createJob(createRequest);
        log.info("POST /jobs - Job created successfully");
        return new ResponseEntity<>("Job created", HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{jobId}")
    public ResponseEntity<JobDTO> findJobById(@PathVariable Long jobId) {
        log.info("GET /jobs/{} - Request to get job by id", jobId);
        JobDTO jobDto = jobService.getJobById(jobId);
        if (jobDto != null)
            return new ResponseEntity<>(jobDto, HttpStatus.OK);

        log.warn("GET /jobs/{} - Job not found", jobId);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{jobId}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long jobId) {
        log.info("DELETE /jobs/{} - Request to delete job", jobId);
        // Note: Monolith had companyId auth. In a microservice, this might be
        // handled by an API Gateway passing user roles, or we just delete.
        // Keeping it simple as per your jobms code.
        boolean deleted = jobService.deleteJobById(jobId);
        if (deleted) {
            log.info("DELETE /jobs/{} - Job deleted successfully", jobId);
            return new ResponseEntity<>("Job deleted", HttpStatus.OK);
        }
        log.warn("DELETE /jobs/{} - Job not found", jobId);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{jobId}")
    public ResponseEntity<String> updateJob(@PathVariable Long jobId, @Valid @RequestBody createJobRequestDto updatedJob) {
        log.info("PUT /jobs/{} - Request to update job", jobId);
        boolean updated = jobService.updateJob(jobId, updatedJob);
        if (updated) {
            log.info("PUT /jobs/{} - Job updated successfully", jobId);
            return new ResponseEntity<>("Job updated", HttpStatus.OK);
        }
        log.warn("PUT /jobs/{} - Job not found", jobId);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // --- Endpoints from Monolith ---

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/sorted/{field}")
    public ResponseEntity<List<Job>> findSortedJobs(@RequestParam(required = false) Long companyId, @PathVariable String field) {
        log.info("GET /jobs/sorted/{}?companyId={}", field, companyId);
        List<Job> sortedJobs = jobService.findjobswithSorting(companyId, field);
        return ResponseEntity.ok(sortedJobs);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/location/{location}")
    public ResponseEntity<List<Job>> findJobsByLocation(@RequestParam(required = false) Long companyId, @PathVariable String location) {
        log.info("GET /jobs/location/{}?companyId={} - Request to find jobs by location", location, companyId);
        List<Job> jobs = jobService.findJobsByLocation(companyId, location);
        return ResponseEntity.ok(jobs);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/salary")
    public ResponseEntity<List<Job>> findJobsByMinSalaryGreaterThan(@RequestParam(required = false) Long companyId, @RequestParam Integer min) {
        log.info("GET /jobs/salary?min={}&companyId={} - Request to find jobs by min salary", min, companyId);
        List<Job> jobs = jobService.findJobsByMinSalaryGreaterThan(companyId, min);
        return ResponseEntity.ok(jobs);
    }
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/search")
    public ResponseEntity<List<Job>> searchJobs(@RequestParam Long companyId, @RequestParam String query) {
        log.info("GET /jobs/search?query={}&companyId={} - Request to search jobs", query, companyId);
        // This search is company-specific, so companyId is required
        List<Job> jobs = jobService.searchJobs(companyId, query);
        return ResponseEntity.ok(jobs);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/pagination/{page}/{pageSize}")
    public ResponseEntity<Page<Job>> getJobsWithPagination(@RequestParam(required = false) Long companyId, @PathVariable int page, @PathVariable int pageSize) {
        log.info("GET /jobs/pagination/{}/{}?companyId={} - Request to get paginated jobs", page, pageSize, companyId);
        Page<Job> jobs = jobService.findJobsWithPagination(companyId, page, pageSize);
        return ResponseEntity.ok(jobs);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/stats/location-counts")
    public ResponseEntity<List<LocationCount>> getLocationCounts() {
        log.info("GET /jobs/stats/location-counts - Request to get global location stats");
        List<LocationCount> stats = jobService.getLocationCounts();
        return ResponseEntity.ok(stats);
    }
}