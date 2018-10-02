package com.cfcp.incc.service.audit;

import com.cfcp.incc.dao.AuditDao;
import com.cfcp.incc.dao.PrecautionaryDao;
import com.cfcp.incc.entity.Audit;
import com.cfcp.incc.entity.Commodity;
import com.cfcp.incc.entity.Precautionary;
import com.cfcp.incc.entity.User;
import com.cfcp.incc.service.BaseService;
import com.cfcp.incc.service.certification.CertificationService;
import com.cfcp.incc.service.commodity.CommodityService;
import com.cfcp.incc.service.qrcode.QrCodeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhyj on 2017/7/8.
 */
@Service
public class PrecautionaryService extends BaseService{

    @Autowired
    private PrecautionaryDao precautionaryDao;



    public PageInfo<Precautionary> query(Map<String, String> conditions){
        User user = getCurrentUser();
        String distributorId = user.getDistributorId();
        String status = conditions.get("status");
        if (user==null)
            return new PageInfo(null);
        else if (!"20170820".equals(user.getDistributorId())){
            conditions.put("distributorId", user.getDistributorId());
        }
        Page page = this.initPage(conditions);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Precautionary> list =  precautionaryDao.query(conditions);
        PageInfo<Precautionary> pageInfo = new PageInfo(list);
        return pageInfo;
    }

}
