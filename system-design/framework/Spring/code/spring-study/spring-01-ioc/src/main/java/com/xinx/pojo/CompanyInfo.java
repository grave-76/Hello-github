package com.xinx.pojo;

import java.util.Date;

/**
 * @description: 公司实体类
 * @author: JXIN
 * @time: 2021/5/11 21:47
 */
public class CompanyInfo {

    /** 默认构造器 */
    public CompanyInfo() {
        System.out.println("=== run default constructor");
    }

    /** 有参构造方法 */
    public CompanyInfo(String name) {
        this.id = id;
        this.name = name;
        System.out.println("=== CompanyInfo有参构造函数");
    }

    /** ID */
    private String id;

    /** 名称 */
    private String name;

    /** 编码 */
    private String code;

    /** 部门数量 */
    private int deptNum;

    /** 员工数量 */
    private int staffNum;

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

    public int getDeptNum() {
        return deptNum;
    }

    public void setDeptNum(int deptNum) {
        this.deptNum = deptNum;
    }

    public int getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(int staffNum) {
        this.staffNum = staffNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CompanyInfo{" +
                "id=" + id +
                ", name=" + name +
                ", code=" + code +
                ", deptNum=" + deptNum +
                ", staffNum=" + staffNum +
                ", createTime=" + createTime +
                '}';
    }
}
