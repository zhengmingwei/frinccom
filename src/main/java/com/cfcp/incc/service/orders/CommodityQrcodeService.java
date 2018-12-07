package com.cfcp.incc.service.orders;

import com.cfcp.incc.entity.CommodityQrcode;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommodityQrcodeService {

    public int delete(String id);

    public int insert(CommodityQrcode record);

    public CommodityQrcode get(String id);

    public int updateSelective(CommodityQrcode record);

    public int updateIsQrcode(String id);

    public int update(CommodityQrcode record);

    public List<CommodityQrcode> query(Map<String, String> conditions);

    public List<CommodityQrcode> query_(Map<String, String> conditions);

    public List<CommodityQrcode> allAudited();


    public int updateUsedQrcodeTime(String id,Integer status,Integer isQrcode);
    public int updateFinalTime(String id,Integer status);
    public int updateStatus(@Param("id") String id, @Param("status") Integer status);
}
