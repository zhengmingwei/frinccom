package com.cfcp.incc.dao;

import com.cfcp.incc.entity.NumberCommodityReadings;

public interface NumberCommodityReadingsDao {
    int insert(NumberCommodityReadings record);
    int update(NumberCommodityReadings record);
    NumberCommodityReadings get(String id);
}
