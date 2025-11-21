package com.kaif.jobms.job;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections; // <-- ADD THIS
//this file can be deleted if we are not using resttemplate
@Configuration
public class AppConfig {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // Add the interceptor
        restTemplate.setInterceptors(Collections.singletonList(new RequestInterceptor()));
        return restTemplate;
    }

    }
