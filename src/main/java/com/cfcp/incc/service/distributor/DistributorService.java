package com.cfcp.incc.service.distributor;

import com.cfcp.incc.dao.DistributorDao;
import com.cfcp.incc.entity.Distributor;
import com.cfcp.incc.service.BaseService;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by zhyj on 2017/7/8.
 */
@Service
public class DistributorService extends BaseService{

    @Autowired
    private DistributorDao distributorDao;

    public int saveOrUpdate(Distributor distributor){
        if (distributor == null){
            return 0;
        } else {
            if (StringUtils.hasLength(distributor.getId())){
                return distributorDao.updateSelective(distributor);
            } else {
                distributor.setId(this.generateNumIdentifier());
                distributor.setCreator(getCurrentUser().getId());
                distributor.setStatus(1);
                distributor.setCreateTime(new Date());
                return distributorDao.insert(distributor);
            }
        }
    }

    public PageInfo<Distributor>  query(Map<String, String> conditions){
        Page page = this.initPage(conditions);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Distributor> list =  distributorDao.query(conditions);
        PageInfo<Distributor> pageInfo = new PageInfo(list);
        return pageInfo;
    }

//    public List<Distributor> query(Distributor distributor) {
//        return distributorDao.query(distributor);
//    }

    public List<Map> allMap(){
        List<Map> allMap = distributorDao.allMap();
        return distributorDao.allMap();
    }

    public Distributor get(String id) {
        return distributorDao.get(id);
    }

    public int delete(String id) {
        return distributorDao.delete(id);
    }
}
