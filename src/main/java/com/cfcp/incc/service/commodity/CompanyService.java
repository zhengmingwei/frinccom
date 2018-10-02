package com.cfcp.incc.service.commodity;

import com.cfcp.incc.dao.CompanyDao;
import com.cfcp.incc.entity.Commodity;
import com.cfcp.incc.entity.Company;
import com.cfcp.incc.entity.User;
import com.cfcp.incc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * Created by zhyj on 2017/7/5.
 */
@Service
public class CompanyService extends BaseService {

    @Autowired
    CompanyDao companyDao;

    public Company getByName(String name){
//        return companyDao.getByName(name);
        return null;
    }

    public int saveOrUpdate(Company company){
        if(StringUtils.hasLength(company.getId())){
            return companyDao.update(company);
        } else {
            User user = getCurrentUser();
            company.setCreator(user.getId());
//            company.setDistributorId(user.getDistributorId());
            company.setCreateTime(new Date());
            company.setId(this.generateNumIdentifier());
            company.setStatus(1);
            return companyDao.insert(company);
        }
    }

    public Company get(String id){
        return companyDao.get(id);
    }
}
