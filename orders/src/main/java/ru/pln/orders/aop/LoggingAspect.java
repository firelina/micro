package ru.pln.orders.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Pointcut("execution(public * ru.pln.orders.service.OrderService.*(..))")
    public void orderServices(){}
    @Before("orderServices()")
    public void aopDoSomething(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Вызывается метод из OrderService " + methodSignature);
        Object[] args = joinPoint.getArgs();
        if (args.length > 0){
            System.out.println("Аргументы");
            for (Object o: args){
                System.out.println(o);
            }
        }

    }
    @Around("orderServices()")
    public Object aopDoSomethingAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Start profiling");
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        System.out.println("duration of " + proceedingJoinPoint.getSignature() + " is " + duration);
        System.out.println("Finish profiling");
        return out;
    }
}
