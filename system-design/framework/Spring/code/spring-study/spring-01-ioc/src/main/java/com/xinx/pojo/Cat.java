package com.xinx.pojo;

/**
 * @description:
 * @author: JXIN
 * @time: 2021/5/25 11:38
 */
public class Cat {

    private String id;

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

    @Override
    public String toString() {
        return "Cat{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
