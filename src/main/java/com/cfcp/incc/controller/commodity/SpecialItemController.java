package com.cfcp.incc.controller.commodity;

import com.cfcp.incc.controller.BaseController;
import com.cfcp.incc.dto.CommonDto;
import com.cfcp.incc.entity.Commodity;
import com.cfcp.incc.entity.Distributor;
import com.cfcp.incc.entity.SpecialItem;
import com.cfcp.incc.service.commodity.SpecialItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tigerfacejs.commons.view.DataEvent;


/**
 * Created by zhyj on 2017/7/8.
 */
@RestController
@RequestMapping("/manager/specialitem")
public class SpecialItemController extends BaseController {

    @Autowired
    private SpecialItemService specialItemService;

    @RequestMapping(method = RequestMethod.POST)
    public Object addDistributor(@RequestBody SpecialItem specialItem){
//        if (specialItemService.save(specialItem) > 0 ){
        specialItemService.save(specialItem);
        return DataEvent.wrap("specialItem", new CommonDto<SpecialItem>(specialItem));
//        } else {
//            return DataEvent.wrap("specialItem", "保存失败");
//        }
    }

    public Object query(@RequestBody Distributor distributor){
//        List<Distributor> list = specialItemService.queryery(distributor);distributor
        return DataEvent.wrap("distributor_list", null);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable String id){
        specialItemService.delete(id);
        return DataEvent.wrap("specialItemDeleted", new CommonDto<String>(id));
    }

}
