package com.xinx.factory;

import com.xinx.product.Circle;
import com.xinx.product.Shape;

/**
 * @description:
 * @author: JXIN
 */
public class CircleFactory implements ShapeFactory {

    public Shape getShape() {
        return new Circle();
    }

}
