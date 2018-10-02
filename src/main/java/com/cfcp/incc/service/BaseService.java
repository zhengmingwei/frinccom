package com.cfcp.incc.service;

import com.cfcp.incc.entity.User;
import com.cfcp.incc.security.UserContext;
import com.cfcp.incc.utils.generator.GeneratorComparator;
import com.github.pagehelper.Page;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 *
 * 业务接口定义类
 *
 * <p>
 *
 * @author zyj
 * @date 12/1/2014
 * @since 0.1
 */
public class BaseService {
	/*
	generateIdentifier(GeneratorComparator.GENERATOR_UUID)
	 */
	public String generateIdentifier(String idType) {
		return GeneratorComparator.getGenerator(idType).generate().toString();
	}

	public String generateNumIdentifier() {
		return GeneratorComparator.getGenerator(GeneratorComparator.GENERATOR_NUM).generate().toString();
	}

	/**
	 * 获取当前登录用户
	 * @return
	 */
	protected User getCurrentUser() {
		User user = UserContext.getCurrentUser();
//		User user = new User();
//		user.setId("1");
//		user.setDistributorId("1");
//		user.setName("test");
		return user;
	}

	/**
	 * 获取当前登录用户;如果没有登录抛出异常
	 * @return
	 */
	protected User getRequiredCurrentUser() {
		User user = getCurrentUser();
		Assert.notNull(user, "当前没有登录，无法使用此功能");
		return user;
	}

	protected Page initPage(Map<String, String> conditions){
		int pageNum = 1;
		if(StringUtils.hasLength(conditions.get("pageNum"))){
			pageNum = Integer.valueOf(conditions.get("pageNum"));
		}
		Page page=new Page(pageNum, 15);
		return page;
	}

	public static void main(String[] atges){
		String oid = GeneratorComparator.getGenerator(GeneratorComparator.GENERATOR_OID).generate().toString();
		String num = GeneratorComparator.getGenerator(GeneratorComparator.GENERATOR_NUM).generate().toString();
		String ecid = GeneratorComparator.getGenerator(GeneratorComparator.GENERATOR_ECID).generate().toString();
		String pid = GeneratorComparator.getGenerator(GeneratorComparator.GENERATOR_PID).generate().toString();
		String uuid = GeneratorComparator.getGenerator(GeneratorComparator.GENERATOR_UUID).generate().toString();
		System.err.println("oid : "+oid);
		System.err.println("num : "+num);
		System.err.println("pid : "+pid);
		System.err.println("uuid : "+uuid);

	}
}
