package com.cfcp.incc.controller.audit;

import com.cfcp.incc.controller.BaseController;
import com.cfcp.incc.dto.CommonDto;
import com.cfcp.incc.entity.Audit;
import com.cfcp.incc.service.audit.AuditService;
import com.cfcp.incc.service.qrcode.QrCodeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tigerfacejs.commons.view.DataEvent;

import java.util.List;
import java.util.Map;


/**
 * Created by zhyj on 2017/7/8.
 */
@RestController
@RequestMapping("manager/audit")
public class AuditController extends BaseController {

    @Autowired
    AuditService auditService;


    @RequestMapping(value = "/firstAccept", method = RequestMethod.POST)
    public Object addFirstAccept(@RequestBody Audit audit){
        if (auditService.firstAccept(audit) > 0 ){
            return DataEvent.wrap("firstAccept", new CommonDto<Audit>(audit));
        } else {
            return DataEvent.wrap("firstAccept", "保存失败");
        }
    }

    @RequestMapping(value = "/firstReject", method = RequestMethod.POST)
    public Object addFirstReject(@RequestBody Audit audit){
        if (auditService.firstReject(audit) > 0 ){
            return DataEvent.wrap("firstReject", new CommonDto<Audit>(audit));
        } else {
            return DataEvent.wrap("firstReject", "保存失败");
        }
    }

    @RequestMapping(value = "/reAccept", method = RequestMethod.POST)
    public Object addReAccept(@RequestBody Audit audit){
        if (auditService.reAccept(audit) > 0 ){
            return DataEvent.wrap("reAccept", new CommonDto<Audit>(audit));
        } else {
            return DataEvent.wrap("reAccept", "保存失败");
        }
    }

    @RequestMapping(value = "/reReject", method = RequestMethod.POST)
    public Object addReReject(@RequestBody Audit audit){
        if (auditService.reReject(audit) > 0 ){
            return DataEvent.wrap("reReject", new CommonDto<Audit>(audit));
        } else {
            return DataEvent.wrap("reReject", "保存失败");
        }
    }

    @RequestMapping(value = "/finalAccept", method = RequestMethod.POST)
    public Object addFinalAccept(@RequestBody Audit audit){
        if (auditService.finalAccept(audit) > 0 ){
            return DataEvent.wrap("finalAccept", new CommonDto<Audit>(audit));
        } else {
            return DataEvent.wrap("finalAccept", "保存失败");
        }
    }

    @RequestMapping(value = "/finalReject", method = RequestMethod.POST)
    public Object addFinalReject(@RequestBody Audit audit){
        if (auditService.finalReject(audit) > 0 ){
            return DataEvent.wrap("finalReject", new CommonDto<Audit>(audit));
        } else {
            return DataEvent.wrap("finalReject", "保存失败");
        }
    }

    @RequestMapping(value = "/findAudits", method = RequestMethod.POST)
    public Object findByCommodityId(@RequestParam String commodityId){

        List<Audit> list = auditService.findByCommodityId(commodityId);

        return DataEvent.wrap("findAudits", new CommonDto<>(list));

    }

    @RequestMapping(value = "/allCertification", method = RequestMethod.POST)
    public Object allCertification(){

        auditService.generateAllCertifications();
        return DataEvent.wrap("finalReject", new CommonDto<>());
    }



//    @RequestMapping(value = "query")
//    public Object query(@RequestParam(required = false) Map<String, String> conditions){
//        PageInfo<Audit> pageInfo= auditService.query(conditions);
//        return DataEvent.wrap("auditList", pageInfo);
//    }

//    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
//    public Object get(@PathVariable String id) {
//        Audit audit = auditService.get(id);
//        return DataEvent.wrap("audit", new CommonDto<Audit>(audit));
//    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public Object delete(@PathVariable String id){
//        auditService.delete(id);
//        return DataEvent.wrap("auditDeleted", new CommonDto<>(CommonDto.CommonResult.SUCCESS));
//    }
}
