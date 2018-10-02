package com.cfcp.incc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class OtherQualification extends BaseEntity {
//    private String id;

    private String reportType;
    private Dictionary reportTypePo;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date expiryDate;

    private String detectionOrg;

    private String report;

    private String commodityId;

    private Date createTime;

    private String creator;

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id == null ? null : id.trim();
//    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getDetectionOrg() {
        return detectionOrg;
    }

    public void setDetectionOrg(String detectionOrg) {
        this.detectionOrg = detectionOrg == null ? null : detectionOrg.trim();
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report == null ? null : report.trim();
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId == null ? null : commodityId.trim();
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

    public Dictionary getReportTypePo() {
        return reportTypePo;
    }

    public void setReportTypePo(Dictionary reportTypePo) {
        this.reportTypePo = reportTypePo;
    }
}