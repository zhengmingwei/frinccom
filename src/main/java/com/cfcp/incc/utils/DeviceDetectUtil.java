package com.cfcp.incc.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * <p/>
 * 识别客户端操作系统
 * @author zhyj
 * @date 2016/12/16
 * @since 0.1
 */
public class DeviceDetectUtil {

    public static final String OS_ANDROID = "Android";
    public static final String OS_IPHONE = "IPhone";
    public static final String OS_WINDOWS = "Windows";
    public static final String OS_MAC = "Mac";
    public static final String OS_UNIX = "Unix";
    public static final String OS_UNKNOWN = "UnKnown";

    public static String detectOS(String userAgent) {

        String os;

        if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
            os = OS_IPHONE;
        } else if (userAgent.toLowerCase().indexOf("android") >= 0) {
            os = OS_ANDROID;
        }else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
            os = OS_MAC;
        } else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
            os = OS_UNIX;
        } else if (userAgent.toLowerCase().indexOf("windows") >= 0) {
            os = OS_WINDOWS;
        }   else {
            os = OS_UNKNOWN;
        }

        return os;
    }

}
