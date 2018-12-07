package com.cfcp.incc.service.orders.impl;

import com.cfcp.incc.dao.OrderPriceSystemDao;
import com.cfcp.incc.entity.OrderPriceSystem;
import com.cfcp.incc.service.orders.OrderPriceSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderPriceSystemServiceImpl implements OrderPriceSystemService {
    @Autowired
    private OrderPriceSystemDao dao;

    @Override
    public List<OrderPriceSystem> queryAll() {
        return dao.queryAll();
    }

    @Override
    public OrderPriceSystem findOrderPriceSystemById(String id) {
        return dao.findOrderPriceSystemById(id);
    }

    @Override
    public OrderPriceSystem queryById(String productId) {
        return dao.findOrderPriceSystemById(productId);
    }

    @Override
    public OrderPriceSystem queryByScjrf() {
        return dao.queryByScjrf();
    }
}
