package com.xinx.product;

/**
 * @description: 枚举式
 * @author: JXIN
 */
public enum EnumPattern {

    // 枚举类的构造器只能是private修饰
    // 枚举项实质是静态常量 => public static final EnumPattern INSTANCE;
    // 每一个枚举项都是本类的实例
    INSTANCE;

    public void doSomething() {
        System.out.println("===通过枚举实现单例");
    }

}
