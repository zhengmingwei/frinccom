package com.cfcp.incc.controller.captcha;

import com.cfcp.incc.Constants;
import com.cfcp.incc.utils.image.ImgCheckCodeUtil;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tigerfacejs.commons.view.DataEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/view/picCheckCode")
public class PicCheckCodeController {

private int width = 100;
private int height = 38;



@RequestMapping(method = RequestMethod.GET)
public void get(HttpServletRequest request, HttpServletResponse response) throws IOException
{
  response.setHeader("Pragma", "No-cache");
  response.setHeader("Cache-Control", "no-cache");
  response.setDateHeader("Expires", 0L);
  
  response.setContentType("image/jpeg");

  //生成随机字串
  String verifyCode = ImgCheckCodeUtil.generateVerifyCode(4);
  //存入会话session
  HttpSession session = request.getSession(true);
  session.setAttribute(Constants.PIC_CHECK_CODE, verifyCode.toLowerCase());
  //生成图片

  ImgCheckCodeUtil.outputImage(width, height, response.getOutputStream(), verifyCode);
}

  @RequestMapping(value = "/getString",method = RequestMethod.GET)
  public String getString(HttpServletRequest request, HttpServletResponse response) throws IOException
  {
    //生成随机字串
    String verifyCode = ImgCheckCodeUtil.generateVerifyCode(4);
    //存入会话session
    HttpSession session = request.getSession(true);
    session.setAttribute(Constants.PIC_CHECK_CODE, verifyCode.toLowerCase());
    return verifyCode;
  }

  @RequestMapping(method = RequestMethod.POST)
  public Object validate(@ModelAttribute("picCheckCode") String picCheckCode, HttpSession session){
    String sessionCode = (String) session.getAttribute(Constants.PIC_CHECK_CODE);
    String result = "error";
    String requestCode = (picCheckCode != null? picCheckCode.toLowerCase(): "");

    if(requestCode != null && requestCode.equals(sessionCode)){
      result = "success";
    }
    return DataEvent.wrap("view/picCheckCode", "{\"result\":\""+result+"\"}");
  }
}
