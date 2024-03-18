package com.example.product.infrastructure.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
class LogAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping) || "
        + "@annotation(org.springframework.web.bind.annotation.PostMapping) || "
        + "@annotation(org.springframework.web.bind.annotation.PutMapping) || "
        + "@annotation(org.springframework.web.bind.annotation.PatchMapping) || "
        + "@annotation(org.springframework.web.bind.annotation.DeleteMapping) ")
    void requestMapping() {
    }

    ;

    @Around("requestMapping()")
    Object controllerLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[REQUEST] {} - {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
        Object retVal = joinPoint.proceed();
        log.info("[RESPONSE] {} - {}", joinPoint.getSignature().getName(), retVal);
        return retVal;
    }

    @Around("@annotation(org.springframework.stereotype.Service)")
    Object serviceLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[SERVICE] {} START", joinPoint.getSignature().getName());
        Object retVal = joinPoint.proceed();
        log.info("[SERVICE] {} END", joinPoint.getSignature().getName());
        return retVal;
    }
}