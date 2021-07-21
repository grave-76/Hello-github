package com.xinx.pojo;

/**
 * @description:
 * @author: JXIN
 */
public class Dog {

    /** ID */
    private String id;

    /** 名称 */
    private String name;

    /** 狗吠 */
    public void barking() {

        System.out.println("======汪");

    }

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

    @Override
    public String toString() {
        return "Dog{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
