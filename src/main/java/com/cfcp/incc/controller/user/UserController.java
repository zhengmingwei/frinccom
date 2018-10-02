package com.cfcp.incc.controller.user;

import com.cfcp.incc.dao.UserDao;
import com.cfcp.incc.utils.generator.UUIDGenerator;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.cfcp.incc.controller.BaseController;
import com.cfcp.incc.dto.CommonDto;
import com.cfcp.incc.dto.user.UserDto;
import com.cfcp.incc.entity.User;
import com.cfcp.incc.security.LoginService;
import com.cfcp.incc.security.UserContext;
import com.cfcp.incc.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.tigerfacejs.commons.view.DataEvent;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 用户Controller
 * Created by zhyj on 2016/11/26.
 */
@RestController
@RequestMapping("/manager/user")
public class UserController extends BaseController{

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

//    /**
//     * 用户注册
//     * @param user
//     * @param code
//     * @param session
//     * @return
//     */
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public Object registerAsUser(@RequestBody User user,
//                                 @RequestParam String code,
//                                 HttpSession session) {
//        if(!StringUtils.hasLength(user.getUsername())) return new UserDto(UserDto.ResponseInfo.USER_ISNULL);
//        if(!StringUtils.hasLength(code)) return new UserDto(UserDto.ResponseInfo.CAPTCHA_ISNULL);
//        if(!StringUtils.hasLength(user.getPassword())) return new UserDto(UserDto.ResponseInfo.PASSWORD_ISNULL);
//        if (userService.isUserExists(user.getUsername().trim())) {
//            return new UserDto(UserDto.ResponseInfo.USER_EXISTED);
//        }
//        if (!smsCheckCodeService.validate(user.getUsername(), code, session)) {
//            return new UserDto(UserDto.ResponseInfo.BAD_CAPTCHA);
//        }
//        String originalPwd = user.getPassword();
//        if (userService.insertAsUser(user) > 0) {
//            if(loginService.autoLogin(user.getUsername(), originalPwd)){
//                return new UserDto(UserDto.ResponseInfo.SUCCESS, user);
//            }
//            return new UserDto(UserDto.ResponseInfo.LOGIN_ERR);
//        }
//        return new UserDto(UserDto.ResponseInfo.REGISTER_ERR);
//
//    }

    /**
     * 忘记密码
     * @param username
     * @param code
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    public Object forgotPassword(@RequestParam("username") String username, @RequestParam("authCode") String code, @RequestParam("password") String password, HttpSession session){
        if(!StringUtils.hasLength(username)) return new UserDto(UserDto.ResponseInfo.USER_ISNULL);
        if(!StringUtils.hasLength(code)) return new UserDto(UserDto.ResponseInfo.CAPTCHA_ISNULL);
        if(!StringUtils.hasLength(password)) return new UserDto(UserDto.ResponseInfo.PASSWORD_ISNULL);
//        if (smsCheckCodeService.validate(username, code, session)){
//            userService.changePwd(username, password);
            return new CommonDto(CommonDto.CommonResult.SUCCESS);
//        }
//        return new UserDto(UserDto.ResponseInfo.BAD_CAPTCHA);
    }

    /**
     * 修改密码
     * @param oldpassword
     * @param password
     * @return
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public Object resetPassword (@RequestParam("oldpassword") String oldpassword, @RequestParam("password") String password){
        if(!StringUtils.hasLength(oldpassword)) return DataEvent.wrap("resetpwd", new UserDto(UserDto.ResponseInfo.PASSWORD_ISNULL));
        if(!StringUtils.hasLength(password)) return DataEvent.wrap("resetpwd", new UserDto(UserDto.ResponseInfo.PASSWORD_ISNULL));
        if(StringUtils.hasLength("oldpassword") && StringUtils.hasLength("password") ){
            User user = UserContext.getCurrentUser();
            if (user != null && userService.encodePassword(oldpassword).equals(user.getPassword())){
                userService.changePwd(user.getName(), password);
                return DataEvent.wrap("resetpwd", new CommonDto(CommonDto.CommonResult.SUCCESS));
            }
        }
        return DataEvent.wrap("resetpwd", new CommonDto(CommonDto.CommonResult.FAILED));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Object get(@PathVariable String id) {
        User user = userService.get(id);
        user.setRoles(userService.listRolesByUser(user));
        return DataEvent.wrap("user", new CommonDto<User>(user));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable String id){
        userService.delete(id);
        return DataEvent.wrap("userDeleted", new CommonDto<>(CommonDto.CommonResult.SUCCESS));
    }

//    /**
//     * 导游补全信息
//     * @param form
//     * @return
//     */
//    @RequestMapping(value = "/completeInfo", method = RequestMethod.POST)
//    public Object becomeGuide(@RequestBody UserInfoForm form) {
//        User user = UserContext.getCurrentUser();
//        if (user == null) return new UserDto(UserDto.ResponseInfo.USER_NOT_FOUND);
//        userService.completeInfo(form, user.getId());
//        return  new CommonDto(CommonDto.CommonResult.SUCCESS);
//    }

//    /**
//     * 修改用户信息
//     * @param form
//     * @param propertyName
//     * @return
//     */
//    @RequestMapping(value = "/userInfo/${propertyName}", method = RequestMethod.POST)
//    public Object modifyUserInfo(@RequestBody UserInfoForm form, @PathVariable String propertyName) {
//        User user = UserContext.getCurrentUser();
//        if (user == null) return new UserDto(UserDto.ResponseInfo.USER_NOT_FOUND);
//        if (! PropertiesUtils.isExist(form, propertyName)) return new CommonDto(CommonDto.CommonResult.FAILED);
//        userService.updateUser(form, user.getId(), propertyName);
//        return new CommonDto(CommonDto.CommonResult.SUCCESS);
//    }
    /**
     * 修改用户状态信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/changeUserStatus", method = RequestMethod.PUT)
    public Object changeUserStatus(@RequestBody User user) {

//        userService.changeStatus(user.getStatus(), user.getId());
        return DataEvent.wrap("userStatusChanged", new CommonDto(CommonDto.CommonResult.SUCCESS));
    }
    @RequestMapping(value = "query")
    public Object query(@RequestParam(required = false) Map<String, String> conditions) {
        Page page = initPage(conditions, BaseController.PageType.DEFAULT);
        PageInfo<User> pageInfo=  userService.query(conditions);
        return DataEvent.wrap("userList", pageInfo);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addUser(@RequestBody User user) {
        if (userService.isUserExists(user.getName(), user.getId())){
            return DataEvent.wrap("user", new UserDto(UserDto.ResponseInfo.USER_EXISTED));
        }
        if (userService.saveOrUpdate(user) > 0) {
            return DataEvent.wrap("user", new CommonDto<User>(user));
        } else {
            return DataEvent.wrap("user", "保存失败");
        }
    }

    @RequestMapping(value = "/saveUser_rig", method = RequestMethod.POST)
    public Object addUser_rig(@RequestBody User user) {
        if (userService.isUserExists(user.getName(), user.getId())){
            return DataEvent.wrap("user", new UserDto(UserDto.ResponseInfo.USER_EXISTED));
        }
        if ( userService.saveOrUpdate(user) > 0) {
            //return DataEvent.wrap("user", "注册成功。");
            //return "redirect:http://icon.cfcpincc.com/";
            return DataEvent.wrap("user", new CommonDto<User>(user));
        } else {
            return DataEvent.wrap("user", "保存失败！");
        }
    }
}