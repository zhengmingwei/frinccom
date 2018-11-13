package com.cfcp.incc.utils;


import org.springframework.web.servlet.view.InternalResourceView;

import java.io.File;
import java.util.Locale;

/**
 * 配置返回页面 .jsp
 * 如果返回页面是 .html ，就需要使用此类
 * @author MING
 * @date 2018/4/23 16:52
 */
public class HtmlResourceView extends InternalResourceView {
    @Override
    public boolean checkResource(Locale locale){
        File file=new File(this.getServletContext().getRealPath("/")+getUrl());
        return file.exists(); //判断页面是否存在
    }
}
/*
---------------------
        作者：Tomorrow__C
        来源：CSDN
        原文：https://blog.csdn.net/qq_38637558/article/details/82110553
        版权声明：本文为博主原创文章，转载请附上博文链接！
        */
