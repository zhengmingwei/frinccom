package com.cfcp.incc.dao;


import com.cfcp.incc.entity.OrderPriceSystem;
import com.cfcp.incc.entity.OrderPriceSystem1;

import java.util.List;
import java.util.Map;

public interface OrderPriceSystemDao {

    List<OrderPriceSystem> queryAll();
    List<OrderPriceSystem1> queryAll1_1(Map conditions);
    List<OrderPriceSystem1> queryAll1();
    List<OrderPriceSystem> queryAll01();
    OrderPriceSystem findOrderPriceSystemById(String id);
    OrderPriceSystem queryByScjrf();
    int insert(OrderPriceSystem op);
    int update(OrderPriceSystem op);
    int insert(OrderPriceSystem1 op);
    int update(OrderPriceSystem1 op);
}
