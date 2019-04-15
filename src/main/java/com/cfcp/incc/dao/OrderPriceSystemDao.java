package com.cfcp.incc.dao;


import com.cfcp.incc.entity.OrderPriceSystem;

import java.util.List;

public interface OrderPriceSystemDao {

    List<OrderPriceSystem> queryAll();
    /*List<OrderPriceSystem> queryAll1_1(Map conditions);*/
    List<OrderPriceSystem> queryAll2();
    List<OrderPriceSystem> queryAll01();
    OrderPriceSystem findOrderPriceSystemById(String id);
    OrderPriceSystem queryByScjrf();
    int insert(OrderPriceSystem op);
    int update(OrderPriceSystem op);
}
