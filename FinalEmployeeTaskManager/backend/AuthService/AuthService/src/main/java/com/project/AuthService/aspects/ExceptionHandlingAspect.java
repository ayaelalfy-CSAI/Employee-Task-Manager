package com.project.AuthService.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ExceptionHandlingAspect {

    @Pointcut("execution(* com.project.AuthService.services..*(..))")
    public void serviceLayer() {}

    @Around("serviceLayer()")
    public Object handleExceptions(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Exception ex) {
            log.error(" Exception in {}(): {}", joinPoint.getSignature().getName(), ex.getMessage(), ex);

            throw ex;
        }
    }
}
