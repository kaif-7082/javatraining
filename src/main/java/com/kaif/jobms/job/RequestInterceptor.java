package com.kaif.jobms.job;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;

//this can be deleted if we are not using resttemplate
public class RequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        // Get the current request context
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes != null) {
            // Get the original request from the user
            HttpServletRequest originalRequest = attributes.getRequest();

            // Get the Authorization header from the original request
            String token = originalRequest.getHeader(HttpHeaders.AUTHORIZATION);

            if (token != null) {
                // Add that header to the new request (the RestTemplate one)
                request.getHeaders().add(HttpHeaders.AUTHORIZATION, token);
            }
        }

        // Continue with the RestTemplate execution
        return execution.execute(request, body);
    }
}