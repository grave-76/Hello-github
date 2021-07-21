package com.xinx.aop;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: JXIN
 * @time: 2021/6/20 16:51
 */
public class MyLog implements MethodBeforeAdvice, AfterReturningAdvice {

    /**
     * MyLog 为一个aspect（切面）
     * 下述方法为advice（通知）
     */

    // 执行前
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("开始执行方法：" + method.getName());
    }

    // 执行后
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("执行完方法：" + method.getName());
        System.out.println("返回值：" + o);
    }

}
