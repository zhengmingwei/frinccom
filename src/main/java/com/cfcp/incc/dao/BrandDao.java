package com.cfcp.incc.dao;

import com.cfcp.incc.entity.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandDao {

    int delete(String id);

    int insert(Brand record);

    int insertSelective(Brand record);

    Brand get(String id);

    List<Brand> getByName(@Param("name") String name, @Param("distributorId") String distributorId);

    int updateSelective(Brand record);

    int update(Brand record);

    List<Brand> getBrandsByDistributor(String distributorId);

    Brand getByCommodityId(String commodityId);
}