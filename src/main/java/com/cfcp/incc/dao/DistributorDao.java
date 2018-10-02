package com.cfcp.incc.dao;

import com.cfcp.incc.entity.Distributor;

import java.util.List;
import java.util.Map;

public interface DistributorDao {
    int delete(String id);

    int insert(Distributor record);

    int insertSelective(Distributor record);

    Distributor get(String id);

    int updateSelective(Distributor record);

    int update(Distributor record);

    List<Map> allMap();

//    List<Distributor> query(Distributor distributor);

    List<Distributor> query(Map<String, String> conditions);
}