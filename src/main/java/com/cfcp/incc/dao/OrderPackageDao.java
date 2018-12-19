package com.cfcp.incc.dao;

import com.cfcp.incc.entity.OrderPackage;
import com.cfcp.incc.entity.OrderPriceSystem;

import java.util.List;
import java.util.Map;

public interface OrderPackageDao {


    public int insert(OrderPackage record);
    public List<OrderPackage> queryAll();
    public OrderPackage findOrderPackageById(String id);
    public OrderPackage findMaxOrderPackageByUserIdAndStatusIsBuy(Map<String, String> str);
    public OrderPackage findMaxOrderPackageByUserIdAndStatusNoBuy(Map<String, String> str);
    public List<OrderPackage> queryAllByUserId(String userId);
    public void updateOrderNum(OrderPackage p);
    public OrderPackage findMaxOrderPackageByOrderNum(String orderNum);
    public void updateStatusIsBuyedForOrderNum(OrderPackage noBuyOp);
    public void updateConsumptionCode(OrderPackage p);
    public OrderPackage findSumAllSutplusQuantityByUserId(String userId);
    public OrderPackage findSumSutplusQuantityByUserId(String userId);
    public List<OrderPackage> queryPayList();
}
