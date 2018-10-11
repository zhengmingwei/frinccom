package com.cfcp.incc.dto.user;

import com.cfcp.incc.dto.CommonDto;
import com.cfcp.incc.entity.Distributor;
import com.cfcp.incc.entity.User;
import org.json.simple.JSONObject;
import org.springframework.beans.BeanUtils;

/**
 * 用于返回用户信息
 * Created by zhyj on 2016/11/28.
 */
public class User1Dto extends CommonDto{


    private String name;

    private String yzm;
    private String distributorId;

    private String idCard;
     private String mail;
   private String phone;
    //private String distributorId;
     private String companyName;
     private String password;

    private String password2;


    private String businessLicense;

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(String distributorId) {
        this.distributorId = distributorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYzm() {
        return yzm;
    }

    public void setYzm(String yzm) {
        this.yzm = yzm;
    }
}
