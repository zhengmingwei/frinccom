package com.cfcp.incc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class WebBinding implements WebBindingInitializer {

    @Override
    public void initBinder(WebDataBinder webDataBinder) {

    }

    @Override
    public void initBinder(WebDataBinder binder, WebRequest request) {
        // 使用spring自带的CustomDateEditor
        // 解决时间转换问题
        // yyyy-MM-dd
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
    }


}
