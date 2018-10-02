package com.cfcp.incc.entity;

import java.util.Date;

public class Brand extends BaseEntity {
//    private String id;

    private String name;

    private String owner;

    private String code;

    private String brandStatus;
    private String brandStatusValue;

    private String registrationCertificate;

    private String notification;

    private String authorization;

    private String distributorId;

    private Date createTime;

    private String creator;

//    private Integer status;

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id == null ? null : id.trim();
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getBrandStatus() {
        return brandStatus;
    }

    public void setBrandStatus(String brandStatus) {
        this.brandStatus = brandStatus;
        if ("142".equals(brandStatus)){
            this.brandStatusValue="R";
        } else {
            this.brandStatusValue="TM";
        }
    }

    public String getRegistrationCertificate() {
        return registrationCertificate;
    }

    public void setRegistrationCertificate(String registrationCertificate) {
        this.registrationCertificate = registrationCertificate == null ? null : registrationCertificate.trim();
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification == null ? null : notification.trim();
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization == null ? null : authorization.trim();
    }

    public String getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(String distributorId) {
        this.distributorId = distributorId == null ? null : distributorId.trim();
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
        this.creator = creator == null ? null : creator.trim();
    }

    public String getBrandStatusValue() {
        return brandStatusValue;
    }

    //    public Integer getStatus() {
//        return status;
//    }
//
//    public void setStatus(Integer status) {
//        this.status = status;
//    }
}