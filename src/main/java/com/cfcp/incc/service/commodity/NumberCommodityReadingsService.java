package com.cfcp.incc.service.commodity;

import com.cfcp.incc.dao.NumberCommodityReadingsDao;
import com.cfcp.incc.entity.NumberCommodityReadings;
import com.cfcp.incc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class NumberCommodityReadingsService extends BaseService {

    @Autowired
    NumberCommodityReadingsDao dao;
    public int saveOrUpdate(String commodityId,String ip){
        NumberCommodityReadings ni =new NumberCommodityReadings();
        ni.setIp(ip);
        ni.setCommodityId(commodityId);
        NumberCommodityReadings n = dao.get(ni);

        if(n!=null && StringUtils.hasLength(n.getId())){
             n.setLatelyTime(new Date());
             n.setIp(ip);
             dao.update(n);
             return n.getTotal()+1;
        } else {
            n = new NumberCommodityReadings();
            n.setId(commodityId);
            n.setCreateTime(new Date());
            n.getLatelyTime(new Date());
            n.setCommodityId(commodityId);
            n.setTotal(1);
            n.setStatus(1);
            n.setIp(ip);
            dao.insert(n);
            return 1;
        }
    }

    public NumberCommodityReadings get(NumberCommodityReadings ni){
        return dao.get(ni);
    }
}
