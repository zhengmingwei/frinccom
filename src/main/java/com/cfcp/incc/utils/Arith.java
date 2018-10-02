package com.cfcp.incc.utils;

import java.math.BigDecimal;

/**
 * 提供精确算术计算的工具
 *
 * @author Rambo
 * @date 2017/01/02 0030
 * @since 0.1
 */
public class Arith {
	/**
	 * 提供精确加法计算的add方法
	 *
	 * @param value1 被加数
	 * @param value2 加数
	 * @param scale 精确范围
	 * @return 两个参数的和
	 */
	public static double add(double value1, double value2, int scale) {
		//如果精确范围小于0，默认为2
		if (scale < 0) {
			scale = 2;
		}
		BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
		BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
		//return b1.add(b2).doubleValue();
		return b1.add(b2).setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确减法运算的sub方法
	 *
	 * @param value1 被减数
	 * @param value2 减数
	 * @param scale 精确范围
	 * @return 两个参数的差
	 */
	public static double sub(double value1, double value2, int scale) {
		//如果精确范围小于0，默认为2
		if (scale < 0) {
			scale = 2;
		}
		BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
		BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
//		return b1.subtract(b2).doubleValue();
		return b1.subtract(b2).setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确乘法运算的mul方法
	 *
	 * @param value1 被乘数
	 * @param value2 乘数
	 * @param scale 精确范围
	 * @return 两个参数的积
	 */
	public static double mul(double value1, double value2, int scale) {
		//如果精确范围小于0，默认为2
		if (scale < 0) {
			scale = 2;
		}
		BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
		BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
//		return b1.multiply(b2).doubleValue();
		return b1.multiply(b2).setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的除法运算方法div
	 *
	 * @param value1 被除数
	 * @param value2 除数
	 * @param scale  精确范围
	 * @return 两个参数的商
	 * @throws IllegalAccessException
	 */
	public static double div(double value1, double value2, int scale) {
		//如果精确范围小于0，默认为2
		if (scale < 0) {
			scale = 2;
		}
		BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
		BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
//		return b1.divide(b2, scale).doubleValue();
		return b1.divide(b2).setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 比较两个double类型数据大小
	 * @param a
	 * @param b
	 * @return -1 表示a<b; 0 表示a=b； 1表示a>b
	 */
	public static int compareTo(double a,double b){
		BigDecimal data1 = new BigDecimal(a);
		BigDecimal data2 = new BigDecimal(b);
		return data1.compareTo(data2);
	}

	public static void main(String[] args) {
		System.out.println(0.06+0.01);
		System.out.println(Arith.add(0.06,0.01,2));
		System.out.println(1.0-0.42);
		System.out.println(Arith.sub(1.0,0.42,2));
		System.out.println(4.015*100);
		System.out.println(Arith.mul(4.015,100,2));
		System.out.println(303.1/1000);
		System.out.println(Arith.div(303.1,1000,2));
	}
}