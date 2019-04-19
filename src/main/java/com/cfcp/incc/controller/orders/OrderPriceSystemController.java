package com.cfcp.incc.controller.orders;

import com.cfcp.incc.dto.CommonDto;
import com.cfcp.incc.entity.OrderPriceSystem;
import com.cfcp.incc.entity.OrderPriceSystem1;
import com.cfcp.incc.service.orders.OrderPriceSystemService;
import com.cfcp.incc.utils.DateUtils;
import com.cfcp.incc.utils.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tigerfacejs.commons.view.DataEvent;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("manager/orderPriceSystem")
public class OrderPriceSystemController {

    @Autowired
    private OrderPriceSystemService service;



    /**
     * 新增或更新价格体系信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addOrUpdateOrderPackages")
    @ResponseBody
    public JsonResult AddOrUpdateOrderPackages(@RequestBody OrderPriceSystem1 r) throws Exception {

        JsonResult jsonResult = new JsonResult();
        List<OrderPriceSystem> oList = null;
        if(!"".equals(r.getCreateTimes()) && r.getCreateTimes()!=null
                && r.getEndTimes()!=null && !"".equals(r.getEndTimes())
                && r.getName()!=null && !"".equals(r.getName())
                && r.getDescribe()!=null && !"".equals(r.getDescribe())
                && r.getPrice()!=null && !"".equals(r.getPrice())
                && r.getTotal()!=null && !"".equals(r.getTotal())
        ){
            service.saveOrUpdate1(r);
            jsonResult.setStatus(true);
        }else{
            jsonResult.setMessage("不允许空值提交，填入数据");
            jsonResult.setStatus(false);
        }

        jsonResult.setMessage("OK");
        return jsonResult;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addDictionary(@RequestBody OrderPriceSystem1 orderPriceSystem) {

        if(orderPriceSystem.getCreateTimes()!=null && !orderPriceSystem.getCreateTimes().equals("")){
            String sfm = orderPriceSystem.getCreateTimes().substring(12,20);
            String sfrm = orderPriceSystem.getCreateTimes().substring(11,19);
            orderPriceSystem.setCreateTime(com.cfcp.incc.utils.DateUtils.parseFormat_DateTime(orderPriceSystem.getCreateTimes().substring(0,10)+" "+orderPriceSystem.getCreateTimes().substring(11,19)));
        }
        if(orderPriceSystem.getEndTimes()!=null && !orderPriceSystem.getEndTimes().equals("")){
            String ss = orderPriceSystem.getEndTimes().substring(0,10)+" "+orderPriceSystem.getEndTimes().substring(11,19);
            Date d = com.cfcp.incc.utils.DateUtils.parseFormat_DateTime(orderPriceSystem.getEndTimes().substring(0,10)+" "+orderPriceSystem.getEndTimes().substring(11,19));

            orderPriceSystem.setEndTime(com.cfcp.incc.utils.DateUtils.parseFormat_DateTime(orderPriceSystem.getEndTimes().substring(0,10)+" "+orderPriceSystem.getEndTimes().substring(11,19)));
        }

        if(        orderPriceSystem.getCreateTime()!=null && !"".equals(orderPriceSystem.getCreateTime())
                && orderPriceSystem.getEndTime()!=null    && !"".equals(orderPriceSystem.getEndTime())
                && orderPriceSystem.getName()!=null       && !"".equals(orderPriceSystem.getName())
                && orderPriceSystem.getDescribe()!=null   && !"".equals(orderPriceSystem.getDescribe())
                && orderPriceSystem.getPrice()!=null      && !"".equals(orderPriceSystem.getPrice())
                && orderPriceSystem.getTotal()!=null      && !"".equals(orderPriceSystem.getTotal())
                && service.saveOrUpdate1(orderPriceSystem) > 0)
        {
            return DataEvent.wrap("orderPriceSystem", new CommonDto<OrderPriceSystem1>(orderPriceSystem));
        } else {
            return DataEvent.wrap("orderPriceSystem", "保存失败");
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Object get(@PathVariable String id){
        System.out.println("+++++++++++"+id);
        OrderPriceSystem dictionary = service.findOrderPriceSystemById(id);
        return DataEvent.wrap("orderPriceSystem", new CommonDto<OrderPriceSystem>(dictionary));
    }
    @RequestMapping(value = "query")
    public Object query() {
        PageInfo<OrderPriceSystem> pageInfo= (PageInfo<OrderPriceSystem>) service.queryAll();
        //pageInfo.getList().forEach(item->item.setIndustryPo(dictionaryService.findDictionaryById(item.getIndustry())));
        return DataEvent.wrap("orderPriceSystemList", pageInfo);
    }

    @RequestMapping(value = "query2")
    public Object query2(@RequestParam(required = false) Map<String, String> conditions) {
        List<OrderPriceSystem1> pageInfo= (List<OrderPriceSystem1>) service.queryByName(conditions);
        List ls = new ArrayList();
        //java8版本
       // pageInfo.forEach(item->{
            for(int i = 0; i < pageInfo.size(); i++){
                OrderPriceSystem1 item = pageInfo.get(i);
              Date createTime = item.getCreateTime();
              String createTimes =(new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(createTime); // dtf.format((TemporalAccessor) createTime);
              item.setCreateTimes(createTimes);
              Date endTime = item.getEndTime();
              String endTimes = (new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(endTime); ;
              item.setEndTimes(endTimes);
            ls.add(item);
                System.out.println(createTimes +"  ;   "+endTimes);
            }
       // });
        //pageInfo.getList().forEach(item->item.setIndustryPo(dictionaryService.findDictionaryById(item.getIndustry())));
        System.out.println();
        return DataEvent.wrap("orderPriceSystemList", ls);
    }
    @RequestMapping(value = "queryBuy")
    public Object queryBuy(@RequestParam(required = false) Map<String, String> conditions) {
        List<OrderPriceSystem1> pageInfo= (List<OrderPriceSystem1>) service.queryByName(conditions);
        List ls = new ArrayList();
        //java8版本
        // pageInfo.forEach(item->{
        for(int i = 0; i < pageInfo.size(); i++){
            OrderPriceSystem1 item = pageInfo.get(i);
            Date createTime = item.getCreateTime();
            String createTimes =(new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(createTime); // dtf.format((TemporalAccessor) createTime);
            item.setCreateTimes(createTimes);
            Date endTime = item.getEndTime();
            String endTimes = (new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(endTime); ;
            item.setEndTimes(endTimes);
            ls.add(item);
            System.out.println(createTimes +"  ;   "+endTimes);
        }
        // });
        //pageInfo.getList().forEach(item->item.setIndustryPo(dictionaryService.findDictionaryById(item.getIndustry())));
        System.out.println();
        return DataEvent.wrap("orderPriceSystemList", ls);
    }
}
