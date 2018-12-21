package com.cfcp.incc.service.orders.impl;

import com.cfcp.incc.dao.OrderPriceSystemDao;
import com.cfcp.incc.entity.OrderPriceSystem;
import com.cfcp.incc.service.orders.OrderPriceSystemService;
import com.cfcp.incc.utils.generator.GeneratorComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
        if(op!=null && op.getEndTimes()!=null){
            try {
                Date d = (Date) sf.parse(op.getEndTimes()+" 00:00:00");

                op.setEndTime(d);
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
}
