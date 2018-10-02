package com.cfcp.incc.dto.user;

import com.cfcp.incc.dto.CommonDto;
import com.cfcp.incc.entity.Distributor;
import com.cfcp.incc.entity.User;
import org.springframework.beans.BeanUtils;

import java.text.SimpleDateFormat;

/**
 * 用于返回用户信息
 * Created by zhyj on 2016/11/28.
 */
public class UserDto extends CommonDto{

    private String idCard;

    private String name;

    private String mail;

    private String phone;

    private String distributorId;

    private Distributor distributor;

    protected ResponseInfo state;

    public enum ResponseInfo{
        SUCCESS("操作成功", "200"),
        USER_NOT_FOUND("用户不存在", "201"),
        BAD_CREDENTIAL("用户名密码错误", "202"),
        BAD_CAPTCHA("验证码错误", "203"),
        REGISTER_ERR("注册失败", "304"),
        USER_EXISTED("用户已存在","205"),
        LOGIN_ERR("注册成功，登录失败", "206"),
        USER_ISNULL("用户为空", "207"),
        PASSWORD_ISNULL("密码为空", "208"),
        CAPTCHA_ISNULL("验证码为空", "209");
        protected String message;
        protected String code;

        public String getMessage() {
            return message;
        }

        public String getCode() {
            return code;
        }

        ResponseInfo(String message, String code) {
            this.message = message;
            this.code = code;
        }

    }

    public UserDto( ResponseInfo state) {
        this.state = state;
    }

    public UserDto( ResponseInfo state, User user) {
        this.state = state;
        BeanUtils.copyProperties(user, this);
//        if( user.getUserinfoList() != null && user.getUserinfoList().size() > 0){
//            Userinfo userinfo = user.getUserinfoList().get(0);
//            this.setBirthday(userinfo.getBirthday() !=null ?new SimpleDateFormat("yyyy/MM/dd").format(userinfo.getBirthday()):null);
//            this.setSignature(userinfo.getHeadfsign());
//            this.setDeviceType(userinfo.getDevice());
//        }
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(String distributorId) {
        this.distributorId = distributorId;
    }

    public Distributor getDistributor() {
        return distributor;
    }

    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }

    public String getReturnCode() {
        return this.state.getCode();
    }

    public String getMessage() {
        return this.state.getMessage();
    }

    public static void main(String[] args){
        System.out.println(new UserDto(ResponseInfo.SUCCESS).getReturnCode());
        System.out.println(new UserDto(ResponseInfo.SUCCESS).getMessage());

    }
}
