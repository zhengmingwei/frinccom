package com.cfcp.incc.utils.generator;

import com.cfcp.incc.utils.DateUtils;

import java.io.Serializable;

/**
 * 数字ID生产策略；
 * 结合UUID生产算法hashcode;
 * <p>
 *     算法:四位年(2)+月份(2)+日(2)+uuid#hashCode(10);
 *     共16位
 *
 * @author zyj
 * @date 1/14/2015
 * @since 0.1
 */
public class NumberIDGenerator extends UUIDGenerator {

	public String prefixDateFormat = "yyyyMMdd";

	@Override
	public Serializable generate() {
		int uuid = super.generate().hashCode();
		uuid = Math.abs(uuid);
		return DateUtils.getCurrentDate(prefixDateFormat) + String.format("%010d", uuid);
	}

	public static void main(String[] args) {
		UUIDGenerator uuidGenerator = new NumberIDGenerator();
		for (int i = 0; i < 100; i++) {
			System.out.println(uuidGenerator.generate());
		}
	}
}
