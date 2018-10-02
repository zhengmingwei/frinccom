package com.cfcp.incc.utils.generator.idgenerator;

/**
 * <p>
 *
 * @author zyj
 * @date 2016/4/7 0007
 * @since 0.1
 */
public interface EntityIdGenerator {
	String generateLongId() throws InvalidSystemClockException, GetHardwareIdFailedException;
}
