package com.cfcp.incc.utils.generator;

import com.cfcp.incc.utils.generator.idgenerator.BasicEntityIdGenerator;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *
 * @author zyj
 * @date 12/9/2014
 * @since 0.1
 */
public class GeneratorComparator {

	public static Map<String, IdentifierGenerator> map = new HashMap<String, IdentifierGenerator>();

	public static final String GENERATOR_UUID = "uuid";
	public static final String GENERATOR_NUM = "numid";
	public static final String GENERATOR_OID = "oid";
	public static final String GENERATOR_PID = "pid";
	public static final String GENERATOR_ECID = "ecid";  //合同

//	static {
//		map.put(GENERATOR_UUID, new UUIDGenerator());
//		map.put(GENERATOR_NUM, new NumberIDGenerator());
////		map.put(GENERATOR_OID, new OrderIDGenerator());
//		try {
//			map.put(GENERATOR_OID, new BasicEntityIdGenerator());
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//		}
//		map.put(GENERATOR_PID, new ProductIDGenerator());
//		map.put(GENERATOR_ECID, new OrderIDGenerator());
//	}
	static {
		map.put(GENERATOR_UUID, new UUIDGenerator());
		map.put(GENERATOR_NUM, new NumberIDGenerator());
		map.put(GENERATOR_OID, new OrderIDGenerator());
		map.put(GENERATOR_PID, new ProductIDGenerator());
		map.put(GENERATOR_ECID, new OrderIDGenerator());
	}
	public static IdentifierGenerator getGenerator(String key) {
		Assert.hasText(key, "Generator key must not be null.");
		IdentifierGenerator generator = map.get(key.toLowerCase());
		Assert.notNull(generator, "This Key not found generator.[key=" + key + "]");
		return generator;
	}

//	/**
//	 * 自动生成主键；插入到object对象中;
//	 * @param object
//	 * @param classMeta
//	 */
//	public static void generatedKey(Object object, ClassMeta classMeta, String generatorKey) {
//		Assert.notNull(object, "Object entity must not be null！");
//		Assert.notNull(classMeta, "Method classMeta must not be null");
//		Assert.hasText(generatorKey, "Generator key must not be null");
//		IdentifierGenerator generator = getGenerator(generatorKey);
//		generatedKey(object, classMeta, generator);
//	}

//	/**
//	 * 自动生成主键；插入到object对象中;
//	 * @param object
//	 * @param classMeta
//	 */
//	public static void generatedKey(Object object, ClassMeta classMeta, IdentifierGenerator generator) {
//		Assert.notNull(object, "Object entity must not be null！");
//		Assert.notNull(classMeta, "Method classMeta must not be null");
//		Assert.notNull(generator, "Generator must not be null");
//		String identifier = generator.generate().toString();
//		setEntityPrimary(object, classMeta, identifier);
//	}

//	public static void setEntityPrimary(Object object, ClassMeta classMeta, Object identifier) {
//		Map<String, String> mapping = classMeta.getMapping();
//		String getValueMethod = null;
//		for(Map.Entry<String, String> entry : mapping.entrySet()) {
//			if(entry.getValue().equals(classMeta.getPrimaryKeyName())) {
//				getValueMethod = entry.getKey();
//				break;
//			}
//		}
//		Assert.notNull(getValueMethod, "ClassMeta primary column not defind");
//		Assert.notNull(getValueMethod.startsWith(MethodMeta.GETVALUE_METHOD_PREFIX), "Entity annotation require define be get method.");
//		String setValueMethod = getValueMethod.replaceFirst(MethodMeta.GETVALUE_METHOD_PREFIX, MethodMeta.SETVALUE_METHOD_PREFIX);
//
//		Method setMethod = ReflectionUtils.findMethod(object.getClass(), setValueMethod, classMeta.getPrimaryKeyClass());
//		Assert.notNull(setMethod, "Entity not found primary key set method.");
//		ReflectionUtils.invokeMethod(setMethod, object, identifier);
//	}

	public static void main(String[] args) {
//		System.out.println("getUigetdget".replaceFirst("get", "set"));
//		System.out.println(StringUtils.replace("getUigetdget", "get", "set"));

		long c = System.currentTimeMillis();
		System.out.println(c);
		for (int i = 0; i < 1000000; i++) {
			UUID.randomUUID().toString();
			//调用toString()方法性能下降一半；因为toString中进行计算返回结果集
		}
		long cc = System.currentTimeMillis();
		System.out.println(cc);
		System.out.println(cc - c);
		System.out.println("----------------------");
		c = System.currentTimeMillis();
		System.out.println(c);
		UUIDGenerator uuidGenerator = new UUIDGenerator();
		for (int i = 0; i < 1000000; i++) {
			uuidGenerator.generate();
		}
		cc = System.currentTimeMillis();
		System.out.println(cc);
		System.out.println(cc - c);
	}
}
