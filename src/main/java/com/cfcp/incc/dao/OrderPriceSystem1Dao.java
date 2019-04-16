package com.cfcp.incc.dao;

import com.cfcp.incc.entity.OrderPriceSystem1;

import java.util.List;
import java.util.Map;

public interface OrderPriceSystem1Dao {

    List<OrderPriceSystem1> queryAll1_1(Map conditions);
    List<OrderPriceSystem1> queryByName(Map conditions);
    List<OrderPriceSystem1> queryAll1();
    int insert(OrderPriceSystem1 op);
    int update(OrderPriceSystem1 op);
}
