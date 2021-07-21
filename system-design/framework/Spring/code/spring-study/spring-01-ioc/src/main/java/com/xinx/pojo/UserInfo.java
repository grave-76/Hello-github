package com.xinx.pojo;

import java.util.Date;

/**
 * @description:
 * @author: JXIN
 * @time: 2021/5/11 23:07
 */
public class UserInfo {

    /* 默认构造函数 */
    public UserInfo() {
        System.out.println("======UserInfo默认无参构造函数");
    }

    /* 有参构造函数 */
    public UserInfo(String id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("======UserInfo有参构造函数：id=" + id + ",name=" + name);
    }

    /* ID */
    private String id;

    /* 名称 */
    private String name;

    /* 编码 */
    private String code;

    /* age */
    private int age;

    /* 创建时间 */
    private Date createTime;

    /* 宠物猫 */
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }
}
