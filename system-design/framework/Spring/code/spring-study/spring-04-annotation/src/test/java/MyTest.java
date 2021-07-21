import com.xinx.pojo.Cat;
import com.xinx.pojo.Person;
import com.xinx.pojo.UserInfo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: JXIN
 * @time: 2021/5/24 11:41
 */
public class MyTest {

    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserInfo userInfo = context.getBean("userInfo", UserInfo.class);
        UserInfo userInfo2 = context.getBean("userInfo", UserInfo.class);

        System.out.println(userInfo == userInfo2);

    }

    @Test
    public void test2() {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = context.getBean("person", Person.class);
        Cat cat3 = person.getCat();
        Cat cat4 = person.getCat();
        System.out.println(cat3 == cat4);

        //AnnotationConfigApplicationContext context2 = new AnnotationConfigApplicationContext(Person.class);
        //Cat cat = context2.getBean("getCat", Cat.class);
        //Cat cat2 = context2.getBean("getCat", Cat.class);
        //
        //System.out.println(cat == cat2);

    }

}
