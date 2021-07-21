package com.xinx.product;

/**
 * @description: 静态内部类模式
 * @author: JXIN
 * @time: 2021/5/25 22:37
 */
public class InnerClassPattern {

    private InnerClassPattern() {

    }

    public static final InnerClassPattern getInstance() {
        return InstanceHandler.INSTANCE;
    }

    // 静态内部类
    private static class InstanceHandler {
        // 静态常量
        private static final InnerClassPattern INSTANCE = new InnerClassPattern();
    }

}
