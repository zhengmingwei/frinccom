package com.cfcp.incc.utils.generator;

import java.io.Serializable;
import java.util.UUID;

/**
 * UUID生成器
 *
 * @author zyj
 */
public class UUIDGenerator implements IdentifierGenerator {

	public UUIDGenerator() {
	}

	public static String getUuid(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-","");
	}

	private final static String sep = "";

	protected String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}
	@Override
	public Serializable generate(){
			return new StringBuffer(36)
				.append(getUuid()).append(sep)
				.toString();
	}
}

