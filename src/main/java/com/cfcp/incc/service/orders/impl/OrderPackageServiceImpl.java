package com.cfcp.incc.service.orders.impl;

import com.cfcp.incc.dao.OrderPackageDao;
import com.cfcp.incc.entity.OrderPackage;
import com.cfcp.incc.entity.OrderPriceSystem;
import com.cfcp.incc.service.orders.OrderPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderPackageServiceImpl implements OrderPackageService {
    @Autowired
    private OrderPackageDao dao;


    @Override
    public int insert(OrderPackage r) {
        return dao.insert(r);
    }

    @Override
    public List<OrderPackage> queryAll() {
        return dao.queryAll();
    }

    @Override
    public OrderPackage findOrderPackageById(String id) {
        return dao.findOrderPackageById(id);
    }

    @Override
    public OrderPackage findMaxOrderPackageByUserIdAndStatusIsBuy(Map<String, String> str) {
        return dao.findMaxOrderPackageByUserIdAndStatusIsBuy(str);
    }
    @Override
    public OrderPackage findMaxOrderPackageByUserIdAndStatusNoBuy(Map<String, String> str) {
        return dao.findMaxOrderPackageByUserIdAndStatusNoBuy(str);
    }

    @Override
    public List<OrderPackage> queryAllByUserId(String userId) {
        return dao.queryAllByUserId(userId);
    }

    @Override
    public void updateOrderNum(OrderPackage p) {
         dao.updateOrderNum(p);
    }

    @Override
    public OrderPackage findMaxOrderPackageByOrderNum(String orderNum) {
        return dao.findMaxOrderPackageByOrderNum(orderNum);
    }

    @Override
    public void updateStatusIsBuyedForOrderNum(OrderPackage noBuyOp) {
        dao.updateStatusIsBuyedForOrderNum(noBuyOp);
    }

    @Override
    public OrderPackage findSumSutplusQuantityByUserId(String userId) {
        return dao.findSumSutplusQuantityByUserId(userId);
    }

    @Override
    public OrderPackage findSumAllSutplusQuantityByUserId(String userId) {
        return dao.findSumAllSutplusQuantityByUserId(userId);
    }



    @Override
    public void updateConsumptionCode(OrderPackage p) {
        dao.updateConsumptionCode(p);
    }

    @Override
    public List<OrderPackage> queryPayList() {
        return dao.queryPayList();
    }
}
