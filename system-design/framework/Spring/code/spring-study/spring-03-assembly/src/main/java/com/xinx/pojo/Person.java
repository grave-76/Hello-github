package com.xinx.pojo;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: JXIN
 */
public class Person {

    /** 无参构造 */
    public Person() {

    }

    /** 有参构造 */
    public Person(Cat cat, Dog dog) {
        this.cat = cat;
        this.dog2 = dog;
    }

    /** 有参构造 */
    public Person(Cat cat) {
        this.cat = cat;
    }

    /** ID */
    private String id;

    /** 名称 */
    private String name;

    @Autowired
    //@Resource(name = "dog2")
    /** 狗 */
    private Dog dog2;

    @Autowired
    //@Resource
    /** 猫 */
    private Cat cat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dog getDog() {
        return dog2;
    }

    public void setDog(Dog dog) {
        this.dog2 = dog;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dog=" + dog2+
                ", cat=" + cat+
                '}';
    }
}
