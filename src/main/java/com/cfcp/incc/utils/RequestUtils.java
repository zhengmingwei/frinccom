package com.cfcp.incc.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;

/**
 * <p>
 *
 * @author zyj
 * @date 12/16/2014
 * @since 0.1
 */
public class RequestUtils {

	/**
	 * 根据request获取basepath
	 * @param request
	 * @return
	 */
	public static String getBasePath(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		StringBuilder sb = new StringBuilder(request.getScheme());
		sb.append("://").append(request.getServerName());
		if(request.getServerPort() != 80) {
			sb.append(":").append(request.getServerPort());
		}
		sb.append(contextPath);
		String basePath = sb.toString();
		return basePath;
	}

	/**
	 * @param request 请求对象
	 * @param clazz   要设置Bean的类型,传入试为: Bean.class
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBeanToRequest(HttpServletRequest request, Class<T> clazz) {

		//获取页面所有的请求参数名称
		Enumeration<String> enume = request.getParameterNames();
		T beanObj = getObjectByClass(clazz);
		try {
			while (enume.hasMoreElements()) {
				//参数名称
				String propertyName = enume.nextElement();
				//判断是否存在此属性
				if (isCheckBeanExitsPropertyName(clazz, propertyName)) {
					//获取请求值
					String propertyValue = request.getParameter(propertyName);
					if(StringUtils.hasText(propertyValue) && !propertyValue.equalsIgnoreCase("null")) {
						setProperties(beanObj, propertyName, propertyValue);
					}
				}

			}
		} catch (Exception e) {
		}

		return beanObj;
	}

	private static <T> T getObjectByClass(Class<T> clazz) {
		T t = null;
		try {
			t = clazz.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		return t;
	}


	/**
	 * @param clazz        Class对象
	 * @param propertyName 属性名称
	 * @return true || false  检查对象中是否存在该属性名称
	 */
	private static boolean isCheckBeanExitsPropertyName(Class<?> clazz, String propertyName) {
		boolean retValue = false;
		try {
			Field field = clazz.getDeclaredField(propertyName);
			if (null != field) {
				retValue = true;
			}
		} catch (NoSuchFieldException e) {
//			System.out.println("类: " + clazz.getSimpleName() + ",不存在属性名: " + propertyName + " ,详细错误信息: " + e.getMessage());
		}
		return retValue;

	}

	/**
	 * 设置字段值
	 *
	 * @param object          实例对象
	 * @param propertyName 属性名
	 * @param value        新的字段值
	 * @return
	 */
	public static void setProperties(Object object, String propertyName, Object value) throws IntrospectionException,
			IllegalAccessException, InvocationTargetException {
		PropertyDescriptor pd = new PropertyDescriptor(propertyName, object.getClass());
		Method methodSet = pd.getWriteMethod();
		Class<?> paramterType = methodSet.getParameterTypes()[0];
		if(paramterType.isAssignableFrom(String.class)) {
			value = value.toString();
		} else if(paramterType.isAssignableFrom(Float.class)) {
			value = Float.valueOf(value.toString());
		} else if(paramterType.isAssignableFrom(Double.class)) {
			value = Double.valueOf(value.toString());
		} else if(paramterType.isAssignableFrom(Integer.class)) {
			value = Integer.valueOf(value.toString());
		} else if(paramterType.isAssignableFrom(Long.class)) {
			value = Long.valueOf(value.toString());
		} else if(paramterType.isAssignableFrom(java.sql.Date.class)) {
			value = DateUtils.parseSQLDate(value.toString());
		} else if(paramterType.isAssignableFrom(java.sql.Timestamp.class)) {
			value = DateUtils.parseTimeStamp(value.toString());
		} else if(paramterType.isAssignableFrom(Date.class)) {
			value = DateUtils.parse(value.toString());
		}
		methodSet.invoke(object, value);
	}
}
