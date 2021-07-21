package com.xinx.pojo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: JXIN
 */
@Component
//@Configuration
public class Person {

    @Bean // 注册一个bean,id为方法名
    public Cat getCat() {
        return new Cat();
    }

    @Qualifier
    /* ID */
    private String id;

    @Value("孙悟空")
    /* 名称 */
    private String name;

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
}
