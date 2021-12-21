import com.xinx.product.EnumPattern;
import com.xinx.product.LazyPattern;
import org.junit.Test;

/**
 * @description:
 * @author: JXIN
 * @time: 2021/5/25 22:12
 */
public class MyTest {

    public static void main(String[] args) {

        LazyPattern lazyPattern = LazyPattern.getInstance();

        EnumPattern.INSTANCE.doSomething();

    }

    @Test
    public void test() {
        EnumPattern.INSTANCE.doSomething();
    }

}
