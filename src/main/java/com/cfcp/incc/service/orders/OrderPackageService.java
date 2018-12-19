package com.cfcp.incc.service.orders;

import com.cfcp.incc.entity.OrderPackage;
import com.cfcp.incc.entity.OrderPriceSystem;

import java.util.List;
import java.util.Map;

public interface OrderPackageService {

    public int insert(OrderPackage record);
    public List<OrderPackage> queryAll();
    public OrderPackage findOrderPackageById(String id);
    public OrderPackage findMaxOrderPackageByUserIdAndStatusIsBuy(Map<String, String> str);
    public OrderPackage findMaxOrderPackageByUserIdAndStatusNoBuy(Map<String, String> str);
    public List<OrderPackage> queryAllByUserId(String userId);
    public void updateOrderNum(OrderPackage p);
    public OrderPackage findMaxOrderPackageByOrderNum(String orderNum);
    public void updateStatusIsBuyedForOrderNum(OrderPackage noBuyOp);
    //根据 用户ID查询 当前登录人的购买二维码的剩余数量
    public OrderPackage findSumSutplusQuantityByUserId(String userId);
    public OrderPackage findSumAllSutplusQuantityByUserId(String userId);
    public void updateConsumptionCode(OrderPackage p);

    public List<OrderPackage> queryPayList();
}
