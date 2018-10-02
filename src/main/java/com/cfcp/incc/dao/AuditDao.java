package com.cfcp.incc.dao;

import com.cfcp.incc.entity.Audit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuditDao {
    int delete(String id);

    int insert(Audit record);

    int insertSelective(Audit record);

    Audit get(String id);

    int updateSelective(Audit record);

    int update(Audit record);

    Audit getLast(String id);

    Audit findByCommodityIdAndStep(@Param("commodityId") String commodityId, @Param("step") Integer step);

    List<Audit> findByCommodityId(@Param("commodityId") String commodityId);

    int deleteByCommodityIdAndStep(@Param("commodityId") String commodityId, @Param("step") Integer step);

}