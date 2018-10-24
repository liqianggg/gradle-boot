package com.liqiang.gradle.gradleboot.service.impl;

import com.liqiang.gradle.gradleboot.mapper.CusCustomerInfoMapper;
import com.liqiang.gradle.gradleboot.model.CusCustomerInfo;
import com.liqiang.gradle.gradleboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CusCustomerInfoMapper cusCustomerInfoMapper;

    @Override
    public CusCustomerInfo getCustomerById(Long cid) {
        return cusCustomerInfoMapper.selectByPrimaryKey(cid);
    }
}
