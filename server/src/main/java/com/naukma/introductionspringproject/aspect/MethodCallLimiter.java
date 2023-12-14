package com.naukma.introductionspringproject.aspect;

import org.apache.commons.lang3.tuple.Pair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class MethodCallLimiter {
    private static final int MAX_LIMIT = 5;
    private final Map<String, Pair<Long, Integer>> methodCallCounts = new ConcurrentHashMap<>();

    @Around("execution(* com.naukma.introductionspringproject.service.impl.MealServiceImpl.getAllMeals())")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().toShortString();
        Pair<Long, Integer> callData = methodCallCounts.getOrDefault(methodName, Pair.of(System.currentTimeMillis(), 0));

        if (System.currentTimeMillis() - callData.getLeft() > 60000) {
            callData = Pair.of(System.currentTimeMillis(), 1);
        } else if (callData.getRight() >= MAX_LIMIT) {
            throw new RuntimeException("Method call limit reached, try again later");
        } else {
            callData = Pair.of(callData.getLeft(), callData.getRight() + 1);
        }

        methodCallCounts.put(methodName, callData);

        return joinPoint.proceed();
    }
}