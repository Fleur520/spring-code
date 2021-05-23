package com.zm.anno.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
@ComponentScan(basePackages = "com.zm.anno")
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MyAspect {


    @Around("execution (* login(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("aspectj  log ======");

        Object ret = joinPoint.proceed();

        return ret;



    }



}
