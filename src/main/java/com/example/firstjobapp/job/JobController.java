package com.example.firstjobapp.job;

import com.example.firstjobapp.job.dto.LocationCount;
import com.example.firstjobapp.job.dto.createJobRequestDto;
import com.example.firstjobapp.job.dto.userResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    @GetMapping
    public ResponseEntity<List<userResponseDTO>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }


    @GetMapping("/sorted/{field}")
    public ResponseEntity<List<Job>> findSortedJobs(@PathVariable String field) {
        List<Job> sortedJobs = jobService.findjobswithSorting(field);
        return ResponseEntity.ok(sortedJobs);
    }


    @PostMapping
    public ResponseEntity<String> createJob(@Valid @RequestBody createJobRequestDto createRequest) {
        jobService.createJob(createRequest);
        return new ResponseEntity<>("Job created", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        if (job != null)
            return new ResponseEntity<>(job, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        boolean deleted = jobService.deleteJobById(id);
        if (deleted)
            return new ResponseEntity<>("Job deleted", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @Valid @RequestBody createJobRequestDto updatedJob) {
        boolean updated = jobService.updateJob(id, updatedJob);
        if (updated)
            return new ResponseEntity<>("Job updated", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/location/{location}")
    public ResponseEntity<List<Job>> findJobsByLocation(@PathVariable String location) {
        List<Job> jobs = jobService.findJobsByLocation(location);
        return ResponseEntity.ok(jobs);
    }
    @GetMapping("/salary")
    public ResponseEntity<List<Job>> findJobsByMinSalaryGreaterThan(@RequestParam Integer min) {
        List<Job> jobs = jobService.findJobsByMinSalaryGreaterThan(min);
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Job>> searchJobs(@RequestParam String query) {
        List<Job> jobs = jobService.searchJobs(query);
        return ResponseEntity.ok(jobs);
    }
    @GetMapping("/stats/location-counts")
    public ResponseEntity<List<LocationCount>> getLocationCounts() {
        List<LocationCount> stats = jobService.getLocationCounts();
        return ResponseEntity.ok(stats);
    }
}