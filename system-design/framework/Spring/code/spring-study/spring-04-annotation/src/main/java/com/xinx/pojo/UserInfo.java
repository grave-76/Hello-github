package com.xinx.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description:
 * @author: JXIN
 */
@Component // 等价于在xml中配置bean,id默认为类名
public class UserInfo {

    @Value("001") // 相当于配置bean中的property属性
    /** ID */
    private String id;

    /** 名称 */
    private String name;

    /** 编码 */
    private String code;

    /** age */
    private int age;

    /** 创建时间 */
    private Date createTime;

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
}
