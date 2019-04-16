package com.cfcp.incc.service.orders;

import com.cfcp.incc.entity.OrderPriceSystem;
import com.cfcp.incc.entity.OrderPriceSystem1;

import java.util.List;
import java.util.Map;

public interface OrderPriceSystemService {


    public List<OrderPriceSystem> queryAll01();
    public List<OrderPriceSystem> queryAll();
    public List<OrderPriceSystem1> queryAll1();
    public List<OrderPriceSystem1> queryAll1(Map conditions);
    public List<OrderPriceSystem1> queryByName(Map conditions);
    public OrderPriceSystem findOrderPriceSystemById(String id);

    public OrderPriceSystem queryById(String productId);

    public OrderPriceSystem queryByScjrf();

    public int saveOrUpdate(OrderPriceSystem orderPriceSystem);
    public int saveOrUpdate1(OrderPriceSystem1 orderPriceSystem);
}
