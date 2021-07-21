package com.xinx.pattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description: 动态代理处理类
 * @author: JXIN
 * @time: 2021/6/22 22:56
 */
public class DynamicProxyHandler implements InvocationHandler {

    // 目标对象，必须是接口的实现对象
    private Object target;

    // 有参构造，传递目标对象
    public DynamicProxyHandler(Object target) {
        this.target = target;
    }

    /**
     * 实现 InvocationHandler 接口方法
     * 动态生成的代理类继承了 DynamicProxyHandler 类，实现了 target（目标对象）所实现的接口与方法
     * 实现的方法中会调用父类的 invoke 方法（即此方法）
     * @param proxy
     * @param method 接口方法
     * @param args 方法参数
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 业务其他
        System.out.println("=== 准备中... ===");

        // 目标对象调用
        Object obj = method.invoke(target, args);

        // 其他业务
        System.out.println("=== 结束... ===");

        return obj;
    }

    /**
     * 获取代理对象
     * @return
     */
    public Object getProxyInstance() {

        if (target == null) {
            return null;
        }

        // 创建代理对象
        // ClassLoader loader表示当前使用到的appClassloader;
        // Class<?>[] interfaces表示目标对象实现的一组接口;
        // InvocationHandler h表示当前的InvocationHandler实例对象，此处用this即可
        Object proxyInstance = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);

        return proxyInstance;
    }

}
