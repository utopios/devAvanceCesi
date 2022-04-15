package com.cesi.apireservation.aspect;

import com.cesi.apireservation.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LogAspect {

    @Autowired
    private LogService logService;

    @Pointcut("execution(public * com.cesi.apireservation.service.*.*(..))")
    public void methodCall() {}

    @AfterThrowing(pointcut = "methodCall()", throwing = "e")
    public void log(JoinPoint joinPoint, Throwable e) throws Exception {
        logService.Log(joinPoint.toShortString(), Arrays.asList(joinPoint.getArgs()), e.getMessage());
    }
}
