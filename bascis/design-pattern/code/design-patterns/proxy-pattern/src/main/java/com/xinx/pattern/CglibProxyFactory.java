package com.xinx.pattern;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description: Cglib 代理工厂
 * @author: JXIN
 * @time: 2021/6/24 22:13
 */
public class CglibProxyFactory implements MethodInterceptor {

    // 目标对象，必须是接口的实现对象
    private Object target;

    // 有参构造，传递目标对象
    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 实现的接口方法
     * @param o
     * @param method 方法
     * @param objects 方法参数
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        // 业务其他
        System.out.println("=== 准备中... ===");

        // 目标对象调用
        Object obj = method.invoke(target, objects);

        // 其他业务
        System.out.println("=== 结束... ===");

        return obj;
    }

    /**
     * 创建代理对象
     */
    public Object getProxyInstance(){

        // 工具类
        Enhancer en = new Enhancer();

        // 设置父类
        en.setSuperclass(target.getClass());

        // 设置回调函数
        en.setCallback(this);

        // 创建子类(代理对象)
        return en.create();

    }

}
