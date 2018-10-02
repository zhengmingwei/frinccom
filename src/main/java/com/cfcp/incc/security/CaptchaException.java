package com.cfcp.incc.security;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by zhyj on 16/4/27.
 */
public class CaptchaException extends AuthenticationException {

    public CaptchaException(String msg) {
        super(msg);
    }

    public CaptchaException(String msg, Throwable t) {
        super(msg, t);
    }
}
