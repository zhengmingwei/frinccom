package com.cfcp.incc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
public class CommodityQrcode extends BaseEntity{

    private String commodityId;
    private String childCommodityId;
    private String batch;
    private Integer isQrcode;
    private Integer dele;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date usedQrcodeTime;//USED_QRCODE_TIME
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date finalTime;//FINAL_TIME
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getChildCommodityId() {
        return childCommodityId;
    }

    public void setChildCommodityId(String childCommodityId) {
        this.childCommodityId = childCommodityId;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Integer getIsQrcode() {
        return isQrcode;
    }

    public void setIsQrcode(Integer isQrcode) {
        this.isQrcode = isQrcode;
    }

    public Integer getDele() {
        return dele;
    }

    public void setDele(Integer dele) {
        this.dele = dele;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUsedQrcodeTime() {
        return usedQrcodeTime;
    }

    public void setUsedQrcodeTime(Date usedQrcodeTime) {
        this.usedQrcodeTime = usedQrcodeTime;
    }

    public Date getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(Date finalTime) {
        this.finalTime = finalTime;
    }
}
