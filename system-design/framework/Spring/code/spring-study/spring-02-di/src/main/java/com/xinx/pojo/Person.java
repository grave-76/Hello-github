package com.xinx.pojo;

import java.util.*;

/**
 * @description: person实体对象
 * @author: JXIN
 * @time: 2021/5/19 22:04
 */
public class Person {

    /** ID */
    private String id;

    /** 名称 */
    private String name;

    /** 住址 */
    private Address address;

    /** 书 */
    private String[] books;

    /** 爱好 */
    private List<String> hobbies;

    /** 游戏 */
    private Map<String, String> games;

    /** 朋友 */
    private Set<String> friends;

    /** 钱 */
    private String money;

    /** 特性 */
    private Properties info;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String[] getBooks() {
        return books;
    }

    public void setBooks(String[] books) {
        this.books = books;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public Map<String, String> getGames() {
        return games;
    }

    public void setGames(Map<String, String> games) {
        this.games = games;
    }

    public Set<String> getFriends() {
        return friends;
    }

    public void setFriends(Set<String> friends) {
        this.friends = friends;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Properties getInfo() {
        return info;
    }

    public void setInfo(Properties info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address=" + address.toString() +
                ", books=" + Arrays.toString(books) +
                ", hobbies=" + hobbies +
                ", games=" + games +
                ", friends=" + friends +
                ", money='" + money + '\'' +
                ", info=" + info +
                '}';
    }
}
