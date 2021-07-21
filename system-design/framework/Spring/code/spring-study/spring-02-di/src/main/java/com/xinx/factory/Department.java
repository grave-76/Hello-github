package com.xinx.factory;

/**
 * @description:
 * @author: JXIN
 * @time: 2021/5/25 21:05
 */
public class Department {

    /** ID */
    private String id;

    /** 名称 */
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
        return "Department{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
