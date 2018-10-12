package com.cfcp.incc.controller.user;

import com.cfcp.incc.Constants;
import com.cfcp.incc.dao.UserDao;
import com.cfcp.incc.dto.StringDto;
import com.cfcp.incc.dto.user.User1Dto;
import com.cfcp.incc.entity.Distributor;
import com.cfcp.incc.service.distributor.DistributorService;
import com.cfcp.incc.utils.BeanUtil;
import com.cfcp.incc.utils.CheckUtil;
import com.cfcp.incc.utils.IdcardUtils;
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
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.tigerfacejs.commons.view.DataEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    DistributorService distributorService;

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

    @ResponseBody
    @RequestMapping(value = "/getUserByName",method = RequestMethod.POST)
    public Object getUserByName( HttpServletResponse response,HttpServletRequest request, @RequestBody StringDto usr) {

        String name = (String) usr.getName();
        String type = usr.getType();

        if(type!=null && !type.equals("") && !type.equals("用户")){

            int user = userService.getUserByCompanyName(name);
            if(user>0){
                return new CommonDto(CommonDto.CommonResult.COMPANY_NAME_ISUSED);
            }else{
                return new CommonDto(CommonDto.CommonResult.SUCCESS);
            }
        }


        User user = userService.getUserByName(name);
        if(user!=null){
            return new CommonDto(CommonDto.CommonResult.FAILED);
        }else{
            return new CommonDto(CommonDto.CommonResult.SUCCESS);
        }
        //return DataEvent.wrap("user", new CommonDto<User>(user));
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
    //@RequestMapping(value = {"/saveUser_rigi","saveUser_rigi"}, method = RequestMethod.POST)
    //@RequestMapping(value = "/saveUser_rigi", method = RequestMethod.POST,produces = "application/json")


    @ResponseBody
    @RequestMapping(value = "/saveUser_rici", method = RequestMethod.POST)
    public Object addUser_rici(HttpServletResponse response,HttpServletRequest request, @RequestBody User1Dto usr) {


        String yzm = usr.getYzm();
        String genCaptcha = (String)request.getSession().getAttribute(Constants.PIC_CHECK_CODE);
        if (genCaptcha==null){
            return DataEvent.wrap("user", new UserDto(UserDto.ResponseInfo.BAD_CAPTCHA_ISNULL));
        }

        if (yzm==null || "".equals(yzm) || !genCaptcha.equals(yzm)){
            return DataEvent.wrap("user", new UserDto(UserDto.ResponseInfo.BAD_CAPTCHA));
        }

        //验证  用户名为空
        if(usr.getName()==null || usr.getName().equals("")){
            return DataEvent.wrap("user", new UserDto(UserDto.ResponseInfo.USER_ISNULL));
        }


        if(usr.getPassword()!=null && usr.getPassword2()!=null && !usr.getPassword2().equals(usr.getPassword())){
            return DataEvent.wrap("user", new UserDto(UserDto.ResponseInfo.BAD_CAPTCHA));
        }
        if(usr.getMail()==null || "".equals(usr.getMail())){
            return DataEvent.wrap("user", new UserDto(UserDto.ResponseInfo.MAIL_ISNULL));
        }
        if(usr.getIdCard()==null || "".equals(usr.getIdCard())){
            return DataEvent.wrap("user", new UserDto(UserDto.ResponseInfo.ICCARD_ISNULL));
        }
        if(!IdcardUtils.validateCard(usr.getIdCard())){
            return DataEvent.wrap("user", new UserDto(UserDto.ResponseInfo.ICCARD_ERR));
        }

        if(!CheckUtil.checkEmail(usr.getMail())){
            return DataEvent.wrap("user", new UserDto(UserDto.ResponseInfo.MAIL_ERR));
        }
        if(usr.getPhone()==null || "".equals(usr.getPhone())){
            return DataEvent.wrap("user", new UserDto(UserDto.ResponseInfo.PHONE_ISNULL));
        }
        if(!CheckUtil.checkMobileNumberzJ(usr.getPhone()) && !CheckUtil.checkMobileNumber(usr.getPhone())){
            return DataEvent.wrap("user", new UserDto(UserDto.ResponseInfo.PHONE_ERR));
        }
        //验证  企业名称为空
        if(usr.getCompanyName()==null || usr.getCompanyName().equals("")){
                return DataEvent.wrap("user", new UserDto(UserDto.ResponseInfo.COMPANYNAME_ISNULL));
        }
        //验证  企业名称已被其他用户注册使用
        if(usr.getCompanyName()!=null && !usr.getCompanyName().equals("")){
            if(userService.getUserByCompanyName(usr.getCompanyName())>0){
                return DataEvent.wrap("user", new UserDto(UserDto.ResponseInfo.COMPANYNAME_ISUSED));
            }
        }

        if (usr.getName()!=null && userService.isUserExists1(usr.getName())){
            return DataEvent.wrap("user", new UserDto(UserDto.ResponseInfo.USER_EXISTED));
        }
        User user = null;

        if (usr.getName()!=null && userService.isUserExists1(usr.getName())){
            return DataEvent.wrap("user", new UserDto(UserDto.ResponseInfo.USER_EXISTED));
        }
        user = new User();
       //OrganizationListDto organizationListDto = new OrganizationListDto();
        BeanUtil.copy(usr, user);
        String si = usr.getBusinessLicense().replaceAll("\\\\", "");

        user.setBusinessLicense(si.substring(1,si.length()-1));
        if(!usr.getDistributorId().equals("")){
            //Distributor distributor = distributorService.get(usr.getDistributorId());
            //user.setDistributor(distributor);
        }
        user.setDistributorId(usr.getDistributorId());
        request.getSession().removeAttribute(Constants.PIC_CHECK_CODE); //验证码不能清空

        if ( userService.saveOrUpdate(user) > 0) {


            //return DataEvent.wrap("user", "注册成功。");
            //return "redirect:http://icon.cfcpincc.com/";
            return DataEvent.wrap("user", new CommonDto<User>(user));
        } else {
            return DataEvent.wrap("user", "保存失败！");
        }
    }
}