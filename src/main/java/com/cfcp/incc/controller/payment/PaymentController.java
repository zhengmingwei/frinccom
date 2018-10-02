package com.cfcp.incc.controller.payment;

import com.cfcp.incc.controller.BaseController;
import com.cfcp.incc.dto.CommonDto;
import com.cfcp.incc.entity.Payment;
import com.cfcp.incc.service.payment.PaymentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tigerfacejs.commons.view.DataEvent;

import java.util.Map;


/**
 * Created by zhyj on 2017/7/8.
 */
@RestController
@RequestMapping("manager/payment")
public class PaymentController extends BaseController {

    @Autowired
    PaymentService paymentService;

    @RequestMapping(method = RequestMethod.POST)
    public Object addPayment(@RequestBody Payment payment){
        if (paymentService.saveOrUpdate(payment) > 0 ){
            return DataEvent.wrap("payment", new CommonDto<Payment>(payment));
        } else {
            return DataEvent.wrap("payment", "保存失败");
        }
    }


    @RequestMapping(value = "query")
    public Object query(@RequestParam(required = false) Map<String, String> conditions){
        PageInfo<Payment> pageInfo= paymentService.query(conditions);
        return DataEvent.wrap("paymentList", pageInfo);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Object get(@PathVariable String id) {
        Payment payment = paymentService.get(id);
        return DataEvent.wrap("payment", new CommonDto<Payment>(payment));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable String id){
        paymentService.delete(id);
        return DataEvent.wrap("paymentDeleted", new CommonDto<>(CommonDto.CommonResult.SUCCESS));
    }
}
