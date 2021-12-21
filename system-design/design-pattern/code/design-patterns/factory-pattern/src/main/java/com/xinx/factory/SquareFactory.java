package com.xinx.factory;

import com.xinx.product.Shape;
import com.xinx.product.Square;

/**
 * @description:
 * @author: JXIN
 */
public class SquareFactory implements ShapeFactory {

    public Shape getShape() {
        return new Square();
    }

}
