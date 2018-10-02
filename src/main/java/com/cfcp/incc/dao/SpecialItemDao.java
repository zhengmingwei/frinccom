package com.cfcp.incc.dao;

import com.cfcp.incc.entity.SpecialItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpecialItemDao {

    int delete(String id);

    int insert(SpecialItem record);

    int insertSelective(SpecialItem record);

    SpecialItem get(String id);

    List<SpecialItem> getByCommodityId(String commodityId);

    int updateSelective(SpecialItem record);

    int update(SpecialItem record);

    int updateCommodityId(@Param("id") String id, @Param("commodityId") String commodityId);
}