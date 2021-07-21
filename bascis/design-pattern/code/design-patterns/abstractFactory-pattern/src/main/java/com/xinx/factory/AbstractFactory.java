package com.xinx.factory;

import com.xinx.product.color.Color;
import com.xinx.product.shape.Shape;

/**
 * @description: 抽象工厂
 * @author: JXIN
 */
public abstract class AbstractFactory {

    // 获取形状
    public abstract Shape getShape(String shapeType);
    // 获取颜色
    public abstract Color getColor(String colorType);

}
