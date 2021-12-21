import com.xinx.product.Shape;
import com.xinx.factory.ShapeFactory;
import com.xinx.factory.SquareFactory;

/**
 * @description:
 * @author: JXIN
 */
public class MyTest {

    public static void main(String[] args) {

        // 实例化正方形工厂
        ShapeFactory squareFactory = new SquareFactory();
        // 获取正方形实例
        Shape square = squareFactory.getShape();
        // 调用方法
        square.draw();

    }

}
