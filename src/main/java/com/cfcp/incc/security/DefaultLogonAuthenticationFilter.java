package com.cfcp.incc.security;

import com.cfcp.incc.Constants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhyj on 16/4/27.
 */
public class DefaultLogonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private Boolean allowCheckCode;

    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        String requestCaptcha = request.getParameter(Constants.PIC_CHECK_CODE);
        String genCaptcha = (String)request.getSession().getAttribute(Constants.PIC_CHECK_CODE);
        request.getSession().removeAttribute(Constants.PIC_CHECK_CODE);
        logger.info("开始校验验证码，生成的验证码为："+genCaptcha+" ，输入的验证码为："+requestCaptcha);
        logger.info("登录用户名："+request.getParameter("username")+" ，登录密码："+request.getParameter("password"));

        if( allowCheckCode && (requestCaptcha == null || !requestCaptcha.toLowerCase().equals(genCaptcha))){
            throw new CaptchaException("登录失败,验证码错误!");
        }
        return super.attemptAuthentication(request, response);
    }

    public void setAllowCheckCode(Boolean allowCheckCode) {
        this.allowCheckCode = allowCheckCode;
    }

    public Boolean getAllowCheckCode() {
        return allowCheckCode;
    }
}
