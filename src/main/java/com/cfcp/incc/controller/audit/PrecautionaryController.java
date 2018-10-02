package com.cfcp.incc.controller.audit;

import com.cfcp.incc.controller.BaseController;
import com.cfcp.incc.entity.Precautionary;
import com.cfcp.incc.service.audit.PrecautionaryService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tigerfacejs.commons.view.DataEvent;

import java.util.Map;

/**
 * Created by zhyj on 2017/9/24.
 */
@Controller
@RequestMapping("manager/precautionary/")
public class PrecautionaryController extends BaseController{

    @Autowired
    PrecautionaryService precautionaryService;

    @RequestMapping("query")
    public Object query(@RequestParam(required = false) Map<String, String> conditions){
        PageInfo<Precautionary> pageInfo=  precautionaryService.query(conditions);
        return DataEvent.wrap("precautionaryList", pageInfo);

    }
}
