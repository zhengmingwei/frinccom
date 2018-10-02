package com.cfcp.incc.utils;

import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <p/>
 * 属性工具用于属性copy
 * @author zhyj
 * @date 2016/12/9
 * @since 0.1
 */
public class PropertiesUtils {
    public static void copyProperty(Object source, Object target, String propertyName){
        PropertyDescriptor targetPd = BeanUtils.getPropertyDescriptor(target.getClass(), propertyName);
        Method writeMethod = targetPd.getWriteMethod();
        PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), propertyName);
        Method readMethod = sourcePd.getReadMethod();
        Object ex = null;
        try {
            ex = readMethod.invoke(source, new Object[0]);
            writeMethod.invoke(target, new Object[]{ex});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断属性是否存在
     * @param obj
     * @param propertyName
     * @return
     */
    public static boolean isExist(Object obj, String propertyName){
        PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(obj.getClass(), propertyName);
        try {
            propertyDescriptor.getPropertyType();
        } catch (NullPointerException e){
            return false;
        }
        return true;
    }

}
