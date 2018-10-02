package com.cfcp.incc.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 民族数据
 *
 * <p>
 *
 * @author zyj
 * @date 1/14/2015
 * @since 0.1
 */
public class NationData {
	public static final Map<Integer, String> DATA = new LinkedHashMap<>();
	public static final List<Integer> CODES = new ArrayList<>();
	static {
		DATA.put(1, "汉族");
		DATA.put(2, "蒙古族");
		DATA.put(3, "回族");
		DATA.put(4, "藏族");
		DATA.put(5, "维吾尔族");
		DATA.put(6, "苗族");
		DATA.put(7, "彝族");
		DATA.put(8, "壮族");
		DATA.put(9, "布依族");
		DATA.put(10, "朝鲜族");
		DATA.put(11, "满族");
		DATA.put(12, "侗族");
		DATA.put(13, "瑶族");
		DATA.put(14, "白族");
		DATA.put(15, "土家族");
		DATA.put(16, "哈尼族");
		DATA.put(17, "哈萨克族");
		DATA.put(18, "傣族");
		DATA.put(19, "黎族");
		DATA.put(20, "傈僳族");
		DATA.put(21, "佤族");
		DATA.put(22, "畲族");
		DATA.put(23, "高山族");
		DATA.put(24, "拉祜族");
		DATA.put(25, "水族");
		DATA.put(26, "东乡族");
		DATA.put(27, "纳西族");
		DATA.put(28, "景颇族");
		DATA.put(29, "柯尔克孜族");
		DATA.put(30, "土族");
		DATA.put(31, "达斡尔族");
		DATA.put(32, "仫佬族");
		DATA.put(33, "羌族");
		DATA.put(34, "布朗族");
		DATA.put(35, "撒拉族");
		DATA.put(36, "毛难族");
		DATA.put(37, "仡佬族");
		DATA.put(38, "锡伯族");
		DATA.put(39, "阿昌族");
		DATA.put(40, "普米族");
		DATA.put(41, "塔吉克族");
		DATA.put(42, "怒族");
		DATA.put(43, "乌孜别克族");
		DATA.put(44, "俄罗斯族");
		DATA.put(45, "鄂温克族");
		DATA.put(46, "崩龙族");
		DATA.put(47, "保安族");
		DATA.put(48, "裕固族");
		DATA.put(49, "京族");
		DATA.put(50, "塔塔尔族");
		DATA.put(51, "独龙族");
		DATA.put(52, "鄂伦春族");
		DATA.put(53, "赫哲族");
		DATA.put(54, "门巴族");
		DATA.put(55, "珞巴族");
		DATA.put(56, "基诺族");
		CODES.addAll(DATA.keySet());
	}

}
