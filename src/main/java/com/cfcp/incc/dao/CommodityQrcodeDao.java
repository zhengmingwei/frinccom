package com.cfcp.incc.dao;

import com.cfcp.incc.entity.CommodityQrcode;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommodityQrcodeDao {
    int delete(String id);

    int insert(CommodityQrcode record);

    CommodityQrcode get(String id);

    int updateSelective(CommodityQrcode record);

    int updateIsQrcode(String id);

    int update(CommodityQrcode record);

    List<CommodityQrcode> query(Map<String, String> conditions);

    List<CommodityQrcode> query_(Map<String, String> conditions);

    List<CommodityQrcode> allAudited();


    int updateStatus(@Param("id") String id, @Param("status") Integer status);
    int updateUsedQrcodeTime(@Param("id") String id, @Param("status") Integer status, @Param("isQrcode") Integer isQrcode);
    int updateFinalTime(@Param("id") String id, @Param("status") Integer status);
}