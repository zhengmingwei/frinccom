package com.cfcp.incc.dao;

import com.cfcp.incc.entity.Commodity;
import com.cfcp.incc.form.CommoditySearchForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommodityDao{
    int delete(String id);

    int insert(Commodity record);

    int insertSelective(Commodity record);

    Commodity get(String id);

    int updateSelective(Commodity record);

    int update(Commodity record);

    List<Commodity> query(Map<String, String> conditions);

    List<Commodity> allAudited();

    List<Map> allMap();

    int updateStatus(@Param("id") String id, @Param("status") Integer status);
}