package com.kaif.companyms.companies.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@FeignClient(name = "reviewms")
public interface ReviewClient {
    @GetMapping("/reviews/stats/average-rating")
    Map<String, Double> getAverageRating(@RequestParam("companyId") Long companyId);
}