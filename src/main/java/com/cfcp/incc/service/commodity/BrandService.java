package com.cfcp.incc.service.commodity;

import com.cfcp.incc.dao.BrandDao;
import com.cfcp.incc.entity.Brand;
import com.cfcp.incc.entity.User;
import com.cfcp.incc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by zhyj on 2017/7/2.
 */
@Service
public class BrandService extends BaseService{

    @Autowired
    BrandDao brandDao;

    public int insert(Brand brand){
        return brandDao.insert(brand);
    }

    /**
     * 查询品牌
     * @param name 品牌名称
     * @param distributorId 分销商
     * @return
     */
    public List<Brand> getByName(String name, String distributorId) {
        return brandDao.getByName(name, distributorId);
    }

    /**
     * 查询某经销商的品牌
     * @param distributorId
     * @return
     */
    public List<Brand> getBrandsByDistributor(String distributorId){
        return brandDao.getBrandsByDistributor(distributorId);
    }

    public int saveOrUpdate(Brand brand){
        if (brand == null) return 0;
        if (StringUtils.hasLength(brand.getId())) {
            return brandDao.update(brand);
        } else {
            brand.setId(generateNumIdentifier());
            User user = getCurrentUser();
            brand.setCreator(user.getId());
            brand.setDistributorId(user.getDistributorId());
            brand.setCreateTime(new Date());
            return brandDao.insert(brand);       }
    }
    public int delete(String id){
        return brandDao.delete(id);
    }

    public Brand get(String id) {
        return brandDao.get(id);
    }

    public Brand getByCommodityId(String commodityId){
        return brandDao.getByCommodityId(commodityId);
    }

}
