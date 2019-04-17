package com.cfcp.incc.controller.commodity;

import com.cfcp.incc.controller.BaseController;
import com.cfcp.incc.dto.CommonDto;
import com.cfcp.incc.entity.*;
import com.cfcp.incc.service.DictionaryService;
import com.cfcp.incc.service.audit.AuditService;
import com.cfcp.incc.service.commodity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tigerfacejs.commons.view.DataEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * Created by zhyj on 2017/7/8.
 */
@Controller
@RequestMapping("/commodity")
public class CommodityDispController extends BaseController {

    @Autowired
    private CommodityService commodityService;

//    @Autowired
//    private BrandService brandService;
//
//    @Autowired
//    private CompanyService companyService;

    @Autowired
    private SpecialItemService specialItemService;

    @Autowired
    private OtherQualificationService otherQualificationService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private AuditService auditService;

    @Autowired
    private NumberCommodityReadingsService numberCommodityRService;

    @ModelAttribute("dictionarys")
    public Map populateTypes() {
        return dictionaryService.dictionariesMap();
    }

    /*
    扫描 二维码  ：视频 图片 分开显示
     */
    @RequestMapping(value = "/{commodityId}", method = RequestMethod.GET)
    public String addDistributor(HttpServletResponse response, HttpServletRequest request, @PathVariable String commodityId, Model model){
//        if (commodityService.save(commodity) > 0 ){
        String ip = getIpAddr(request);
        int numberCommodityReadings = numberCommodityRService.saveOrUpdate(commodityId,ip);
        model.addAttribute("numberCommodityReadings", numberCommodityReadings);

        Map<String, Dictionary> dictionaryMap = dictionaryService.dictionariesMap();
        Commodity commodity = commodityService.get(commodityId);
        if(commodity!=null && commodity.getStatus()==0){//删除 状态 不允许 再扫描出
            return "index";
        }
        commodity.setIndustryPo(dictionaryMap.get(commodity.getIndustry()));
        commodity.setCategoryPo(dictionaryMap.get(commodity.getCategory()));
        model.addAttribute("commodity", commodity);
        model.addAttribute("specialItems", specialItemService.getByCommodityId(commodityId));
        List<OtherQualification> otherQualifications = otherQualificationService.getByCommodityId(commodityId);
        otherQualifications.forEach((otherQualification)-> otherQualification.setReportTypePo(dictionaryMap.get(otherQualification.getReportType().toString())));
        model.addAttribute("otherQualifications", otherQualifications);
        if (commodity != null) {
            model.addAttribute("brand", commodity.getBrand());
            model.addAttribute("company", commodity.getCompany());
            model.addAttribute("factory", commodity.getFactory());
        }
        model.addAttribute("audit", auditService.getLast(commodityId));
        System.out.println(commodity.getSp_video());
        if(commodity.getSp_video()!=null && !commodity.getSp_video().equals("")){
            System.out.println(commodity.getSp_video());
            return "index_video";
        }else{
            System.out.println(commodity.getSp_video());
            return "index";
        }
    }

    public Object query(@RequestBody Distributor distributor){
//        List<Distributor> list = commodityService.queryery(distributor);distributor
        return DataEvent.wrap("distributor_list", null);
    }

    //获得客户端真实IP地址的方法二：
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
