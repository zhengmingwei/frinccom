package com.cfcp.incc.controller.distributor;

import com.cfcp.incc.controller.BaseController;
import com.cfcp.incc.dto.CommonDto;
import com.cfcp.incc.entity.Dictionary;
import com.cfcp.incc.entity.Distributor;
import com.cfcp.incc.entity.User;
import com.cfcp.incc.service.distributor.DistributorService;
import com.cfcp.incc.service.user.UserService;
import com.cfcp.incc.utils.ExcleImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.tigerfacejs.commons.view.DataEvent;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by zhyj on 2017/7/8.
 */
@RestController
@RequestMapping("manager/distributor")
public class DistributorController extends BaseController {

    @Autowired
    DistributorService distributorService;
    @Autowired
    UserService userService;
    @RequestMapping(method = RequestMethod.POST)
    public Object addDistributor(@RequestBody Distributor distributor) {
        if (distributorService.saveOrUpdate(distributor) > 0) {
            return DataEvent.wrap("distributor", new CommonDto<Distributor>(distributor));
        } else {
            return DataEvent.wrap("distributor", "保存失败");
        }
    }


    @RequestMapping(value = "query")
    public Object query(@RequestParam(required = false) Map<String, String> conditions) {
        PageInfo<Distributor> pageInfo = distributorService.query(conditions);
        return DataEvent.wrap("distributorList", pageInfo);
    }

    @RequestMapping(value = "options")
    public Object allMap() {
        return DataEvent.wrap("distributorOptions", distributorService.allMap());
    }

    @RequestMapping(value = "/options_rg",method = RequestMethod.POST )
    public Object allMap_rg(
            HttpServletRequest request,
            HttpServletResponse response) {
        return DataEvent.wrap("distributorOptions", distributorService.allMap());
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Object get(@PathVariable String id) {
        Distributor distributor = distributorService.get(id);
        return DataEvent.wrap("distributor", new CommonDto<Distributor>(distributor));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable String id){
        distributorService.delete(id);
        return DataEvent.wrap("distributorDeleted", new CommonDto<Distributor>(CommonDto.CommonResult.SUCCESS));
    }

    @RequestMapping(value = "UserExcel/{id}/{userType}",method = RequestMethod.GET)
    public @ResponseBody String  getUserExcel(HttpServletResponse response,HttpServletRequest request,@PathVariable String id,@PathVariable String userType){

        //这里直接new了
        ExcleImpl  excleImpl=new ExcleImpl();
        Distributor distributor = distributorService.get(id);
        List<User> pList = userService.getUserByDistributorId(id);
        response.setContentType("application/binary;charset=UTF-8");
        try{
            ServletOutputStream out=response.getOutputStream();
            try {
                java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = format1.format(new Date());
                //设置文件头：最后一个参数是设置下载文件名(这里我们叫：张三.pdf)
                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("CFCPINCC分销商或代理("+distributor.getName()+")对应用户列表"+time+".xls", "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }

            String photo="/upload/noimg.png";
            String imgPath = request.getSession().getServletContext().getRealPath("/images")+"/BG.png";
            String p1 =  request.getContextPath()+"/images/BG.png";
            String p2 = request.getRequestURI()+"/images/BG.png";
            String p3 = System.getProperty("user.dir")+"/images/BG.png";
            String p4 = request.getSession().getServletContext().getRealPath("");



            //2.你也可以随时在任意的class里调用
            String p41 = this.getClass().getClassLoader().getResource("/").getPath();
            //这将获取 到classes目录的全路径
            ///例如 : E:\eclipseM9/workspace/tree/WEB-INF/classes/

            // 这个方法也可以不在web环境里确定路径,比较好用

            //3.
            String p42 = request.getContextPath();
           // 获得web根的上下文环境
            //如 /tree
            //tree是我的web项目的root context

            //jsp 取得当前目录的路径
            String path12=request.getRealPath("")+"images\\bodybg.png";
            //得到jbossWEB发布临时目录 warUrl=.../tmp/deploy/tmp14544test-exp.war/
            //path=C:\jboss-4.0.5.GA\server\default\tmp\deploy\tmp14544test-exp.war\

            String path = (String)request.getContextPath();
            //得到项目(test)应用所在的真实的路径 path=/test
            String path11 = request.getRequestURI();
            //得到应用所在的真实的路径 path=/test/admin/admindex.jsp

            String savePath=request.getRealPath(request.getServletPath());
            //得到当前文件的磁盘绝对路径

            //JAVA 取得当前目录的路径
            File filei=new File(".");
            String pathi=filei.getAbsolutePath();
            path=filei.getPath();
            //得到jboss运行目录 path=C:\jboss-4.0.5.GA\bin\



            FileInputStream fileIs=null;
            try {
                fileIs = new FileInputStream(path12);
            } catch (Exception e) {
                e.getMessage();
            }


            String[] titles = { "所属分销商", "用户姓名", "邮箱", "电话", "角色","创建时间"  };
            excleImpl.export(titles, out,pList,distributor.getName(),path12);
            return "success";
        } catch(Exception e){
            e.printStackTrace();
            return "导出信息失败";
        }

        //return DataEvent.wrap("distributor", new CommonDto<Distributor>(distributor));
    }

    @RequestMapping(value = "DistributorUser/{id}/{userType}",method = RequestMethod.GET)
    public ModelAndView getDistributorUser(@PathVariable String id, @PathVariable String userType){

        Distributor distributor = distributorService.get(id);
        List<User> pList = userService.getUserByDistributorId(id);
        //return DataEvent.wrap("dictionary", new CommonDto<Dictionary>(dictionary));
        ModelAndView mv = new ModelAndView("distributorUser");
        mv.addObject("pList", pList);
        mv.addObject("distributor", distributor);
        return mv;
    }

}
