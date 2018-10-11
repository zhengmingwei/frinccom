package com.lkpower.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by YinZhiwei on 2016/11/23.
 */
public class BeanUtil {

    public static void copy(Object source, Object target) {
        BeanUtils.copyProperties(source,target);
    }

    public static void copyIgnoreNull(Object source, Object target) {
        BeanUtils.copyProperties(source,target,getNullPropertyNames(source));
    }


    //解决Bean拷贝时Null赋值的问题
    private static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
