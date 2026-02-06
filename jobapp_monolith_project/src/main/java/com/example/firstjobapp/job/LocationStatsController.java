package com.example.firstjobapp.job;

import com.example.firstjobapp.job.dto.LocationCount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/stats")
public class LocationStatsController {

    private final JobService jobService;

    public LocationStatsController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/location-counts")
    public ResponseEntity<List<LocationCount>> getLocationCounts() {
        log.info("GET /stats/location-counts - Request to get global location stats");
        List<LocationCount> stats = jobService.getLocationCounts();
        return ResponseEntity.ok(stats);
    }
}