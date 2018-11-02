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
    public int saveOrUpdate(String commodityId){
        NumberCommodityReadings n = dao.get(commodityId);
        if(n!=null && StringUtils.hasLength(n.getId())){
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
            dao.insert(n);
            return 1;
        }
    }

    public NumberCommodityReadings get(String id){
        return dao.get(id);
    }
}
