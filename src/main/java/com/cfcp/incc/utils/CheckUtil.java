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
}
