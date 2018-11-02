package com.cfcp.incc.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class WebUtils {

    private HttpServletRequest request;

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public static String getClientIp(HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }


    /**
     * 得到访问者的IP地址
     * @return ip
     * @throws Exception
     */
    public String getIp() throws Exception{
        String ip = request.getHeader("X-Forwarded-For");
        if(ip!=null){
            if(!ip.isEmpty()&&!"unKnown".equalsIgnoreCase(ip)){
                int index = ip.indexOf(",");
                if (index != -1){
                    return ip.substring(0,index);
                }else{
                    return ip;
                }
            }
        }

        return  request.getRemoteAddr();
    }

    /**
     * 通过访问的Ip地址得到mac地址
     * @param ip
     * @return mac
     */
    public static String getMacByIp(String ip){
        String macAddress = "";
        try {
            java.lang.Process process = Runtime.getRuntime().exec("nbtstat -A "+ip);
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String str = "";
            while ((str=input.readLine())!=null){
                str = str.toUpperCase();
                if(str.indexOf("MAC ADDRESS")>1){
                    int start = str.indexOf("=");
                    macAddress = str.substring(start+1,str.length()).trim();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  macAddress;
    }

    // 获取ip地址
    public static String getIpAddress() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                } else {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        if (ip != null && ip instanceof Inet4Address) {
                            return ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
// _logger.error("IP地址获取失败", e);
            e.printStackTrace();
        }
        return "";
    }

    // 获取mac地址
    public static String getMacAddress() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            byte[] mac = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                } else {
                    mac = netInterface.getHardwareAddress();
                    if (mac != null) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < mac.length; i++) {
                            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                        }
                        if (sb.length() > 0) {
                            return sb.toString();
                        }
                    }
                }
            }
        } catch (Exception e) {
// _logger.error("MAC地址获取失败", e);
            e.printStackTrace();
        }
        return "";
    }


    public static String getMACAddress(String ip){
        String str = "";
        String macAddress = "";
        try {
            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
            InputStreamReader ir = new InputStreamReader(p.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for (int i = 1; i < 100; i++) {
                str = input.readLine();
                if (str != null) {
                    if (str.indexOf("MAC Address") > 1) {
                        macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length());
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return macAddress;
    }
    //获得客户端真实IP地址的方法二：
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }




    /**
     * 得到访问者的IP地址
     * @return ip
     * @throws Exception
     */
    public String getIp1() throws Exception{
        String ip = request.getHeader("X-Forwarded-For");
        if(ip!=null){
            if(!ip.isEmpty()&&!"unKnown".equalsIgnoreCase(ip)){
                int index = ip.indexOf(",");
                if (index != -1){
                    return ip.substring(0,index);
                }else{
                    return ip;
                }
            }
        }

        return  request.getRemoteAddr();
    }

    /**
     * 通过访问的Ip地址得到mac地址
     * @param ip
     * @return mac
     */
    public String getMacByIp1(String ip){
        String macAddress = "";
        try {
            java.lang.Process process = Runtime.getRuntime().exec("nbtstat -A "+ip);
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String str = "";
            while ((str=input.readLine())!=null){
                str = str.toUpperCase();
                if(str.indexOf("MAC ADDRESS")>1){
                    int start = str.indexOf("=");
                    macAddress = str.substring(start+1,str.length()).trim();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  macAddress;
    }
    public String getMacAddrByIp(String ip) {
        String macAddr = null;
        try {
            Process process = Runtime.getRuntime().exec("nbtstat -a " + ip);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            Pattern pattern = Pattern.compile("([A-F0-9]{2}-){5}[A-F0-9]{2}");
            Matcher matcher;
            for (String strLine = br.readLine(); strLine != null;
                 strLine = br.readLine()) {
                matcher = pattern.matcher(strLine);
                if (matcher.find()) {
                    macAddr = matcher.group();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return macAddr;
    }

}
