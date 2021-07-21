package com.xinx.demo;

/**
 * @description:
 * @author: JXIN
 */
public abstract class Shape implements Cloneable {

    // 画一个图形
    public abstract void draw();

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
