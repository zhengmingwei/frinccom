package com.cfcp.incc.utils.generator;

import com.cfcp.incc.utils.DateUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Ummo on 2015/3/26.
 * @author zyj
 * @date 2015-11-06
 */
public class ProductIDGenerator extends UUIDGenerator {

//	public String prefix = "10001";
//	public String dateFormat = "yyyyMMdd";
//
//	@Override
//	public Serializable generate() {
//		int uuid = super.generate().hashCode();
//		uuid = Math.abs(uuid);
//		String s = uuid + "";
//		return prefix + DateUtils.getCurrentDate(dateFormat) + String.format("%010d", uuid);
//	}

	public String prefix = "";
	public String dateFormat = "yyMMdd";

	public static Set<String> TODAY_PID = Collections.synchronizedSet(new HashSet<String>());

	@Override
	public Serializable generate() {
		String postfix = DateUtils.getCurrentDate(dateFormat) + random();
		String pid = prefix + postfix;
		if(TODAY_PID.contains(pid)) {
			pid = generate().toString();
		}
		return pid;
	}

	private int max = 999;
	private int min = 100;
	private int random() {
		Random random = new Random();
		int s = random.nextInt(max)%(max-min+1) + min;
		return s;
	}

	public static void main(String[] args) {
		ProductIDGenerator productIDGenerator = new ProductIDGenerator();
		for (int i = 0; i < 10; i++) {
			System.out.println(productIDGenerator.generate());
		}
	}
}
