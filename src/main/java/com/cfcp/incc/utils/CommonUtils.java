package com.cfcp.incc.utils;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/3/13.
 */
public class CommonUtils {


    /**
     * 将字符集合转换为字符
     * @param stringList
     * @return
     */
    public static String changeListForStr(List<String> stringList){
        return changeListForStr(stringList,null);
    }

    /**
     * 将字符集合转换为字符
     * @param stringList
     * @param mark
     * @return
     */
    public static String changeListForStr(List<String> stringList,String mark){
        if(stringList!=null&&stringList.size()>0){
            if (!StringUtils.hasText(mark)){
                mark="、";
            }
            if (stringList.size()<2){
                return stringList.get(0);
            }else {
                String str=stringList.get(0);
                for (int i=1;i<stringList.size();i++){
                    str=str+mark+stringList.get(i);
                }
                return str;
            }
        }else {
            return "";
        }
    }

    /**
     * 处理空格
     * @param splitStr
     * @return
     */
    public static String sortingSplitStrTrim(String splitStr){
        StringBuilder sb=new StringBuilder(",");
        if (StringUtils.hasText(splitStr)) {
            String[] tagArr=splitStr.split(",");
            for (String tag:tagArr){
                if(StringUtils.hasText(tag)){
                    sb.append(tag.trim()+",");
                }
            }
            splitStr=sb.toString();
        }
        return splitStr;
    }

    /**
     * 转换为集合
     * @param splitStr
     * @return
     */
    public static List<String> sortingSplitStrCollection(String splitStr){
        List<String> splitList=new ArrayList<>();
        if (StringUtils.hasText(splitStr)) {
            String[] tagArr=splitStr.split(",");
            for (String tag:tagArr){
                if(StringUtils.hasText(tag)){
                    splitList.add(tag.trim());
                }
            }
        }
        return splitList;
    }

    /**
     *得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为1,英文字符长度为0.5
     * @param s
     * @return
     */
    public static double length(String s) {
        double valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
        for (int i = 0; i < s.length(); i++) {
            // 获取一个字符
            String temp = s.substring(i, i + 1);
            // 判断是否为中文字符
            if (temp.matches(chinese)) {
                // 中文字符长度为1
                valueLength += 1;
            } else {
                // 其他字符长度为0.5
                valueLength += 0.5;
            }
        }
        //进位取整
        return  Math.ceil(valueLength);
    }
}
