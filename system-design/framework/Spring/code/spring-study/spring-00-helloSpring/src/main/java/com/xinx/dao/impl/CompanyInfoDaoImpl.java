package com.xinx.dao.impl;

import com.xinx.dao.CompanyInfoDao;
import com.xinx.pojo.CompanyInfo;

/**
 * @description:
 * @author: JXIN
 * @time: 2021/5/11 23:01
 */
public class CompanyInfoDaoImpl implements CompanyInfoDao {

    @Override
    public CompanyInfo getInfo() {
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setId("002");
        companyInfo.setName("SUN");
        companyInfo.setCode("sun");
        return companyInfo;
    }
}
