package com.cfcp.incc.controller;

import com.github.pagehelper.Page;
import com.cfcp.incc.entity.User;
import com.cfcp.incc.security.UserContext;
import com.cfcp.incc.utils.generator.GeneratorComparator;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * 通用的Controller；
 * 建议所有Spring Mvc Controller子类集成
 *
 * <p>
 */
public class BaseController {

	protected static class AjaxStatus {
		public static final String RETURN_CODE = "returnCode";
		public static final String STATUS_FAILED = "6007";
		public static final String STATUS_SUCCESSFUL = "6006";
		public static final String PROPERTY_ERROR = "error";
	}
	protected static class PageType {
		public static final String DEFAULT = "DEFAULT";
		public static final String MID_20 = "MID_20";
		public static final String APP_INDEX = "APP_INDEX";
		public static final String TEST_INDEX = "TEST_INDEX";
		public static final String PERSON_PHOTO_INDEX = "PERSON_PHOTO_INDEX";
	}
	protected Map<String, Object> getRestMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(AjaxStatus.RETURN_CODE, AjaxStatus.STATUS_FAILED);
		map.put(AjaxStatus.PROPERTY_ERROR, "");
		return map;
	}

	protected static class SystemAccount {
		public static final String SYS_ADMIN_ACCOUNT_ID = "1";
		public static final String SYS_ADMIN_UID = "1";
	}

	/**
	 * generateIdentifier(GeneratorComparator.GENERATOR_UUID)
	 * @param idType
	 * @return
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

	/**
	 * 获取pageSize参数，默认为10（传入null或“”时为默认值）
	 * @param pageType 要求显示类型 DEFAULT = 10；
	 * @return 返回数
	 */
	protected int setPageSize(String pageType) {
		if(pageType == null || pageType.length() < 1){
			pageType = "DEFAULT";
		}
		final Map<String, Integer> TYPE_MAP = new LinkedHashMap<String, Integer>();
		TYPE_MAP.put(PageType.DEFAULT, 10);
		TYPE_MAP.put(PageType.MID_20, 20);
		TYPE_MAP.put(PageType.APP_INDEX, 6);
		TYPE_MAP.put(PageType.PERSON_PHOTO_INDEX, 8);
		TYPE_MAP.put(PageType.TEST_INDEX, 2);
		return TYPE_MAP.get(pageType);
	}

	/**
	 * 设置当前页数 默认为1
	 * @param pageNum 接收前台参数传入
	 * @return 返回当前页数
	 */
	protected int setPageNo(Integer pageNum) {
		int pageNo = 1;
		if(pageNum!=null){
			pageNo = Integer.valueOf(pageNum);
		}
		return pageNo;
	}

	/**
	 * 返回页对象
	 * @param pageNum
	 * @param pageType
	 * @return
	 */
	protected Page setPage(Integer pageNum, String pageType){
		Page page=new Page(setPageNo(pageNum),setPageSize(pageType));
//		page.setPageNum(setPageNo(pageNum));
//		page.setPageSize(setPageSize(pageType));
		return page;
	}

	/**
	 * 按查询条件和页面类型初始化page对象
	 * @param conditions
	 * @param pageType
     * @return
     */
	protected Page initPage(Map<String, String> conditions, String pageType){
		int pageNum = 1;
		if(StringUtils.hasLength(conditions.get("pageNum"))){
			pageNum = Integer.valueOf(conditions.get("pageNum"));
		}
		Page page=new Page(setPageNo(pageNum),setPageSize(pageType));
		return page;
	}
}
