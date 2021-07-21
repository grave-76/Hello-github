import com.xinx.pojo.Person;
import com.xinx.pojo.UserInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: JXIN
 * @time: 2021/5/19 23:02
 */
public class MyTest {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Person person = context.getBean("person", Person.class);
        // money设置为null值，打印显示'null'字符串为toString()方法中拼接字符串导致
        System.out.println(person.toString());

        UserInfo userInfo = context.getBean("userInfo", UserInfo.class);
        System.out.println(userInfo.toString());
    }

}
