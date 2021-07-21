package com.xinx.factory;

import com.xinx.factory.Department;

/**
 * @description:
 * @author: JXIN
 * @time: 2021/5/25 21:07
 */
public class DeptFactory {

    /**
     * 静态方式-获取部门对象
     * @return
     */
    public static Department initDept() {
        Department department = new Department();
        department.setName("研发部");
        return department;
    }

    /**
     * 获取部门对象
     * @return
     */
    public Department getDept() {
        Department department = new Department();
        department.setName("产品部");
        return department;
    }

}
