package com.cfcp.incc.controller.orders;

import com.cfcp.incc.entity.OrderPriceSystem;
import com.cfcp.incc.service.orders.OrderPriceSystemService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tigerfacejs.commons.view.DataEvent;

@RestController
@RequestMapping("manager/orderPriceSystem")
public class OrderPriceSystemController {

    @Autowired
    private OrderPriceSystemService service;


    @RequestMapping(value = "query")
    public Object query() {
        PageInfo<OrderPriceSystem> pageInfo= (PageInfo<OrderPriceSystem>) service.queryAll();
        //pageInfo.getList().forEach(item->item.setIndustryPo(dictionaryService.findDictionaryById(item.getIndustry())));
        return DataEvent.wrap("orderPriceSystemList", pageInfo);
    }

}
