package com.cfcp.incc.entity;

/**
 * Created by zhyj on 2017/7/12.
 */
public class BusinessCompany extends Company {

    private String regAddr;

    private String businessAddr;

    
    public String getRegAddr() {
        return regAddr;
    }

    public void setRegAddr(String regAddr) {
        this.regAddr = regAddr;
    }

    public String getBusinessAddr() {
        return businessAddr;
    }

    public void setBusinessAddr(String businessAddr) {
        this.businessAddr = businessAddr;
    }
}
