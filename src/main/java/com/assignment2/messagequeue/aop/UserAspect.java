package com.assignment2.messagequeue.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAspect {

    // "Run this ONLY for methods that have the @LogExecution sticker"
    @Around("@annotation(com.assignment2.messagequeue.aop.LogExecution)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        // 1. Run the actual method
        Object proceed = joinPoint.proceed();

        // 2. Calculate time
        long executionTime = System.currentTimeMillis() - start;

        // 3. Log it
        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");

        return proceed;
    }
}