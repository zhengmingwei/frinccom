package com.cfcp.incc.dao;

import com.cfcp.incc.entity.Payment;

import java.util.List;
import java.util.Map;

public interface PaymentDao {
    int delete(String id);

    int insert(Payment record);

    int insertSelective(Payment record);

    Payment get(String id);

    int updateSelective(Payment record);

    int update(Payment record);

    List<Payment> query(Map<String, String> conditions);
}