package com.cfcp.incc.service.commodity;

import com.cfcp.incc.dao.OtherQualificationDao;
import com.cfcp.incc.entity.OtherQualification;
import com.cfcp.incc.entity.User;
import com.cfcp.incc.service.BaseService;
import com.cfcp.incc.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by zhyj on 2017/7/12.
 */
@Service
public class OtherQualificationService extends BaseService {

    @Autowired
    OtherQualificationDao qualificationDao;

    @Autowired
    DictionaryService dictionaryService;

    public int save(OtherQualification qualification) {
        User user = getCurrentUser();
        qualification.setCreator(user.getId());
        qualification.setCreateTime(new Date());
        qualification.setId(this.generateNumIdentifier());
        qualification.setReportTypePo(dictionaryService.findDictionaryById(qualification.getReportType()));
        return qualificationDao.insert(qualification);
    }

    public int delete(String id){
        return qualificationDao.delete(id);
    }

    public int updateCommodityId(String id, String commodityId){
        return qualificationDao.updateCommodityId(id, commodityId);
    }

    public List<OtherQualification> getByCommodityId(String commodityId){
        return qualificationDao.getByCommodityId(commodityId);
    }
}
