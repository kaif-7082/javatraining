package com.consumer.reciever.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ConsumerAspect {

    // Target ONLY methods with the @LogExecution annotation
    @Around("@annotation(com.consumer.reciever.aop.LogExecution)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        // 1. Run the method
        Object proceed = joinPoint.proceed();

        // 2. Calculate time
        long executionTime = System.currentTimeMillis() - start;

        // 3. Log it
        System.out.println(" [AOP] " + joinPoint.getSignature() + " executed in " + executionTime + "ms");

        return proceed;
    }
}