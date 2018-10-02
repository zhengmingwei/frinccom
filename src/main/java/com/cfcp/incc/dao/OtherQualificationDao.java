package com.cfcp.incc.dao;

import com.cfcp.incc.entity.OtherQualification;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OtherQualificationDao {
    int delete(String id);

    int insert(OtherQualification record);

    int insertSelective(OtherQualification record);

    OtherQualification get(String id);

    int updateSelective(OtherQualification record);

    int update(OtherQualification record);

    int updateCommodityId(@Param("id") String id, @Param("commodityId") String commodityId);

    List<OtherQualification> getByCommodityId(String commodityId);
}