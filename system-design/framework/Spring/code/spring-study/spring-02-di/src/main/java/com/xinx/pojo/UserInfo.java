package com.xinx.pojo;

/**
 * @description: 用户信息表
 * @author: JXIN
 * @time: 2021/5/19 23:48
 */
public class UserInfo {

    /** 无参构造器 */
    public UserInfo() {

    }

    /** 有参构造器 */
    public UserInfo(String id, String name, int age, String hobby, Address address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.hobby = hobby;
        this.address = address;
    }

    /** ID */
    private String id;

    /** 名称 */
    private String name;

    /** 年龄 */
    private int age;

    /** 爱好 */
    private String hobby;

    private Address address;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", hobby='" + hobby + '\'' +
                ", address=" + address +
                '}';
    }
}
