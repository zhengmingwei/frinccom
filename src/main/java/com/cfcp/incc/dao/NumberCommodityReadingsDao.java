package com.cfcp.incc.dao;

import com.cfcp.incc.entity.NumberCommodityReadings;

import java.util.List;

public interface NumberCommodityReadingsDao {
    public int insert(NumberCommodityReadings record);
    public int update(NumberCommodityReadings record);
    public NumberCommodityReadings get(NumberCommodityReadings ni);
    public List<NumberCommodityReadings> getAll(NumberCommodityReadings ni);
    public int updateAddressByIP(NumberCommodityReadings ni);
    public List<NumberCommodityReadings> getByAddressIsNull(NumberCommodityReadings ni);

}
