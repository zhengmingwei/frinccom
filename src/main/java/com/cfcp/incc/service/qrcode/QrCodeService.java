package com.cfcp.incc.service.qrcode;

import com.cfcp.incc.utils.FileUtils;
import com.cfcp.incc.utils.image.PngCompositionUtil;
import com.cfcp.incc.utils.image.ZxingSimpleUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by zhyj on 2017/8/4.
 */

@Service
public class QrCodeService {


    @Value("${qrcode.RootPath}")
    private String rootPath;

    @Value("${qrcode.RootURL}")
    private String rootURL;

    public void generate(String id){
        String qrFile = FileUtils.createFile(rootPath + "/temp/");
        String templateFileName = rootPath + "/ICON01.png";
        String outputFileName = FileUtils.createFile(rootPath + "/output/");

        try {

            ZxingSimpleUtils.encodeWithMargin(rootURL + id, 900, 910, 0,"png", qrFile+id);

            PngCompositionUtil.compositionFile(new File(qrFile+id), new File(templateFileName), new File(outputFileName+id+".png"));
//			System.out.println(decode(outFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
