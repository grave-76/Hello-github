package com.xinx.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: JXIN
 * @time: 2021/6/20 17:34
 */
@Aspect // 标注该类为切面
@Component
public class AnnotationPointcut {

    @Before("execution(* com.xinx.service.impl.UserServiceImpl.*(..))")
    public void MyBefore() {

        System.out.println("=== 前置方法 ===");
    }

    @After("execution(* com.xinx.service.impl.UserServiceImpl.*(..))")
    public void MyAfter() {

        System.out.println("=== 后置方法 ===");
    }

    /**
     * 使用环绕通知时，可以给定一个连接点参数，可获取处理切入的点
     */
    @Around("execution(* com.xinx.service.impl.UserServiceImpl.*(..))")
    public void MyAround(ProceedingJoinPoint jp) throws Throwable {

        System.out.println("环绕前...");

        // 执行方法
        Object obj = jp.proceed();

        System.out.println("环绕后...");
    }

}
