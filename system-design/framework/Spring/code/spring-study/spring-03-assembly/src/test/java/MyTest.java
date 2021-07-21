import com.xinx.pojo.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

/**
 * @description:
 * @author: JXIN
 * @time: 2021/5/23 22:21
 */
public class MyTest {

    @Test
    public void testAssembly() {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Person person = context.getBean("person", Person.class);
        Person personByNo = context.getBean("personByNo", Person.class);
        Person personByName = context.getBean("personByName", Person.class);
        Person personByType = context.getBean("personByType", Person.class);
        Person personByConstructor = context.getBean("personByConstructor", Person.class);

        System.out.println("======" + person.toString());
        System.out.println("===no===" + personByNo.toString());
        System.out.println("===byName===" + personByName.toString());
        System.out.println("===byType===" + personByType.toString());
        System.out.println("===constructor===" + personByConstructor.toString());

    }

    @Test
    public void testAnnotation() {

        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");

        /*
        @Autowired 作用于属性与setter方法
        byType
        首先在容器中查找对应类型的bean,
        恰好为一个则装配；
        若有多个则根据属性名称查找；
        若无则抛出异常（可用require=false处理）
         */
        Person person = context.getBean("person", Person.class);
        System.out.println(person.toString());

        /*
        @Resource java注解，作用于属性与setter方法
        默认byName方式，默认取小写bean名称
        若根据name找不到,则通过byType方式
         */

    }

}
