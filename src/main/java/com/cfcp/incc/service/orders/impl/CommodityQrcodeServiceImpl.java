package com.cfcp.incc.service.orders.impl;

import com.cfcp.incc.dao.CommodityQrcodeDao;
import com.cfcp.incc.entity.CommodityQrcode;
import com.cfcp.incc.service.orders.CommodityQrcodeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommodityQrcodeServiceImpl implements CommodityQrcodeService {

    @Autowired
    private CommodityQrcodeDao dao;

    @Override
    public int delete(String id) {
        return dao.delete(id);
    }

    @Override
    public int insert(CommodityQrcode r) {
        return dao.insert(r);
    }

    @Override
    public CommodityQrcode get(String id) {
        return dao.get(id);
    }

    @Override
    public int updateSelective(CommodityQrcode r) {
        return dao.updateSelective(r);
    }

    @Override
    public int updateIsQrcode(String id) {
        return dao.updateIsQrcode(id);
    }

    @Override
    public int update(CommodityQrcode r) {
        return dao.update(r);
    }

    @Override
    public List<CommodityQrcode> query(Map<String, String> c) {
        return dao.query(c);
    }

    @Override
    public List<CommodityQrcode> query_(Map<String, String> c) {
        return dao.query_(c);
    }

    @Override
    public List<CommodityQrcode> allAudited() {
        return dao.allAudited();
    }

    @Override
    public int updateUsedQrcodeTime(String id, Integer status, Integer isQrcode) {
        return dao.updateUsedQrcodeTime(id,status,isQrcode);
    }

    @Override
    public int updateFinalTime(String id, Integer status) {
        return dao.updateFinalTime(id,status);
    }

    @Override
    public int updateStatus(String id, Integer status) {
        return dao.updateStatus(id,status);
    }


}
