package com.cfcp.incc.service.certification;

import com.cfcp.incc.entity.Audit;
import com.cfcp.incc.entity.Commodity;
import com.cfcp.incc.service.commodity.CommodityService;
import com.cfcp.incc.utils.DateUtils;
import com.cfcp.incc.utils.FileUtils;
import com.cfcp.incc.utils.image.CertificationUtil;
import com.cfcp.incc.utils.image.PngCompositionUtil;
import com.cfcp.incc.utils.image.ZxingSimpleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by zhyj on 2017/8/4.
 */

@Service
public class CertificationService {


    @Value("${certification.RootPath}")
    private String rootPath;

    @Value("${qrcode.RootURL}")
    private String rootURL;

    public void generate(Commodity commodity, Audit audit){
        String qrFile = FileUtils.createFile(rootPath + "/temp/");
        String templateFileName = rootPath +  "/template.png";
        FileUtils.createFile(rootPath + "/output/");//新添加 接手无此步骤，导致新月份查看证书时无法下载
        String outputFileName = FileUtils.getFile(rootPath + "/output/", commodity.getId());
        String id = commodity.getId();
        try {
            ZxingSimpleUtils.encodeWithMargin(rootURL + id, 132, 132, 0,"png", qrFile+id);
            CertificationUtil.compositionFile(new File(qrFile+id), new File(templateFileName), new File(outputFileName+id+".png"), commodity.getName(),commodity.getBrand().getName(),commodity.getCompany().getName(), DateUtils.formatCn(audit.getBeginDate()) + " 至 " + DateUtils.formatCn(audit.getExpireDate()), DateUtils.formatCn(audit.getAuditDate()));

//			System.out.println(decode(outFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
