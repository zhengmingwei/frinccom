package com.cfcp.incc.service.audit;

import com.cfcp.incc.dao.AuditDao;
import com.cfcp.incc.dao.AuditDao;
import com.cfcp.incc.entity.Audit;
import com.cfcp.incc.entity.Commodity;
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

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhyj on 2017/7/8.
 */
@Service
public class AuditService extends BaseService{

    @Autowired
    private AuditDao auditDao;

    @Autowired
    private CommodityService commodityService;

    @Autowired
    QrCodeService qrCodeService;

    @Autowired
    CertificationService certificationService;


    public int init(String commodityId){
        Audit audit = new Audit();
        audit.setCommodityId(commodityId);
        audit.setCreateTime(new Date());
        audit.setAuditDate(audit.getCreateTime());
        audit.setStep(1);
        audit.setCreator(getCurrentUser().getId());
        audit.setResult(Audit.RESULT_SUCCESS);
        Audit orignAudit = auditDao.findByCommodityIdAndStep(commodityId, 1);
        if( orignAudit != null){
            return auditDao.update(audit);
        } else {
            audit.setId(this.generateNumIdentifier());
            return auditDao.insert(audit);
        }

    }

    /**
     * 一审通过
     * @param audit
     * @return
     */
    public int firstAccept(Audit audit){
        if (audit == null){
            return 0;
        } else {
            audit.setId(this.generateNumIdentifier());
            audit.setCreateTime(new Date());
            audit.setCreator(getCurrentUser().getId());
            audit.setResult(Audit.RESULT_SUCCESS);
            audit.setAuditDate(audit.getCreateTime());
            audit.setStep(2);
            //fixme 临时用当前时间，加上终审后修改成正式的
//            audit.setBeginDate(new Date());
//            Calendar rightNow = Calendar.getInstance();
//            rightNow.setTime(new Date());
//            rightNow.add(Calendar.YEAR,1);
//            audit.setExpireDate(rightNow.getTime());
            String commodityId = audit.getCommodityId();
            int status = Commodity.STATUS_FIRST;
            if ( isPayed(commodityId)){
                status = Commodity.STATUS_SECOND;
            }
            commodityService.updateStatus(commodityId, status);
            auditDao.deleteByCommodityIdAndStep(commodityId, audit.getStep());
            return auditDao.insert(audit);
        }
    }

    /**
     *
     * @param audit
     * @return
     */
    public int firstReject(Audit audit){
        if (audit == null){
            return 0;
        } else {
            audit.setId(this.generateNumIdentifier());
            audit.setResult(Audit.RESULT_FAILURE);
            audit.setCreateTime(new Date());
            audit.setAuditDate(audit.getCreateTime());
            audit.setStep(2);
            audit.setCreator(getCurrentUser().getId());
            commodityService.updateStatus(audit.getCommodityId(), Commodity.STATUS_FIRST_FAILED);
            auditDao.deleteByCommodityIdAndStep(audit.getCommodityId(),  Commodity.STATUS_FIRST_FAILED);
            return auditDao.insert(audit);
        }
    }

    /**
     * 二审通过(缴费)
     *
     * @return
     */
    public int payment(String commodityId, Double fee){
        Audit audit = new Audit();
        audit.setCommodityId(commodityId);
        audit.setCreateTime(new Date());
        audit.setAuditDate(audit.getCreateTime());
        audit.setStep(3);
        audit.setFee(fee);
        audit.setCreator(getCurrentUser().getId());
        audit.setResult(Audit.RESULT_SUCCESS);
        commodityService.updateStatus(audit.getCommodityId(), Commodity.STATUS_SECOND);
        Audit orignAudit = auditDao.findByCommodityIdAndStep(commodityId, 3);
        if( orignAudit != null){
            return auditDao.update(audit);
        } else {
            audit.setId(this.generateNumIdentifier());
            return auditDao.insert(audit);
        }

    }

    /**
     * 复审通过
     *
     * @return
     */
    public int reAccept(Audit audit){
        audit.setId(this.generateNumIdentifier());
        audit.setCreateTime(new Date());
        audit.setCreator(getCurrentUser().getId());
        audit.setAuditDate(audit.getCreateTime());
        audit.setStep(4);
        audit.setResult(Audit.RESULT_SUCCESS);
        commodityService.updateStatus(audit.getCommodityId(), Commodity.STATUS_THIRD);
        auditDao.deleteByCommodityIdAndStep(audit.getCommodityId(), Commodity.STATUS_THIRD);
        return auditDao.insert(audit);
    }

    /**
     * 复审拒绝
     *
     * @return
     */
    public int reReject(Audit audit){
        audit.setId(this.generateNumIdentifier());
        audit.setCreateTime(new Date());
        audit.setAuditDate(audit.getCreateTime());
        audit.setCreator(getCurrentUser().getId());
        audit.setResult(Audit.RESULT_FAILURE);
        audit.setStep(4);
        commodityService.updateStatus(audit.getCommodityId(), Commodity.STATUS_RE_FAILED);
        auditDao.deleteByCommodityIdAndStep(audit.getCommodityId(), Commodity.STATUS_RE_FAILED);
        return auditDao.insert(audit);
    }

    /**
     * 终审通过
     *
     * @return
     */
    public int finalAccept(Audit audit){
        audit.setId(this.generateNumIdentifier());
        audit.setCreateTime(new Date());
        audit.setCreator(getCurrentUser().getId());
        audit.setResult(Audit.RESULT_SUCCESS);
//        audit.setAuditDate(audit.getCreateTime());
        audit.setStep(5);
        qrCodeService.generate(audit.getCommodityId());
        commodityService.updateStatus(audit.getCommodityId(), Commodity.STATUS_FINAL);
        auditDao.deleteByCommodityIdAndStep(audit.getCommodityId(), Commodity.STATUS_FINAL);
        Commodity commodity = commodityService.get(audit.getCommodityId());
        certificationService.generate(commodity, audit);
        return auditDao.insert(audit);
    }

    public void generateAllCertifications(){
        List<Commodity> list = commodityService.allAudited();
        for (Commodity commodity: list) {
            Audit audit = auditDao.findByCommodityIdAndStep(commodity.getId(), 5);
            certificationService.generate(commodity, audit);
        }
    }

    /**
     * 终审拒绝
     *
     * @return
     */
    public int finalReject(Audit audit){
        audit.setId(this.generateNumIdentifier());
        audit.setCreateTime(new Date());
        audit.setCreator(getCurrentUser().getId());
        audit.setResult(Audit.RESULT_FAILURE);
        audit.setStep(5);
        commodityService.updateStatus(audit.getCommodityId(), Commodity.STATUS_FINAL_FAILED);
        auditDao.deleteByCommodityIdAndStep(audit.getCommodityId(), Commodity.STATUS_FINAL_FAILED);
        return auditDao.insert(audit);
    }

    public List<Audit> findByCommodityId(String commodityId){
        return auditDao.findByCommodityId(commodityId);
    }
//    public PageInfo<Audit>  query(Map<String, String> conditions){
//        Page page = this.initPage(conditions);
//        PageHelper.startPage(page.getPageNum(), page.getPageSize());
//        List<Audit> list =  auditDao.query(conditions);
//        PageInfo<Audit> pageInfo = new PageInfo(list);
//        return pageInfo;
//    }

    public Audit get(String id) {
        return auditDao.get(id);
    }

    public Audit getLast(String id) {
        return auditDao.getLast(id);
    }

    public int delete(String id) {
        return auditDao.delete(id);
    }

//    public List<Audit> query(Distributor audit) {
//        return auditDao.query(audit);
//    }

    public boolean isPayed(String commodityId){
        return auditDao.findByCommodityIdAndStep(commodityId, Commodity.STATUS_SECOND) != null;
    }

}
