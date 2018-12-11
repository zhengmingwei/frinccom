package com.cfcp.incc.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -3080553479358151186L;

    public static final Integer STATUS_DESABLE = 0;
    public static final Integer STATUS_ENABLE = 1;
    public static final Integer STATUS_DELETED = -1;
    public static final String DEFAULT_PASSWORD = "123456";

//    private String id;

    private String idCard;

    private String password;

    private String name;

    private String mail;

    private String phone;

    private String companyName;

    private String businessLicense;

    private String businessLicense1;

    private String distributorId;

    private Distributor distributor;

    private Date createTime;

    private String creator;
    private String surplusQRcodeDesc;

    private Integer status;

    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String id, String idCard, String password, String name, String mail, String phone,String companyName,String businessLicense,String businessLicense1, String distributorId, Date createTime, String creator, Integer status,String surplusQRcodeDesc) {
        this.id = id;
        this.idCard = idCard;
        this.password = password;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.companyName = companyName;
        this.businessLicense = businessLicense;
        this.businessLicense1 = businessLicense1;
        this.distributorId = distributorId;
        this.createTime = createTime;
        this.creator = creator;
        this.status = status;
        this.surplusQRcodeDesc = surplusQRcodeDesc;
    }

//    @Override
//    public String getId() {
//        return id;
//    }
//
//    @Override
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(String distributorId) {
        this.distributorId = distributorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Distributor getDistributor() {
        return distributor;
    }

    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getSurplusQRcodeDesc() {
        return surplusQRcodeDesc;
    }

    public void setSurplusQRcodeDesc(String surplusQRcodeDesc) {
        this.surplusQRcodeDesc = surplusQRcodeDesc;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getBusinessLicense1() {
        return businessLicense1;
    }

    public void setBusinessLicense1(String businessLicense1) {
        this.businessLicense1 = businessLicense1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public int hashCode() {
        int result = password != null ? password.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", idCard='" + idCard + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                ", companyName='" + companyName + '\'' +
                ", businessLicense='" + businessLicense + '\'' +
                ", businessLicense1='" + businessLicense1 + '\'' +
                ", distributorId='" + distributorId + '\'' +
                ", createTime=" + createTime +
                ", creator='" + creator + '\'' +
                ", status=" + status +
                ", surplusQRcodeDesc=" + surplusQRcodeDesc +
                ", roles=" + roles +
                '}';
    }
}