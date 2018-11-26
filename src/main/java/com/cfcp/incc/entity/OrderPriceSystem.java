package com.cfcp.incc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class OrderPriceSystem extends BaseEntity {
    /*
   `ID`                   varchar(32) not null,
   `NAME`                 varchar(32) default NULL comment '名称',
   `DESCRIBE`             varchar(512) default NULL comment '描述',
   `PRICE`                double(16,2) comment '价格',
   `TOTAL`                int(11) default NULL comment '总数',
   `DELETE`               varchar(1) default NULL comment '是否删除',
   `SORT`                 int(4) default NULL comment '排序',
   `STATUS`               int(11) default NULL  comment '状态：0：免费；1：需支付',
   `CREATE_TIME`          datetime default NULL comment '创建时间',
   `UPDATE_TIME`          datetime default NULL comment '更新时间'
     */

    private String id;
    private String name;
    private String describe;
    private Double price;
    private String delet;
    private Integer total;
    private Integer sort;
    private Integer status;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDelete() {
        return delet;
    }

    public void setDelete(String delete) {
        this.delet = delete;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
}
