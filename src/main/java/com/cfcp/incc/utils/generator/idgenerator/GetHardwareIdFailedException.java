package com.cfcp.incc.utils.generator.idgenerator;

/**
 * <p>
 *
 * @author zyj
 * @date 2016/4/7 0007
 * @since 0.1
 */
public class GetHardwareIdFailedException extends Exception {
	GetHardwareIdFailedException(String reason){
		super(reason);
	}
	GetHardwareIdFailedException(Exception e){
		super(e);
	}
}

