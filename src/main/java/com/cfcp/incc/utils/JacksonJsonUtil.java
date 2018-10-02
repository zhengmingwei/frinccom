package com.cfcp.incc.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Jackson Json工具类
 *
 * @author zyj
 * @date 2014/12/16
 * @see 0.1
 */
public class JacksonJsonUtil {
	private static ObjectMapper mapper;

	/**
	 * 获取ObjectMapper实例
	 *
	 * @param createNew 方式：true，新实例；false,存在的mapper实例
	 * @return
	 */
	public static synchronized ObjectMapper getMapperInstance(boolean createNew) {
		if (createNew) {
			return new ObjectMapper();
		} else if (mapper == null) {
			mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		}
		return mapper;
	}

	/**
	 * 将java对象转换成json字符串
	 *
	 * @param obj 准备转换的对象
	 * @return json字符串
	 * @throws Exception
	 */
	public static String beanToJson(Object obj) throws Exception {
		try {
			ObjectMapper objectMapper = getMapperInstance(false);
			String json = objectMapper.writeValueAsString(obj);
			return json;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 将java对象转换成json字符串
	 *
	 * @param obj       准备转换的对象
	 * @param createNew ObjectMapper实例方式:true，新实例;false,存在的mapper实例
	 * @return json字符串
	 * @throws Exception
	 */
	public static String beanToJson(Object obj, Boolean createNew) throws Exception {
		try {
			ObjectMapper objectMapper = getMapperInstance(createNew);
			String json = objectMapper.writeValueAsString(obj);
			return json;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 将json字符串转换成java对象
	 *
	 * @param json 准备转换的json字符串
	 * @param cls  准备转换的类
	 * @return
	 * @throws Exception
	 */
	public static <T> T jsonToBean(String json, Class<T> cls) throws Exception {
		try {
			ObjectMapper objectMapper = getMapperInstance(false);
			T vo = objectMapper.readValue(json, cls);
			return vo;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 将json字符串转换成java对象
	 *
	 * @param json      准备转换的json字符串
	 * @param cls       准备转换的类
	 * @param createNew ObjectMapper实例方式:true，新实例;false,存在的mapper实例
	 * @return
	 * @throws Exception
	 */
	public static <T> T jsonToBean(String json, Class<T> cls, Boolean createNew) throws Exception {
		try {
			ObjectMapper objectMapper = getMapperInstance(createNew);
			if(createNew) objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			T vo = objectMapper.readValue(json, cls);
			return vo;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 将json字符串转换集合泛型
	 *
	 * @param json      准备转换的json字符串
	 * @param createNew ObjectMapper实例方式:true，新实例;false,存在的mapper实例
	 * @param collectionClass       集合的类
	 * @param elementClasses       泛型的类
	 * @return
	 * @throws Exception
	 */
	public static <T> T jsonToBean(String json, Boolean createNew, Class<?> collectionClass, Class<?>... elementClasses) throws Exception {
		try {
			ObjectMapper objectMapper = getMapperInstance(createNew);
			if(createNew) objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			JavaType javaType = getCollectionType(collectionClass, elementClasses);
			T vo = objectMapper.readValue(json, javaType);
			return vo;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 获取泛型的Collection Type
	 * @param collectionClass 泛型的Collection
	 * @param elementClasses 元素类
	 * @return JavaType Java类型
	 */
	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}
}