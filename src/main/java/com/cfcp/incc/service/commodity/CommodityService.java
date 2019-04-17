package com.cfcp.incc.service.commodity;

import com.cfcp.incc.Constants;
import com.cfcp.incc.dao.CommodityDao;
import com.cfcp.incc.entity.*;
import com.cfcp.incc.form.CommoditySearchForm;
import com.cfcp.incc.service.BaseService;
import com.cfcp.incc.service.audit.AuditService;
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
 * Created by zhyj on 2017/7/3.
 */
@Service
public class CommodityService extends BaseService {

    @Autowired
    CommodityDao commodityDao;

    @Autowired
    BrandService brandService;

    @Autowired
    CompanyService companyService;

    @Autowired
    SpecialItemService specialItemService;

    @Autowired
    OtherQualificationService otherQualificationService;

    @Autowired
    AuditService auditService;

    public void save(Commodity commodity,String commodityId){
        Brand brand = commodity.getBrand();
        brandService.saveOrUpdate(brand);
        commodity.setBrandId(brand.getId());

        Company company = commodity.getCompany();

        if (company != null){

            company.setCompanyTypeStr(company.getCompanyType());
            String i = company.getCompanyTypeValue();
            if(company.getCompanyTypeValue()!=null){
                switch(i){
                    case "有限责任公司":
                            company.setCompanyType("140");
                        break;
                    case "股份有限公司":
                            company.setCompanyType("141");
                        break;
                    default:
                        company.setCompanyType("000");
                        break;
                }
            }



            /*
                                    <Option value="140">有限责任公司</Option>
                                    <Option value="141">股份有限公司</Option>*/
            company.setDescriminator(1);// 类型（1经营企业2生产企业）
            companyService.saveOrUpdate(company);
            commodity.setCompanyId(company.getId());
        }

        Company factory = commodity.getFactory();
        if (factory != null){
            factory.setCompanyTypeStr(factory.getCompanyType());
                String i = factory.getCompanyTypeValue();
                if(factory.getCompanyTypeValue()!=null) {
                    switch (i) {
                        case "有限责任公司":
                            factory.setCompanyType("140");
                            break;
                        case "股份有限公司":
                            factory.setCompanyType("141");
                            break;
                        default:
                            company.setCompanyType("000");
                            break;
                    }
                }


            factory.setDescriminator(2);// 类型（1经营企业2生产企业）
            companyService.saveOrUpdate(factory);
            commodity.setFactoryId(factory.getId());
        }

        if(StringUtils.hasLength(commodity.getId())){
            User user = getCurrentUser();
            commodity.setDistributorId(user.getDistributorId());
            Commodity commodity1 = commodityDao.get(commodity.getId());
            commodity.setStatus(commodity1.getStatus()); //原来的状态
            //commodity.setSp_video(commodity1.getSp_video());
            commodityDao.update(commodity);
        } else {
            commodity.setId(commodityId);
            User user = getCurrentUser();
            commodity.setCreator(user.getId());
            commodity.setDistributorId(user.getDistributorId());
            commodity.setCreateTime(new Date());
            commodity.setDistributorId(user.getDistributorId());
            commodityDao.insert(commodity);
        }

        auditService.init(commodity.getId());

        if(commodity.getSpecialItems() != null){
            updateSpecialItem(commodity.getSpecialItems(), commodity.getId());
        }

        if(commodity.getOtherQualifications() != null){
            updateOtherQualification(commodity.getOtherQualifications(), commodity.getId());
        }

    }
    
    private void updateSpecialItem(List<SpecialItem> list, String commodityId){
        list.forEach(specialItem ->specialItemService.updateCommodityId(specialItem.getId(), commodityId));
    }

    private void updateOtherQualification(List<OtherQualification> list, String commodityId){
        list.forEach(otherQualification ->otherQualificationService.updateCommodityId(otherQualification.getId(), commodityId));
    }

    public Commodity get(String commodityId){
        return commodityDao.get(commodityId);
    }

    public Commodity get_new(String commodityId){
        return commodityDao.get_new(commodityId);
    }

    public int updateIsPayIs(String commodityId){
        return commodityDao.updateIsPayIs(commodityId);
    }

    public PageInfo query(Map<String, String> conditions) {
        User user = getCurrentUser();
        String status = conditions.get("status");
        String distributorId = user.getDistributorId();
        if (user==null || !StringUtils.hasLength(distributorId) || !StringUtils.hasLength(status))
            return new PageInfo(null);
        int intStatus = Integer.parseInt(status);
        if ((intStatus < 8  || intStatus == 12) && !distributorId.equals(Constants.CENTRAL_OFFICE_DISTRIBUTOR))
            conditions.put("distributorId", distributorId);
        Page page = this.initPage(conditions);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Commodity> list =  commodityDao.query(conditions);
        PageInfo<Commodity> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public PageInfo query_(Map<String, String> conditions) {

        String status = conditions.get("status");
        String distributorId = "";
        int intStatus = Integer.parseInt(status);
        if ((intStatus < 8  || intStatus == 12) && !distributorId.equals(Constants.CENTRAL_OFFICE_DISTRIBUTOR))
            conditions.put("distributorId", distributorId);
        Page page = this.initPage(conditions);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Commodity> list =  commodityDao.query_(conditions);
        PageInfo<Commodity> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public int delete(String id){
        return commodityDao.delete(id);
    }

    public List<Map> allMap() {
        return commodityDao.allMap();
    }

    public int updateStatus(String id, Integer status){
        return commodityDao.updateStatus(id, status);
    }

    public List<Commodity> allAudited(){
        return commodityDao.allAudited();
    }
}
