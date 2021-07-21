package com.xinx.factory;

import com.xinx.common.Consts;
import com.xinx.factory.AbstractFactory;
import com.xinx.product.color.Color;
import com.xinx.product.color.Green;
import com.xinx.product.color.Red;
import com.xinx.product.shape.Shape;

/**
 * @description: 颜色工厂
 * @author: JXIN
 */
public class ColorFactory extends AbstractFactory {

    /**
     * 获取颜色实例
     * @param colorType
     * @return
     */
    @Override
    public Color getColor(String colorType) {
        Color color = null;

        if (Consts.COLOR_GREEN.equals(colorType)) {
            color = new Green();
        } else if (Consts.COLOR_RED.equals(colorType)) {
            color = new Red();
        }

        return color;
    }

    @Override
    public Shape getShape(String shapeType) {
        return null;
    }

}
