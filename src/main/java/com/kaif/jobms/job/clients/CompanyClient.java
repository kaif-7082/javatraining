package com.kaif.jobms.job.clients;

import com.kaif.jobms.job.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// "companyms" matches the spring.application.name in Eureka
@FeignClient(name = "companyms")
public interface CompanyClient {

    @GetMapping("/companies/{id}")
    Company getCompany(@PathVariable("id") Long id);
}