package com.jipengblog.webadmin.utils.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.jipengblog.webadmin.common.SysConstants;
import com.jipengblog.webadmin.utils.PennUtils;
import com.jipengblog.webadmin.utils.security.enums.Algorithm;

/**
 * 签名算法，包括32位的md5和40位的sha-1
 * 
 * @author penn
 *
 */
public class SignatureUtils extends PennUtils {

	/**
	 * 不加加密盐
	 */
	public static String NO_SALT = "";
	/**
	 * 使用默认加密盐
	 */
	public static String DEFAULT_SALT = null;

	private MessageDigest messageDigest;
	private Algorithm defaulAlgorithm = Algorithm.MD5;
	private String salt;

	/**
	 * 默认使用MD5算法
	 */
	public SignatureUtils() {// 默认使用MD5算法
		try {
			messageDigest = MessageDigest.getInstance(defaulAlgorithm
					.toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			logger.error("实例化时参数[" + defaulAlgorithm + "]错误");
		}
	}

	/**
	 * 使用参数指定的算法
	 * 
	 * @param algorithm
	 *            算法名称
	 */
	public SignatureUtils(Algorithm algorithm) {
		try {
			messageDigest = MessageDigest.getInstance(algorithm.toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			logger.error("实例化时参数[" + algorithm + "]错误");
		}
	}

	/**
	 * 加密方法。
	 * 如果salt == null，	默认的盐加密。 
	 * 如果salt.equals("")，	不带盐加密
	 * 
	 * @param plaintext
	 *            明文
	 * @param salt
	 *            盐
	 * @return 密文
	 */
	public String encrypt(String plaintext, String salt) {
		if (salt == null) {
			this.salt = SysConstants.MD5_SALT;
		} else {
			this.salt = salt;
		}
		String finaltext = plaintext + this.salt;
		messageDigest.update(finaltext.getBytes());
		byte[] buffer = messageDigest.digest();
		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
			sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
			sb.append(Character.forDigit(buffer[i] & 15, 16));
		}
		return sb.toString();
	}
}
