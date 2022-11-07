package com.example.cyberycon.consumption.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class MethodTimer {

    private Logger logger = LoggerFactory.getLogger(MethodTimer.class);

    @Around("@annotation(LogElapsed)")
    public Object timeableMethodAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = new Date().getTime();
        Object result = joinPoint.proceed();
        long end = new Date().getTime();
        logger.info(String.format("Log elapsed %d", end - start));
        return result;
    }
}
