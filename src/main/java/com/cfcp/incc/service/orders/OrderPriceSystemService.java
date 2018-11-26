package com.cfcp.incc.service.orders;

import com.cfcp.incc.entity.OrderPriceSystem;

import java.util.List;

public interface OrderPriceSystemService {


    public List<OrderPriceSystem> queryAll();
    public OrderPriceSystem findOrderPriceSystemById(String id);

    public OrderPriceSystem queryById(String productId);
}
