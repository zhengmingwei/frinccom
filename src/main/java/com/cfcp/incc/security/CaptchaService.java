package com.cfcp.incc.security;

import javax.servlet.http.HttpSession;

/**
 * Created by zhyj on 2016/11/29.
 */
public interface CaptchaService {
    public Boolean validate(String phone, String code, String tokenSignature);
    public String getTokenSignature(HttpSession session);
}
