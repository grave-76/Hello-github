import com.xinx.factory.Company;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: JXIN
 * @time: 2021/5/25 21:26
 */
public class MyTest2 {

    @Test
    public void test() {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Company company1 = context.getBean("company1", Company.class);
        Company company2 = context.getBean("company2", Company.class);

        System.out.println("===静态工厂方式===" + company1.toString());
        System.out.println("===实例工厂方式===" + company2.toString());

    }

}
