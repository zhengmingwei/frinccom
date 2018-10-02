package com.cfcp.incc.controller.distributor;

import com.cfcp.incc.controller.BaseController;
import com.cfcp.incc.dto.CommonDto;
import com.cfcp.incc.entity.Distributor;
import com.cfcp.incc.service.distributor.DistributorService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tigerfacejs.commons.view.DataEvent;

import java.util.Map;


/**
 * Created by zhyj on 2017/7/8.
 */
@RestController
@RequestMapping("manager/distributor")
public class DistributorController extends BaseController {

    @Autowired
    DistributorService distributorService;

    @RequestMapping(method = RequestMethod.POST)
    public Object addDistributor(@RequestBody Distributor distributor) {
        if (distributorService.saveOrUpdate(distributor) > 0) {
            return DataEvent.wrap("distributor", new CommonDto<Distributor>(distributor));
        } else {
            return DataEvent.wrap("distributor", "保存失败");
        }
    }


    @RequestMapping(value = "query")
    public Object query(@RequestParam(required = false) Map<String, String> conditions) {
        PageInfo<Distributor> pageInfo = distributorService.query(conditions);
        return DataEvent.wrap("distributorList", pageInfo);
    }

    @RequestMapping(value = "options")
    public Object allMap() {
        return DataEvent.wrap("distributorOptions", distributorService.allMap());
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Object get(@PathVariable String id) {
        Distributor distributor = distributorService.get(id);
        return DataEvent.wrap("distributor", new CommonDto<Distributor>(distributor));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable String id){
        distributorService.delete(id);
        return DataEvent.wrap("distributorDeleted", new CommonDto<Distributor>(CommonDto.CommonResult.SUCCESS));
    }

}
