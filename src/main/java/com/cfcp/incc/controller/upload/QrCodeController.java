package com.cfcp.incc.controller.upload;

import com.cfcp.incc.utils.FileUtils;
import com.cfcp.incc.utils.image.PngCompositionUtil;
import com.cfcp.incc.utils.image.ZxingSimpleUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by zhyj on 2017/8/4.
 */
@RestController
@RequestMapping(value = "/qrcode")
public class QrCodeController {

    @Value("${qrcode.RootPath}")
    private String rootPath;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void get(@PathVariable String id,
                    HttpServletResponse response) {
        try {

                response.setHeader("content-type", "image/png");

            File path = new File(FileUtils.getFile(rootPath+"/output/", id ));
            String filename = id + ".png" ;
            File file = new File(path, filename);
            FileInputStream input = new FileInputStream(file);
            FileCopyUtils.copy(input, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
