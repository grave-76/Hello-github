package com.xinx.service.impl;

import com.xinx.dao.CompanyInfoDao;
import com.xinx.dao.impl.CompanyInfoDaoImpl;
import com.xinx.pojo.CompanyInfo;
import com.xinx.service.CompanyInfoService;

/**
 * @description:
 * @author: JXIN
 * @time: 2021/5/11 23:05
 */
public class CompanyInfoServiceImpl implements CompanyInfoService {

    CompanyInfoDao CompanyInfoDao = new CompanyInfoDaoImpl();

    @Override
    public CompanyInfo getInfo() {

        return CompanyInfoDao.getInfo();

    }
}
