package com.cfcp.incc.utils;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;


/**
 * 信息摘要，可以获得CRC32、MD5、SHA-1值
 *
 * @author zyj
 * @version 1.0
 */
public class MessageDigestUtils {

	/**
	 * 计算文件的32位循环冗余校验和CRC32
	 *
	 * @param filepath 文件名
	 * @return String
	 * @throws IOException IOException异常
	 */
	public static String crc32Digest(String filepath) {
		File file = new File(filepath);
		if (file.exists()) {
			return crc32Digest(file);
		}
		return null;
	}

	/**
	 * 计算文件的32位循环冗余校验和CRC32
	 *
	 * @param file 文件
	 * @return String
	 * @throws IOException IOException异常
	 */
	public static String crc32Digest(File file) {
		String digest = null;
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(file));
			digest = crc32Digest(in);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return digest;
	}

	/**
	 * 计算输入流的32位循环冗余校验和CRC32
	 * @return
	 */
	public static String crc32Digest(InputStream inputStream) throws IOException {
		String digest = null;
		if(inputStream == null) {
			throw new IOException("文件流为空，不能调用此方法！");
		}
		CRC32 crc = new CRC32();
		int c;
		while ((c = inputStream.read()) != -1) {
			crc.update(c);
		}
		digest = Long.toHexString(crc.getValue());
		return digest;
	}

	/**
	 * 计算字符串的MD5值
	 *
	 * @param input 字符串
	 * @return String
	 */
	public static String md5Digest(String input) {

		byte[] data = input.getBytes();
		try {
			MessageDigest messageDigest = getMD5();
			messageDigest.update(data);
			return toHexString(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	/**
	 * 计算文件的MD5值
	 *
	 * @param filepath 文件名
	 * @return String
	 */
	public static String md5FileDigest(String filepath) {
		String md5 = "";
		File file = new File(filepath);
		if (file.exists()) {
			md5 = md5FileDigest(file);
		}
		return md5;
	}

	/**
	 * 计算文件的MD5值
	 *
	 * @param file 文件
	 * @return String
	 */
	public static String md5FileDigest(File file) {
		String md5 = null;
		InputStream in = null;
		if (file.exists()) {
			try {
				in = new BufferedInputStream(new FileInputStream(file));
				md5 = md5FileDigest(in);
			} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException(e.getMessage());
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e.getMessage());
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			} finally {
				if(in != null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return md5;
	}

	/**
	 * 计算输入流的MD5值
	 * @param inputStream
	 * @return
	 */
	public static String md5FileDigest(InputStream inputStream) throws IOException, NoSuchAlgorithmException {
		String digest = null;
		if(inputStream == null) {
			throw new IOException("文件流为空，不能调用此方法！");
		}
		MessageDigest messageDigest = getMD5();
		byte[] cache = new byte[CACHE_SIZE];
		int len;
		while ((len = inputStream.read(cache)) != -1) {
			messageDigest.update(cache, 0, len);
		}
		byte[] data = messageDigest.digest();
		digest = toHexString(data);
		return digest;
	}

	/**
	 * 计算字符串的SHA-1值
	 *
	 * @param input 字符串
	 * @return String
	 */
	public static String sha1Digest(String input) {

		byte[] data = input.getBytes();
		try {
			MessageDigest messageDigest = getSHA1();
			messageDigest.update(data);
			return toHexString(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			System.out.println("SHA1算法初始化失败!");
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 计算文件的SHA-1值
	 *
	 * @param filepath 文件名
	 * @return String
	 */
	public static String sha1FileDigest(String filepath) {
		String sha1 = "";
		File file = new File(filepath);
		if (file.exists()) {
			sha1 = sha1FileDigest(file);
		}
		return sha1;
	}

	/**
	 * 计算文件的SHA-1值
	 *
	 * @param file 文件
	 * @return String
	 */
	public static String sha1FileDigest(File file) {

		String sha1 = "";
		InputStream in = null;
		if (file.exists()) {
			try {
				in = new BufferedInputStream(new FileInputStream(file));
				sha1 = sha1FileDigest(in);
			} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException(e.getMessage());
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e.getMessage());
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			} finally {
				if(in != null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return sha1;

	}

	/**
	 * 计算输入流的SHA-1值
	 * @param inputStream
	 * @return
	 */
	public static String sha1FileDigest(InputStream inputStream) throws IOException, NoSuchAlgorithmException {
		String digest = null;
		if(inputStream == null) {
			throw new IOException("文件流为空，不能调用此方法！");
		}
		MessageDigest messageDigest = getSHA1();
		byte[] cache = new byte[CACHE_SIZE];
		int len;
		while ((len = inputStream.read(cache)) != -1) {
			messageDigest.update(cache, 0, len);
		}
		byte[] data = messageDigest.digest();
		digest = toHexString(data);
		return digest;
	}

	/**
	 * 计算输入流的SHA-1值
	 * @param bytes
	 * @return
	 */
	public static String sha1FileDigest(byte[] bytes) throws IOException, NoSuchAlgorithmException {
		String digest = null;
		if(bytes == null) {
			throw new IOException("字节为空，不能调用此方法！");
		}
		MessageDigest messageDigest = getSHA1();
		byte[] data = messageDigest.digest(bytes);
		digest = toHexString(data);
		return digest;
	}

	/**
	 * 字节数组转换为16进制字符串
	 *
	 * @param data 字节数组
	 * @return String
	 */
	private static String toHexString(byte[] data) {
		StringBuilder digestStr = new StringBuilder();
		String stmp = "";
		for (int i = 0; i < data.length; i++) {
			stmp = Integer.toHexString(data[i] & 0XFF);
			if (stmp.length() == 1) {
				digestStr.append("0" + stmp);
			} else {
				digestStr.append(stmp);
			}
		}
		return digestStr.toString();
	}

	/**
	 * 获取MD5实例
	 *
	 * @return MessageDigest
	 * @throws NoSuchAlgorithmException 异常
	 */
	private static MessageDigest getMD5() throws NoSuchAlgorithmException {
		return MessageDigest.getInstance(ALGORIGTHM_MD5);
	}

	private static MessageDigest getSHA1() throws NoSuchAlgorithmException {
		return MessageDigest.getInstance(ALGORIGTHM_SHA1);
	}

	/**
	 * MD5算法名称
	 */
	private static final String ALGORIGTHM_MD5 = "MD5";
	/**
	 * SHA-1算法名称
	 */
	private static final String ALGORIGTHM_SHA1 = "SHA-1";
	/**
	 * 字节数组缓存大小
	 */
	private static final int CACHE_SIZE = 2048;

	public static void main(String[] args) {
		System.out.println(sha1Digest("admin"));
		System.out.println(md5Digest("admin"));
	}
}