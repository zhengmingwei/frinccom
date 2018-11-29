package com.cfcp.incc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class OrderPackage extends BaseEntity {

    /*

-- ----------------------------
-- Table structure for ORDER_PACKAGE
-- ----------------------------
DROP TABLE IF EXISTS `ORDER_PACKAGE`;
CREATE TABLE `ORDER_PACKAGE` (
  `ID` varchar(32) NOT NULL,
  `ORDER_PRICE_SYSTEM_ID` varchar(32) NOT NULL COMMENT '价格体系ID',
  `NAME` varchar(32) DEFAULT NULL COMMENT '订购价格体系名称',
  `PRICE` double(16,2) DEFAULT NULL COMMENT '价格',
  `PAYMENT_AMOUNT` double(16,2) DEFAULT NULL COMMENT '付款金额',
  `BUYING_TIMES` int(11) DEFAULT NULL COMMENT '订购套餐次数',
  `TOTAL` int(11) DEFAULT NULL COMMENT '本次订购总数',
  `QUANTITY_USED` int(11) DEFAULT NULL COMMENT '已用数量',
  `SURPLUS_QUANTITY` int(11) DEFAULT NULL COMMENT '剩余数量',
  `DELETE` varchar(1) DEFAULT NULL COMMENT '是否删除',
  `IS_USE_LIGHT` varchar(1) DEFAULT NULL COMMENT '是否用光',
  `STATUS` int(11) DEFAULT NULL COMMENT '状态 0：未订购；1：已订购；2：已过期；；3：免费（管理员订购免费）；',
  `REFUNDS_STATUS` int(11) DEFAULT NULL COMMENT '是否退款 0：未申请；1：已未申请退款；2：已退款；',
  `REFUNDS_PEE` double(16,2) DEFAULT NULL COMMENT '退款金额',
  `FLOW_NUM` varchar(60) DEFAULT NULL COMMENT '流水号',
  `ORDER_NUM` varchar(60) DEFAULT NULL COMMENT '订单号',
  `USER_ID` varchar(32) NOT NULL COMMENT '支付用户ID',
  `USER_NAME` varchar(32) NOT NULL COMMENT '支付用户名称',
  `USER_ROLE` varchar(32) NOT NULL COMMENT '用户角色',
  `CREATE_USER_ID` varchar(32) NOT NULL COMMENT '创建人ID',
  `DISTRIBUTOR_ID` varchar(32) DEFAULT NULL COMMENT '所属分销商ID',
  `DISTRIBUTOR_NAME` varchar(32) DEFAULT NULL COMMENT '所属分销商',
  `UPDATE_USER_ID` varchar(32) NOT NULL COMMENT '更新人ID',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `FIRST_USE_TIME` datetime DEFAULT NULL COMMENT '第一次使用时间',
  `END_TIME` datetime DEFAULT NULL COMMENT '截至时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订购套餐';

-- ----------------------------
-- Records of ORDER_PACKAGE
-- ----------------------------

     */


    private String id;
    private String orderPriceSystemId;
    private String name;
    private Double price;
    private Double paymentAmount;
    private Integer buyingIimes;
    private Integer total;
    private Integer quantityUsed;
    private Integer surplusQuentity;
    private String dele;
    private String isUseLight;
    private Integer status;
    private String refundsStatus;


    private Double refundsPee;
    private String flowNum;
    private String orderNum;
    private String userId;
    private String userName;
    private String userRole;
    private String createUserId;
    private String distributorId;
    private String distributorName;
    private String updateUserId;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date firstUseTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endTime;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderPriceSystemId() {
        return orderPriceSystemId;
    }

    public void setOrderPriceSystemId(String describe) {
        this.orderPriceSystemId = describe;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Integer getBuyingIimes() {
        return buyingIimes;
    }

    public void setBuyingIimes(Integer buyingIimes) {
        this.buyingIimes = buyingIimes;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getQuantityUsed() {
        return quantityUsed;
    }

    public void setQuantityUsed(Integer quantityUsed) {
        this.quantityUsed = quantityUsed;
    }

    public Integer getSurplusQuentity() {
        return surplusQuentity;
    }

    public void setSurplusQuentity(Integer surplusQuentity) {
        this.surplusQuentity = surplusQuentity;
    }

    public String getIsUseLight() {
        return isUseLight;
    }

    public void setIsUseLight(String isUseLight) {
        this.isUseLight = isUseLight;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getRefundsPee() {
        return refundsPee;
    }

    public void setRefundsPee(Double refundsPee) {
        this.refundsPee = refundsPee;
    }

    public String getFlowNum() {
        return flowNum;
    }

    public void setFlowNum(String flowNum) {
        this.flowNum = flowNum;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(String distributorId) {
        this.distributorId = distributorId;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
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

    public Date getFirstUseTime() {
        return firstUseTime;
    }

    public void setFirstUseTime(Date firstUseTime) {
        this.firstUseTime = firstUseTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDele() {
        return dele;
    }

    public void setDele(String dele) {
        this.dele = dele;
    }

    public String getRefundsStatus() {
        return refundsStatus;
    }

    public void setRefundsStatus(String refundsStatus) {
        this.refundsStatus = refundsStatus;
    }
}
