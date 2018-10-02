package com.cfcp.incc.controller.commodity;

import com.cfcp.incc.controller.BaseController;
import com.cfcp.incc.dto.CommonDto;
import com.cfcp.incc.entity.Distributor;
import com.cfcp.incc.entity.OtherQualification;
import com.cfcp.incc.entity.SpecialItem;
import com.cfcp.incc.service.commodity.OtherQualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tigerfacejs.commons.view.DataEvent;


/**
 * Created by zhyj on 2017/7/8.
 */
@RestController
@RequestMapping("manager/otherqualification")
public class OtherQualificationController extends BaseController {

    @Autowired
    private OtherQualificationService otherQualificationService;

    @RequestMapping(method = RequestMethod.POST)
    public Object addDistributor(@RequestBody OtherQualification otherQualification){
//        if (otherQualificationService.save(otherQualification) > 0 ){
        otherQualificationService.save(otherQualification);
        return DataEvent.wrap("otherQualification", new CommonDto<OtherQualification>(otherQualification));
//        } else {
//            return DataEvent.wrap("otherQualification", "保存失败");
//        }
    }

    public Object query(@RequestBody Distributor distributor){
//        List<Distributor> list = otherQualificationService.queryery(distributor);distributor
        return DataEvent.wrap("distributor_list", null);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable String id){
        otherQualificationService.delete(id);
        return DataEvent.wrap("otherQualificationDeleted", new CommonDto<String>(id));
    }

}
