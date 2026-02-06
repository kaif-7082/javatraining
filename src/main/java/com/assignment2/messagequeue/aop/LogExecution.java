package com.assignment2.messagequeue.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // We can use this on Methods
@Retention(RetentionPolicy.RUNTIME) // Keep it alive while the app runs
public @interface LogExecution {
}