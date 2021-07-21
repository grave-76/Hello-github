package com.xinx.pojo;

/**
 * @description:
 * @author: JXIN
 */
public class Cat {

    /** ID */
    private String id;

    /** 名称 */
    private String name;

    /** 猫叫 */
    public void mew() {

        System.out.println("======喵");

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
        return "Cat{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
