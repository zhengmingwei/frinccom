package com.cfcp.incc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class NumberCommodityReadings  extends BaseEntity {

/*
* create table NUMBER_COMMODITY_READINGS
(
   ID                   varchar(32) not null,
   COMMODITY_ID         varchar(32) default NULL comment '商品ID',
   CREATE_TIME          datetime default NULL comment '初次访问时间',
   LATELY_TIME          datetime default NULL comment '最近访问时间',
   TOTAL                int(11) default NULL comment '访问总次数',
   STATUS               int(11) default NULL  comment '状态',
   IP                   varchar(32) default NULL comment 'IP地址',

)
* */

    private String commodityId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date latelyTime;
    private Integer total;
    private Integer status;
    private String ip;

    public Date getLatelyTime() {
        return latelyTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLatelyTime(Date date) {
        return latelyTime;
    }

    public void setLatelyTime(Date latelyTime) {
        this.latelyTime = latelyTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
