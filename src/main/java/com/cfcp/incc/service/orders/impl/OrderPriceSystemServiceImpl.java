package com.cfcp.incc.service.orders.impl;

import com.cfcp.incc.dao.OrderPriceSystemDao;
import com.cfcp.incc.entity.OrderPriceSystem;
import com.cfcp.incc.entity.OrderPriceSystem1;
import com.cfcp.incc.service.orders.OrderPriceSystemService;
import com.cfcp.incc.utils.generator.GeneratorComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderPriceSystemServiceImpl implements OrderPriceSystemService {
    @Autowired
    private OrderPriceSystemDao dao;

    @Override
    public List<OrderPriceSystem> queryAll01() {
        return dao.queryAll01();
    }

    @Override
    public List<OrderPriceSystem> queryAll() {
        return dao.queryAll();
    }

    @Override
    public List<OrderPriceSystem1> queryAll1(Map conditions) {
        return dao.queryAll1_1(conditions);
    }
    @Override
    public List<OrderPriceSystem1> queryAll1() {
        return dao.queryAll1();
    }
    @Override
    public OrderPriceSystem findOrderPriceSystemById(String id) {
        return dao.findOrderPriceSystemById(id);
    }

    @Override
    public OrderPriceSystem queryById(String productId) {
        return dao.findOrderPriceSystemById(productId);
    }

    @Override
    public OrderPriceSystem queryByScjrf() {
        return dao.queryByScjrf();
    }

    @Override
    public int saveOrUpdate(OrderPriceSystem op) {

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(op!=null && op.getEndTimes()!=null && op.getCreateTimes()!=null){
            try {
                Date d = (Date) sf.parse(op.getEndTimes()+" 00:00:00");
                Date cd = (Date) sf.parse(op.getCreateTimes()+" 00:00:00");
                op.setEndTime(d);
                op.setCreateTime(cd);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if("自动生成".equals(op.getId())){
            op.setId(GeneratorComparator.getGenerator(GeneratorComparator.GENERATOR_NUM).generate().toString());
            dao.insert(op);
        }else{
            dao.update(op);
        }
        return 0;
    }
    @Override
    public int saveOrUpdate1(OrderPriceSystem1 op) {

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(op!=null && op.getEndTime()!=null && op.getCreateTime()!=null){
            try {
                Date d = (Date) op.getEndTime();
                Date cd = (Date) op.getCreateTime();
                op.setEndTime(d);
                op.setCreateTime(cd);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if("自动生成".equals(op.getId()) || "".equals(op.getId())){
            op.setId(GeneratorComparator.getGenerator(GeneratorComparator.GENERATOR_NUM).generate().toString());
            return dao.insert(op);
        }else{
            return dao.update(op);
        }
    }
}
