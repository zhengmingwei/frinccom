package com.cfcp.incc.controller.commodity;

import com.cfcp.incc.controller.BaseController;
import com.cfcp.incc.dto.CommonDto;
import com.cfcp.incc.entity.*;
import com.cfcp.incc.service.DictionaryService;
import com.cfcp.incc.service.audit.AuditService;
import com.cfcp.incc.service.commodity.*;
import com.cfcp.incc.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tigerfacejs.commons.view.DataEvent;

import javax.servlet.http.HttpServletRequest;
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

    @ModelAttribute("dictionarys")
    public Map populateTypes() {
        return dictionaryService.dictionariesMap();
    }


    @ResponseBody
    @RequestMapping(value = "/{commodityId}", method = RequestMethod.GET)
    public String addDistributor(@PathVariable String commodityId, Model model, HttpServletRequest request){

        /**
         * 得到访问者的IP地址
         * @return ip
         * @throws Exception
         */
        String ip1 =  WebUtils.getIpAddress();
        WebUtils u =new WebUtils();
        String ip1234 = (String) u.getMacAddrByIp(ip1);
        /**
         * 通过访问的Ip地址得到mac地址
         * @param ip
         * @return mac
         */
        String mac1 =  WebUtils.getMacAddress();


        String iip = WebUtils.getClientIp(request);

        String ip12341 = (String) u.getMacAddrByIp(iip);

        String imac = WebUtils.getMacByIp(iip);

        String imac1 =WebUtils.getIpAddr(request);
        String ima =WebUtils.getMACAddress(imac1);

        /**
         * 1.获得客户机信息
         */
        String requestUrl = request.getRequestURL().toString();//得到请求的URL地址
        String requestUri = request.getRequestURI();//得到请求的资源
        String queryString = request.getQueryString();//得到请求的URL地址中附带的参数
        String remoteAddr = request.getRemoteAddr();//得到来访者的IP地址
        String remoteHost = request.getRemoteHost();
        int remotePort = request.getRemotePort();
        String remoteUser = request.getRemoteUser();
        String method = request.getMethod();//得到请求URL地址时使用的方法
        String pathInfo = request.getPathInfo();
        String localAddr = request.getLocalAddr();//获取WEB服务器的IP地址
        String localName = request.getLocalName();//获取WEB服务器的主机名



//        if (commodityService.save(commodity) > 0 ){
        Map<String, Dictionary> dictionaryMap = dictionaryService.dictionariesMap();
        Commodity commodity = commodityService.get(commodityId);
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
        return "index";
    }

    public Object query(@RequestBody Distributor distributor){
//        List<Distributor> list = commodityService.queryery(distributor);distributor
        return DataEvent.wrap("distributor_list", null);
    }

}
