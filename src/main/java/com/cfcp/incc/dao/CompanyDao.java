package com.cfcp.incc.dao;

import com.cfcp.incc.entity.BusinessCompany;
import com.cfcp.incc.entity.Company;
import com.cfcp.incc.entity.ManufactureCompany;

public interface CompanyDao {
    int delete(String id);

    int insert(Company record);
//
//    int insertBusinessCompany(BusinessCompany businessCompany);
//
//    int insertManufactureCompany(ManufactureCompany manufactureCompany);

    int insertSelective(Company record);

    Company get(String id);

    int updateSelective(Company record);

    int update(Company record);
}