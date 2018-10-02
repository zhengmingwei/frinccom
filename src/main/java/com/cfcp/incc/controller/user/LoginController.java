package com.cfcp.incc.controller.user;

import com.cfcp.incc.Constants;
import com.cfcp.incc.dto.CommonDto;
import com.cfcp.incc.dto.user.UserDto;
import com.cfcp.incc.entity.User;
import com.cfcp.incc.security.CaptchaException;
import com.cfcp.incc.security.LoginService;
import com.cfcp.incc.security.UserAdapter;
import com.cfcp.incc.security.UserContext;
import com.cfcp.incc.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.tigerfacejs.commons.view.DataEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录Controller
 * 包括登录、登录成功、登录失败相关内容
 */
@RestController
@RequestMapping("/security")
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    public HttpHeaders getJsonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return headers;
    }

    @RequestMapping(value = "/login-page", method = RequestMethod.GET)
    public ResponseEntity<String> loginPage() {
        return new ResponseEntity<String>(getJsonHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/authentication-failure", method = RequestMethod.GET)
    public Object authenticationFailure(HttpServletRequest request) {
        String message = UserDto.ResponseInfo.BAD_CREDENTIAL.getMessage();
        AuthenticationException exception = (AuthenticationException) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        if (exception != null && exception instanceof CaptchaException)
            message = exception.getMessage();
        return new UserDto(UserDto.ResponseInfo.BAD_CREDENTIAL);
    }

    @RequestMapping(value = "/default-target", method = RequestMethod.GET)
    public Object defaultTarget() {
        User user = UserContext.getCurrentUser();
        return new UserDto(UserDto.ResponseInfo.SUCCESS, user);
    }
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Object currentUser() {
        User user = UserContext.getCurrentUser();
        return DataEvent.wrap("currentuser", new CommonDto<User>(user));
    }

    @RequestMapping(value = "/m_login", method = RequestMethod.POST)
    public Object login(@RequestParam("username") String username, @RequestParam("password") String password, /*@RequestHeader("User-Agent") String userAgent,*/ HttpServletRequest request, HttpServletResponse response) {

        UserAdapter userDetails = (UserAdapter) userService.loadUserByUsername(username);
        if (userDetails.getUser() == null || !StringUtils.hasLength(userDetails.getUser().getName())) {
            return new UserDto(UserDto.ResponseInfo.USER_NOT_FOUND);
        }
        if (!userDetails.isEnabled()){
            //return new UserDto(UserDto.ResponseInfo.USER_DISABLED);
        }

        if (loginService.autoLogin(username, password, request, response)) {
//            Userinfo userinfo = userDetails.getUser().getUserinfoList().get(0);
//            userinfoService.updateDevice(userinfo.getId(), DeviceDetectUtil.detectOS(userAgent));
            UserDto dto = new UserDto(UserDto.ResponseInfo.SUCCESS, userDetails.getUser());
            return dto;
        }

        return new UserDto(UserDto.ResponseInfo.BAD_CREDENTIAL);
    }

    @RequestMapping(value = "/logout-target", method = RequestMethod.GET)
    public Object logoutDefaultTarget() {
        return DataEvent.wrap("currentUser", UserContext.getCurrentUser());
    }

    private boolean validatePicCheckCode(HttpServletRequest request ){

        String requestCaptcha = request.getParameter(Constants.PIC_CHECK_CODE);
        String genCaptcha = (String)request.getSession().getAttribute(Constants.PIC_CHECK_CODE);
        request.getSession().removeAttribute(Constants.PIC_CHECK_CODE);

        return((requestCaptcha != null) && (requestCaptcha.toLowerCase().equals(genCaptcha)));

    }
}
