package com.cfcp.incc.service.payment;

import com.cfcp.incc.dao.PaymentDao;
import com.cfcp.incc.entity.Payment;
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
 * Created by zhyj on 2017/7/8.
 */
@Service
public class PaymentService extends BaseService{

    @Autowired
    private PaymentDao paymentDao;

    @Autowired
    private AuditService auditService;

    public int saveOrUpdate(Payment payment){
        if (payment == null){
            return 0;
        } else {
            int result = 0;
            if (StringUtils.hasLength(payment.getId())){
                result =  paymentDao.update(payment);
            } else {
                payment.setId(this.generateNumIdentifier());
                payment.setCreateTime(new Date());
                payment.setCreator(getCurrentUser().getId());
                result =paymentDao.insert(payment);
            }
            auditService.payment(payment.getCommodityId(), payment.getFee());
            return result;
        }
    }

    public PageInfo<Payment>  query(Map<String, String> conditions){
        Page page = this.initPage(conditions);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Payment> list =  paymentDao.query(conditions);
        PageInfo<Payment> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public Payment get(String id) {
        return paymentDao.get(id);
    }

    public int delete(String id) {
        return paymentDao.delete(id);
    }

//    public List<Payment> query(Distributor payment) {
//        return paymentDao.query(payment);
//    }
}
