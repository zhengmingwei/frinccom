package com.cfcp.incc.controller.commodity;

import com.cfcp.incc.controller.BaseController;
import com.cfcp.incc.dto.CommonDto;
import com.cfcp.incc.dto.CommonListDto;
import com.cfcp.incc.dto.StringDto;
import com.cfcp.incc.entity.*;
import com.cfcp.incc.form.CommoditySearchForm;
import com.cfcp.incc.security.UserContext;
import com.cfcp.incc.service.DictionaryService;
import com.cfcp.incc.service.commodity.CommodityService;
import com.cfcp.incc.service.commodity.OtherQualificationService;
import com.cfcp.incc.service.commodity.SpecialItemService;
import com.cfcp.incc.service.orders.OrderPackageService;
import com.cfcp.incc.service.orders.impl.CommodityQrcodeServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.tigerfacejs.commons.view.DataEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


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

    @Autowired
    private OrderPackageService orderPackageService;

    @Autowired
    private CommodityQrcodeServiceImpl commodityQrcodeService;

    //#是否收费开关参数 0：不收费；1：收费 ；默认收费 在配置文件中配置的开关
    @Value("${pay.is_charge}")
    private String pay_is_charge;

    @RequestMapping(method = RequestMethod.POST)
    public Object addCommodity(@RequestBody Commodity commodity, HttpServletRequest request, HttpServletRequest response){
        String commodityId = "";
        if(org.springframework.util.StringUtils.hasLength(commodity.getId())){
            commodityId = commodity.getId();
        }else{
            commodityId = commodityService.generateNumIdentifier();
        }
        User user = UserContext.getCurrentUser();
        //根据 用户ID查询 当前登录人的购买二维码的剩余数量
        OrderPackage p = orderPackageService.findSumSutplusQuantityByUserId( user.getId());
        CommodityQrcode cq = commodityQrcodeService.get(commodityId);

        //管理员  略过 缴费的情况，是不用缴费的
        String ROLE = "";
        //当不收费时，参与做管理员原始保存方法
        if("0".equals(pay_is_charge)){
            ROLE = "ROLE_ADMIN";
        }

        if((p==null || (p!=null && p.getSurplusQuentity()==0)) && !"ROLE_ADMIN".equals(ROLE)){
            Set set = user.getRoles();
            Iterator<Role> it = set.iterator();
            while (it.hasNext()) {
                Role r = it.next();
                if("ROLE_ADMIN".equals(r.getId())){
                    ROLE = r.getId();
                }
            }
        }
        //1、当余额，可以新申请二维码的情况下；  2、或有充值历史且 已有二维码的情况;
        if((p!=null && p.getSurplusQuentity()>0 &&  cq==null)|| (p!=null && cq!=null) || "ROLE_ADMIN".equals(ROLE)){

            //判断该商品是否 已经消耗二维码，若为空则判断为未消耗，此时要做减去码数操作并记录商品对应的码数。
            //若管理员则不参与记录消费的情况
            if(cq==null && !"ROLE_ADMIN".equals(ROLE)){
                cq = new CommodityQrcode();
                cq.setCommodityId(commodityId);
                cq.setChildCommodityId(commodityId);
                cq.setBatch("");
                cq.setDele(0);
                cq.setIsQrcode(0);
                cq.setStatus(0);
                commodityQrcodeService.insert(cq);
                //做消耗 二维码 操作
                p.setUpdateUserId(user.getId());
                int QuantityUsed = p.getQuantityUsed();
                int SurplusQuentity = p.getSurplusQuentity();
                p.setQuantityUsed(QuantityUsed+1);
                if(SurplusQuentity==0)
                    SurplusQuentity = p.getTotal();
                p.setSurplusQuentity(SurplusQuentity-1);
                orderPackageService.updateConsumptionCode(p);
                //主要更新 已经使用二维码，并更新时间
                commodityQrcodeService.updateUsedQrcodeTime(commodity.getId(),1,1);
            }

            commodityService.save(commodity,commodityId);
            return DataEvent.wrap("commodity", new CommonDto<Commodity>(commodity));
        }else{//若没有余额，保存失败。提示用户去充值
            CommonDto dto = new CommonDto<Commodity>(commodity,"CREDIT_LOW");
            return DataEvent.wrap("commodity", dto);
        }

    }


    @RequestMapping(value = "query")
    public Object query(@RequestParam(required = false) Map<String, String> conditions) {
        PageInfo<Commodity> pageInfo=  commodityService.query(conditions);
        pageInfo.getList().forEach(item->item.setIndustryPo(dictionaryService.findDictionaryById(item.getIndustry())));
        return DataEvent.wrap("commodityList", pageInfo);
    }

    @ResponseBody
    @RequestMapping(value = "query_",method = RequestMethod.POST)
    public Object query_(HttpServletResponse response, HttpServletRequest request, @RequestBody StringDto usr) {

        Map conditions = new HashMap();
        String pageNum =  request.getParameter("pageNum");
        String selXm =  request.getParameter("selXm");
        String name =  usr.getName().trim();
        String other_name = "";
        int ii = name.indexOf(" ");
        String f = "";
        if(name.indexOf(" ")>0){
            f = name.substring(0,name.indexOf(" "));
            other_name = name.substring(f.length()).trim();
            conditions.put("other_name",other_name);
            name = f;
        }
        String [] nm = name.split(" ");

        /*
            a_spmc 商品名称
            a_jyqy 经营企业
            a_scqy 生成企业
            a_sbmc 商标名称
            a_fr   法定代表人
        */

        if("a_spmc".equals(selXm)){
            conditions.put("name",name);    //a_spmc 商品名称
        }else if("a_jyqy".equals(selXm)){
            conditions.put("cname",name);   //a_jyqy 经营企业
        }else if("a_scqy".equals(selXm)){
            conditions.put("fname",name);   //a_scqy 生成企业
        }else if("a_sbmc".equals(selXm)){
            conditions.put("bname",name);   //a_sbmc 商标名称
        }else if("a_fr".equals(selXm)){    //a_fr   法定代表人
            //OWNER   '品牌权利人',         BRAND        品牌表
            conditions.put("owner",name);
            //LEGAL_PERSON  '法定代表人',   COMPANY      企业表
            conditions.put("legal_person",name);

        }else
            conditions.put("name",name);

        conditions.put("status","1");
        conditions.put("pageNum",pageNum);
        conditions.put("_","1540449364405");


        PageInfo<Commodity> pageInfo=  commodityService.query_(conditions);
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
