package com.cfcp.incc.controller;


import com.cfcp.incc.entity.DemoEntity;
import com.cfcp.incc.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tigerfacejs.commons.view.DataEvent;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/person")
public class DemoController {

    @Autowired
    DemoService demoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object getDemoEntity(@PathVariable("id") Long id) {
        DemoEntity person = demoService.getDemoEntity(id);
        return DataEvent.wrap("person", person);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object queryDemoEntity(@RequestParam(required = false) Map<String, String> conditions) {
//		conditions.put("name", name);
        return DataEvent.wrap("personList", demoService.queryDemoEntity(conditions));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object insertDemoEntity(@RequestBody DemoEntity person) {
        if (demoService.insertDemoEntity(person) > 0) {
            return DataEvent.wrap(new HashMap<String, Object>() {{
                put("person", person);
                put("personList", demoService.queryDemoEntity(new HashMap<String, String>()));
            }});
        }
        return DataEvent.wrap("message", "保存失败");
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Object updateDemoEntity(@RequestBody DemoEntity person) {
        if (demoService.updateDemoEntity(person) > 0) {
            return DataEvent.wrap(new HashMap<String, Object>() {{
                put("person", demoService.getDemoEntity(person.getId()));
                put("personList", demoService.queryDemoEntity(new HashMap<String, String>()));
            }});
        }
        return DataEvent.wrap("message", "保存失败");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteDemoEntity(@PathVariable("id") Long id) {
        if (demoService.deleteDemoEntity(id) > 0) {
            return DataEvent.wrap("person", new DemoEntity());
        }
        return DataEvent.wrap("message", "删除失败");
    }

    @RequestMapping(value = "/pic/{name}", method = RequestMethod.GET)
    public void get(HttpServletResponse response, @PathVariable String name) {
        try {
            File path = new File("/usr/local/demo/upload");
            File file = new File(path, name);
            System.out.println(file.getPath());
            FileInputStream input = new FileInputStream(file);
            response.setContentType("image/jpeg");
            // response.setContentLength(ufile.length);
            FileCopyUtils.copy(input, response.getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/{id}/pic", method = RequestMethod.POST)
    public Object uploadPic(@PathVariable Long id,
                            @RequestParam(value = "picfile", required = false) MultipartFile file) {
        try {
            if (file.getSize() > 0) {
                InputStream input = file.getInputStream();
                System.out.println("size::" + file.getSize());
                File path = new File("/usr/local/demo/upload");
                path.mkdirs();
                System.out.println(path.getPath());
                String orifilename = file.getOriginalFilename();
                String picname = id + orifilename.substring(orifilename.lastIndexOf("."));
                File newfile = new File(path, picname);
                OutputStream output = new FileOutputStream(newfile);

                int ret = 0;
                byte[] buf = new byte[1024];
                while ((ret = input.read(buf, 0, 1024)) > 0) {
                    output.write(buf, 0, ret);
                }
                output.flush();
                output.close();
                input.close();

                DemoEntity person = demoService.getDemoEntity(id);
                person.setPic(picname);
                demoService.updateDemoEntity(person);

                return DataEvent.wrap("picChanged", person);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataEvent.wrap("message", "上传失败");
    }
}
