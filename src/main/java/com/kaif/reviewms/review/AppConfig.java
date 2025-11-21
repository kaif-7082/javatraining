package com.kaif.reviewms.review;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections; // <-- ADD THIS IMPORT

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