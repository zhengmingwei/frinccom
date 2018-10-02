package com.cfcp.incc.controller.upload;

import com.cfcp.incc.dto.CommonDto;
import com.cfcp.incc.entity.UploadedFile;
import com.cfcp.incc.utils.MessageDigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tigerfacejs.commons.view.DataEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * Created by zyj on 17/4/18.
 */
@RestController
@RequestMapping(value = "/file")
public class UploadController {
    @Value("${upload.RootPath}")
    private String rootPath;

    @Value("${upload.RootURL}")
    private String rootURL;

    @RequestMapping(value = "/get/{system}/{module}/{uuid}.{ext}", method = RequestMethod.GET)
    public void get(@PathVariable String system,
                    @PathVariable String module,
                    @PathVariable String uuid,
                    @PathVariable String ext,
                    HttpServletResponse response) {
        try {
            if( ext !=null && ext.toLowerCase().indexOf("jpg") > 0){
                response.setHeader("content-type", "image/jpeg");
            } else if (ext !=null && ext.toLowerCase().indexOf("png") > 0) {
                response.setHeader("content-type", "image/png");
            } else if (ext !=null && ext.toLowerCase().indexOf("gif") > 0) {
                response.setHeader("content-type", "image/gif");
            } else {
                response.setHeader("content-type", "image/" + ext);
            }
            File path = new File(rootPath, "cdn/" + system + "/" + module + "/" );
            String filename = uuid + "." + ext;
            File file = new File(path, filename);
            FileInputStream input = new FileInputStream(file);
            FileCopyUtils.copy(input, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    @RequestMapping(value = "/upload/{system}/{module}", method = RequestMethod.POST)
    public Object upload(@PathVariable String system,
                         @PathVariable String module,

                         @RequestParam(value = "uploadFile", required = false) MultipartFile file,
                         HttpServletRequest request,
                         HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Expose-Headers", "TigerFace-Event");
        try {
            if (file.getSize() > 0) {

                String orifilename = file.getOriginalFilename();
                String ext = orifilename.substring(orifilename.lastIndexOf(".") + 1);


                InputStream input = file.getInputStream();
                File path = new File(rootPath, "cdn/" + system + "/" + module + "/" );
                path.mkdirs();
                System.out.println(path.getPath());
                String digest = MessageDigestUtils.sha1FileDigest(file.getBytes());
                Assert.hasText(digest, "文件内容为空无法算出文件签名");

                String filename = digest + "." + ext;
                UploadedFile obj = new UploadedFile(digest,this.rootURL + system + "/" + module + "/" + filename, ext, orifilename, "done");
                File newfile = new File(path, filename);
                OutputStream output = new FileOutputStream(newfile);

                int ret = 0;
                byte[] buf = new byte[1024];
                while ((ret = input.read(buf, 0, 1024)) > 0) {
                    output.write(buf, 0, ret);
                }
                output.flush();
                output.close();
                input.close();

                return DataEvent.wrap("UPLOAD_SUCCESS", new CommonDto<UploadedFile>(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataEvent.wrap("UPLOAD_FAILED", "上传失败");
    }
}
