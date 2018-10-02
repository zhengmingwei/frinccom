/**
 *
 */
package com.cfcp.incc.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * 密码加密
 * @author Tairong Zou Rambo
 */
public final class MD5PasswordEncoder {

	public static final String SALT_SECRET = "cfcp";

	/**
	 * 将密码用MD5加密
	 * @param rawPass 密码明文
	 * @param salt    密钥
	 * @return 返加密码明文的MD5摘要
	 */
	public final static String encode(String rawPass, String salt) {
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		md5.setEncodeHashAsBase64(false);

		return md5.encodePassword(rawPass, salt);
	}

	/**
	 * 验证密码
	 * @param encPass 密码MD5摘要
	 * @param rawPass 密码明文
	 * @param salt    密钥
	 * @return 如果明文与摘要匹配返回true, 反之返回false
	 */
	public final static boolean valid(String encPass, String rawPass, String salt) {
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		md5.setEncodeHashAsBase64(false);

		return md5.isPasswordValid(encPass, rawPass, salt);
	}

	public static void main(String[] args) {
		String enPassword = MD5PasswordEncoder.encode("111111",SALT_SECRET);
		System.out.println(enPassword);
		System.out.println(MD5PasswordEncoder.valid(enPassword,"test",SALT_SECRET));
	}
}