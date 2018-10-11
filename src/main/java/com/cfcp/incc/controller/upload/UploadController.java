package com.cfcp.incc.controller.upload;

import com.cfcp.incc.dto.CommonDto;
import com.cfcp.incc.entity.UploadedFile;
import com.cfcp.incc.utils.MessageDigestUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.tigerfacejs.commons.view.DataEvent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
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

    @RequestMapping(value = "/upload_r/{system}/{module}", method = RequestMethod.POST)
    public Object upload_r(
                         HttpServletRequest request,
                         HttpServletResponse response
    ) throws IOException, ServletException {

        // 转型为MultipartHttpRequest(重点的所在)这个就是上面ajax中提到如果直接访问此接口而不加"/"，此转型就会报错
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //MultipartFile file = multipartRequest.getFiles("errPic");//获取文件集合  对应  jquery $("#imageFile").get(0).files
        // 获得第1张图片（根据前台的name名称得到上传的文件）
        MultipartFile file = multipartRequest.getFile("errPic"); //对应  jquery $("#imageFile").get(0).files[index]

        System.out.println("---这里是上传图片的servlet----");
        String picflag = request.getParameter("picture");
        String picPath = null;

        String system = "1";
        String module = "1";

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
                Object oq =  new CommonDto<UploadedFile>(obj);
                return DataEvent.wrap("UPLOAD_SUCCESS", oq);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataEvent.wrap("UPLOAD_FAILED", "上传失败");
    }

      public static  byte[] input2byte(InputStream inStream)  throws IOException {
                  ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
                  byte[] buff = new byte[100];
                  int rc = 0;
                  while ((rc = inStream.read(buff, 0, 100)) > 0) {
                          swapStream.write(buff, 0, rc);
                      }
                  byte[] in2b = swapStream.toByteArray();
                  return in2b;
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
