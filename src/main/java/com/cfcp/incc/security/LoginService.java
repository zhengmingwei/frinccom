package com.cfcp.incc.security;

import com.cfcp.incc.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by zhyj on 16/8/3.
 */
@Service
public class LoginService {

    @Autowired
    UserService userService;

//    @Autowired
//    SmsCheckCodeService smsCheckCodeService;

    @Autowired
    RememberMeServices rememberMeServices;

    @Autowired
    @Qualifier("org.springframework.security.authenticationManager")
    protected AuthenticationManager authenticationManager;

    Logger logger = LoggerFactory.getLogger(LoginService.class);

    public boolean autoLogin(String userName, String password, HttpServletRequest request, HttpServletResponse response) {

        try {
            UserDetails userDetails = userService.loadUserByUsername(userName);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
            authenticationManager.authenticate(auth);
            if (auth.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(auth);
                rememberMeServices.loginSuccess(request, response, auth);
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean captchaLogin(String userName, String captcha, HttpSession session) {
//        if (smsCheckCodeService.validate(userName, captcha, session)) {
//            return false;
//        }
        try {
            UserDetails userDetails = userService.loadUserByUsername(userName);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authenticationManager.authenticate(auth);
            if (auth.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(auth);
                return true;
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return false;
    }

}
