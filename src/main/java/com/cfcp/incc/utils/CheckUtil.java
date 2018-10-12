package com.cfcp.incc.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtil {


    //Java 邮箱的正则表达式验证
    public static boolean checkEmail(String email) {
    boolean flag = false;
    try {
        String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        flag = matcher.matches();
    } catch (Exception e) {
        flag = false;
    }
    return flag;
}
//Java 手机号的正则表达式验证
public static boolean checkMobileNumber(String mobileNumber) {
    boolean flag = false;
    try {
        //Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
        Pattern regex = Pattern.compile("^1\\d{10}$");

        Matcher matcher = regex.matcher(mobileNumber);
        flag = matcher.matches();
    } catch (Exception e) {
        flag = false;
    }
    return flag;
}
// var rer = /^0\d{2,3}-?\d{7,8}$/;//座机验证
    //Java //座机号验证的正则表达式验证
    public static boolean checkMobileNumberzJ(String mobileNumber) {
        boolean flag = false;
        try {
            //Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            Pattern regex = Pattern.compile("^0\\d{2,3}-?\\d{7,8}$");

            Matcher matcher = regex.matcher(mobileNumber);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
    /**
     * 我国公民的身份证号码特点如下
     * 1.长度18位
     * 2.第1-17号只能为数字
     * 3.第18位只能是数字或者x
     * 4.第7-14位表示特有人的年月日信息
     * 请实现身份证号码合法性判断的函数，函数返回值：
     * 1.如果身份证合法返回0
     * 2.如果身份证长度不合法返回1
     * 3.如果第1-17位含有非数字的字符返回2
     * 4.如果第18位不是数字也不是x返回3
     * 5.如果身份证号的出生日期非法返回4
     *
     * @since 0.0.1
     */
    public static int validatorIdCard(String id) {
        String str = "[1-9]{2}[0-9]{4}(19|20)[0-9]{2}"
                + "((0[1-9]{1})|(1[1-2]{1}))((0[1-9]{1})|([1-2]{1}[0-9]{1}|(3[0-1]{1})))"
                + "[0-9]{3}[0-9x]{1}";
        Pattern pattern = Pattern.compile(str);
        return pattern.matcher(id).matches() ? 0 : 1;
    }
}
