package com.kaif.reviewms.review.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "companyms", configuration = FeignClientConfig.class)
public interface CompanyClient {

    @GetMapping("/companies/{id}")
    void getCompany(@PathVariable("id") Long id);
}