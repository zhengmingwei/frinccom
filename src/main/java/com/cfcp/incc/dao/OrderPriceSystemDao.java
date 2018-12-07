package com.cfcp.incc.dao;


import com.cfcp.incc.entity.OrderPriceSystem;

import java.util.List;

public interface OrderPriceSystemDao {

    List<OrderPriceSystem> queryAll();
    OrderPriceSystem findOrderPriceSystemById(String id);

    OrderPriceSystem queryByScjrf();
}
