package com.cfcp.incc.dao;


import com.cfcp.incc.entity.OrderPriceSystem;

import java.util.List;

public interface OrderPriceSystemDao {

    List<OrderPriceSystem> queryAll();
    List<OrderPriceSystem> queryAll01();
    OrderPriceSystem findOrderPriceSystemById(String id);
    OrderPriceSystem queryByScjrf();
    void insert(OrderPriceSystem op);
    void update(OrderPriceSystem op);
}
