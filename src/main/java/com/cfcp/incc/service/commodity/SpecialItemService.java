package com.cfcp.incc.service.commodity;

import com.cfcp.incc.dao.SpecialItemDao;
import com.cfcp.incc.entity.SpecialItem;
import com.cfcp.incc.entity.User;
import com.cfcp.incc.service.BaseService;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhyj on 2017/7/12.
 */
@Service
public class SpecialItemService extends BaseService {

    @Autowired
    SpecialItemDao specialItemDao;

    public int save(SpecialItem specialItem) {
        User user = getCurrentUser();
        specialItem.setCreator(user.getId());
        specialItem.setCreateTime(new Date());
        specialItem.setId(this.generateNumIdentifier());
        return specialItemDao.insert(specialItem);
    }

    public int delete(String id){
        return specialItemDao.delete(id);
    }

    public int updateCommodityId(String id, String commodityId){
        return specialItemDao.updateCommodityId(id, commodityId);
    }
    public List<SpecialItem> getByCommodityId(String commodityId){
        return specialItemDao.getByCommodityId(commodityId);
    }
}
