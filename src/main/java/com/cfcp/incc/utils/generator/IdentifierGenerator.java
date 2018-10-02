package com.cfcp.incc.utils.generator;


import java.io.Serializable;

/**
 * 主键生成器定义接口
 *
 * <p>
 *
 * @author zyj
 * @date 12/9/2014
 * @since 0.1
 */
public interface IdentifierGenerator {

	/**
	 * Generate a new identifier.
	 * @return
	 */
	public Serializable generate();
}
