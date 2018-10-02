package com.cfcp.incc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Company extends BaseEntity {

    //1.经营企业 2.生产企业
//    public static final Integer DESCRIMINATOR_BUSINESS = 1;
//    public static final Integer DESCRIMINATOR_FACTORY = 2;

//    private String id;

    private String name;

    private String idType;
    private String  idTypeValue;

    private String idCode;

    private String companyType;
    private String companyTypeValue;

    private String legalPerson;

    private String regAddr;   //1.经营企业

    private String businessAddr;   //1.经营企业

    private String addr; //2.生产企业

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date businessBegin;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date businessEnd;

    private String contacter;

    private String phone;

    private String mail;

    private String mphone;

    private String businessScope;

    private String businessLicense;

    private String qese;

    private String qeseCode;

    private String qeseFile;

    //1.经营企业 2.生产企业
    private Integer descriminator;

    private Date createTime;

    private String creator;

    private Integer status;

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

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
        if("138".equals(idType)){
            this.idTypeValue = "组织机构代码";
        } else {
            this.idTypeValue = "社会统一信用代码";
        }
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode == null ? null : idCode.trim();
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
        if("140".equals(companyType)){
            this.companyTypeValue = "有限责任公司";
        } else {
            this.companyTypeValue = "股份有限公司";
        }
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
    }

    public Date getBusinessBegin() {
        return businessBegin;
    }

    public void setBusinessBegin(Date businessBegin) {
        this.businessBegin = businessBegin;
    }

    public Date getBusinessEnd() {
        return businessEnd;
    }

    public void setBusinessEnd(Date businessEnd) {
        this.businessEnd = businessEnd;
    }

    public String getContacter() {
        return contacter;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter == null ? null : contacter.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getMphone() {
        return mphone;
    }

    public void setMphone(String mphone) {
        this.mphone = mphone == null ? null : mphone.trim();
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope == null ? null : businessScope.trim();
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense == null ? null : businessLicense.trim();
    }

    public String getQese() {
        return qese;
    }

    public void setQese(String qese) {
        this.qese = qese == null ? null : qese.trim();
    }

    public String getQeseFile() {
        return qeseFile;
    }

    public void setQeseFile(String qeseFile) {
        this.qeseFile = qeseFile == null ? null : qeseFile.trim();
    }

    public Integer getDescriminator() {
        return descriminator;
    }

    public void setDescriminator(Integer descriminator) {
        this.descriminator = descriminator;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

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

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getIdTypeValue() {
        return idTypeValue;
    }

    public String getCompanyTypeValue() {
        return companyTypeValue;
    }

    public String getQeseCode() {
        return qeseCode;
    }

    public void setQeseCode(String qeseCode) {
        this.qeseCode = qeseCode;
    }
}