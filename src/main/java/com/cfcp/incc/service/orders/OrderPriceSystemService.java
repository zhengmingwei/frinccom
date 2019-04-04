package com.cfcp.incc.service.orders;

import com.cfcp.incc.entity.OrderPriceSystem;

import java.util.List;

public interface OrderPriceSystemService {


    public List<OrderPriceSystem> queryAll01();
    public List<OrderPriceSystem> queryAll();
    public OrderPriceSystem findOrderPriceSystemById(String id);

    public OrderPriceSystem queryById(String productId);

    public OrderPriceSystem queryByScjrf();

    public int saveOrUpdate(OrderPriceSystem orderPriceSystem);
    public int saveOrUpdate1(OrderPriceSystem orderPriceSystem);
}
