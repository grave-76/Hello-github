package com.xinx.factory;

/**
 * @description:
 * @author: JXIN
 * @time: 2021/5/25 21:04
 */
public class Company {

    /** ID */
    private String id;

    /** 名称 */
    private String name;

    /** 部门 */
    private Department department;

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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", department=" + department.toString() +
                '}';
    }
}
