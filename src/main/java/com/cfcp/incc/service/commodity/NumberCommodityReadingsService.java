package com.cfcp.incc.service.commodity;

import com.cfcp.incc.dao.NumberCommodityReadingsDao;
import com.cfcp.incc.entity.NumberCommodityReadings;
import com.cfcp.incc.service.BaseService;
import com.cfcp.incc.utils.GetAddressByIp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class NumberCommodityReadingsService extends BaseService {

    @Autowired
    NumberCommodityReadingsDao dao;
    public int saveOrUpdate(String commodityId,String ip){
        NumberCommodityReadings ni =new NumberCommodityReadings();
        ni.setIp(ip);
        ni.setCommodityId(commodityId);
        NumberCommodityReadings n = dao.get(ni);

        if(n!=null && StringUtils.hasLength(n.getId())){
             n.setLatelyTime(new Date());
             n.setIp(ip);
             dao.update(n);
             return n.getTotal()+1;
        } else {
            String resout = "";
            try{
                String str = GetAddressByIp.getJsonContent("http://ip.taobao.com/service/getIpInfo.php?ip="+ip);
                //System.out.println(str);
                resout = str;
            }catch(Exception e){
                e.printStackTrace();
                resout = "获取IP地址异常："+e.getMessage();
            }
            //System.out.println("result: " + resout);

            n = new NumberCommodityReadings();
            n.setId(commodityId);
            n.setCreateTime(new Date());
            n.getLatelyTime(new Date());
            n.setCommodityId(commodityId);
            n.setTotal(1);
            n.setStatus(1);
            n.setIp(ip);
            n.setAddress(resout);
            dao.insert(n);
            return 1;
        }
    }

    public NumberCommodityReadings get(NumberCommodityReadings ni){
        return dao.get(ni);
    }


    public List<NumberCommodityReadings> getAll(NumberCommodityReadings i){
        return dao.getAll(i);
    }
    public int updateAddressByIP(NumberCommodityReadings i){
        return dao.updateAddressByIP(i);
    }
    public  List<NumberCommodityReadings> getByAddressIsNull(NumberCommodityReadings i){
        return dao.getByAddressIsNull(i);
    }

    public String GetAddressByIp(String ip){
        String resout = "";
        try{
            String address = GetAddressByIp.getJsonContent("http://ip.taobao.com/service/getIpInfo.php?ip="+ip);
            //System.out.println(str);
            resout = address;
        }catch(Exception e){
            e.printStackTrace();
            resout = "获取IP地址异常："+e.getMessage();
        }
        return resout;
    }

}
