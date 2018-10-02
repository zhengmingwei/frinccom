package com.cfcp.incc.controller.commodity;

import com.cfcp.incc.controller.BaseController;
import com.cfcp.incc.dto.CommonDto;
import com.cfcp.incc.dto.CommonListDto;
import com.cfcp.incc.entity.Commodity;
import com.cfcp.incc.entity.Dictionary;
import com.cfcp.incc.entity.Distributor;
import com.cfcp.incc.entity.OtherQualification;
import com.cfcp.incc.form.CommoditySearchForm;
import com.cfcp.incc.service.DictionaryService;
import com.cfcp.incc.service.commodity.CommodityService;
import com.cfcp.incc.service.commodity.OtherQualificationService;
import com.cfcp.incc.service.commodity.SpecialItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tigerfacejs.commons.view.DataEvent;

import java.util.List;
import java.util.Map;


/**
 *
 * 全部 1 status > 0
 * 待审 2 status = 1
 * 审核中 3 1 < status <5
 * 终审通过 4 status = 5
 * 所有未通过 5 status < 0
 * 待一审（同待审和） 2 status = 1
 * 一审通过 6 status = 2
 * 一审未过 7 status = -2
 * 待缴费（一审通过） 6  status = 2
 * 已缴费（待复审） 8 status = 3
 * 复审通过（待终审） 9  status = 4
 * 复审未通过 10 status = -4
 * 终审未通过 11 status = -5
 * Created by zhyj on 2017/7/8.
 */
@RestController
@RequestMapping("/manager/commodity")
public class CommodityController extends BaseController {

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private SpecialItemService specialItemService;

    @Autowired
    private OtherQualificationService otherQualificationService;

    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping(method = RequestMethod.POST)
    public Object addCommodity(@RequestBody Commodity commodity){
//        if (commodityService.save(commodity) > 0 ){
        commodityService.save(commodity);
        return DataEvent.wrap("commodity", new CommonDto<Commodity>(commodity));
//        } else {
//            return DataEvent.wrap("commodity", "保存失败");
//        }
    }

    @RequestMapping(value = "query")
    public Object query(@RequestParam(required = false) Map<String, String> conditions) {
        PageInfo<Commodity> pageInfo=  commodityService.query(conditions);
        pageInfo.getList().forEach(item->item.setIndustryPo(dictionaryService.findDictionaryById(item.getIndustry())));
        return DataEvent.wrap("commodityList", pageInfo);
    }

    @RequestMapping(value = "options")
    public Object allMap() {
        return DataEvent.wrap("commodityOptions", commodityService.allMap());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable String id){
        commodityService.delete(id);
        return DataEvent.wrap("commodityDeleted", new CommonDto<>(CommonDto.CommonResult.SUCCESS));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Object get(@PathVariable String id){
        Commodity commodity = commodityService.get(id);
        if(commodity==null) return DataEvent.wrap("commodity", new CommonDto(CommonDto.CommonResult.FAILED));
        commodity.setSpecialItems(specialItemService.getByCommodityId(commodity.getId()));
        commodity.setOtherQualifications(otherQualificationService.getByCommodityId(commodity.getId()));
        this.populateDic(commodity);
        return DataEvent.wrap("commodity", new CommonDto<Commodity>(commodity));
    }

    private void populateDic(Commodity commodity){
        commodity.setCategoryPo(dictionaryService.findDictionaryById(commodity.getCategory()));
        commodity.setIndustryPo(dictionaryService.findDictionaryById(commodity.getIndustry()));
        commodity.getOtherQualifications().forEach(item->{item.setReportTypePo(dictionaryService.findDictionaryById(item.getReportType()));});
    }

    public Object query(@RequestBody Distributor distributor){
//        List<Distributor> list = commodityService.queryery(distributor);distributor
        return DataEvent.wrap("distributor_list", null);
    }

}
