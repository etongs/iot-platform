package com.stanley.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/** 
 * @Module core
 * @Function 加密工具类
 * @Description       		
 * @Copyright © 2015, www.ssit-xm.com.cn, All Rights Reserved
 * @since 1.0   
 * @version 1.0  
 * @author 童晟
 * @see 
 */
public class EncryptUtil {
	
	/** 
	  * @Description MD5加密<p>
	  * @param plaintext 加密的明文,charsetName 字符编码
	  * @return  String 加密的密文
	  * @author 童晟
	  * @throws NoSuchAlgorithmException 
	  * @throws UnsupportedEncodingException 
	*/
	public static String MD5(String plaintext,String charsetName) throws NoSuchAlgorithmException, UnsupportedEncodingException  {
		MessageDigest crypt = MessageDigest.getInstance("MD5");
        crypt.reset();
        crypt.update(plaintext.getBytes(charsetName));
        return byteToHex(crypt.digest());
	}
	
	/** 
	  * @Description MD5加密<p>
	  * @param plaintext 加密的明文,字符编码默认 UTF-8
	  * @return  String 加密的密文
	  * @author 童晟
	  * @throws NoSuchAlgorithmException 
	  * @throws UnsupportedEncodingException 
	*/
	public static String MD5(String plaintext) {
	   try {
		   MessageDigest crypt = MessageDigest.getInstance("MD5");
	       crypt.reset();
	       crypt.update(plaintext.getBytes(Constants.CHARSET));
	       return byteToHex(crypt.digest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/** 
	  * @Description SHA1加密<p>
	  * @param plaintext 加密的明文,charsetName 字符编码
	  * @return  String 加密的密文
	  * @author 童晟
	  * @throws NoSuchAlgorithmException 
	  * @throws UnsupportedEncodingException 
	*/
	public static String SHA1(String plaintext,String charsetName) throws NoSuchAlgorithmException, UnsupportedEncodingException  {
	   MessageDigest crypt = MessageDigest.getInstance("SHA1");
       crypt.reset();
       crypt.update(plaintext.getBytes(charsetName));
       return byteToHex(crypt.digest());
		
	}
	
	/** 
	  * @Description SHA1加密<p>
	  * @param plaintext 加密的明文,字符编码默认 UTF-8
	  * @return  String 加密的密文
	  * @author 童晟
	  * @throws NoSuchAlgorithmException 
	  * @throws UnsupportedEncodingException 
	*/
	public static String SHA1(String plaintext) throws NoSuchAlgorithmException, UnsupportedEncodingException  {
	  MessageDigest crypt = MessageDigest.getInstance("SHA1");
      crypt.reset();
      crypt.update(plaintext.getBytes(Constants.CHARSET));
      return byteToHex(crypt.digest());
		
	}
	
	/** 
	  * @Description SHA256加密<p>
	  * @param plaintext 加密的明文,charsetName 字符编码
	  * @return  String 加密的密文
	  * @author 童晟
	  * @throws NoSuchAlgorithmException 
	  * @throws UnsupportedEncodingException 
	*/
	public static String SHA256(String plaintext,String charsetName) throws NoSuchAlgorithmException, UnsupportedEncodingException  {
	  MessageDigest crypt = MessageDigest.getInstance("SHA-256");
      crypt.reset();
      crypt.update(plaintext.getBytes(charsetName));
      return byteToHex(crypt.digest());
		
	}
	
	/** 
	  * @Description SHA256加密<p>
	  * @param plaintext 加密的明文,字符编码默认 UTF-8
	  * @return  String 加密的密文
	  * @author 童晟
	  * @throws NoSuchAlgorithmException 
	  * @throws UnsupportedEncodingException 
	*/
	public static String SHA256(String plaintext) {
		MessageDigest crypt = null;
		try {
			crypt = MessageDigest.getInstance("SHA-256");
			crypt.reset();
		    crypt.update(plaintext.getBytes(Constants.CHARSET));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return byteToHex(crypt.digest());
	}

	/**
	@Description 字节转化为哈希字符串<p>
	@param
	@return  String
    @author 童晟
    */
	public static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }


	/**
	 * @Description shiro的加密工具，需要提供盐值
	 * @date 2017/9/18
	 * @author 13346450@qq.com 童晟
	 * @param passwd 密码明文
	 * @param salt 盐值
	 * @return
	 */
	public static String shiroEncrypt(String passwd, String salt){
		return new SimpleHash(Constants.SHIRO_ENCRYPT_ALGORITHM, passwd,
				ByteSource.Util.bytes(salt), Constants.SHIRO_ENCRYPT_TIMES).toHex();
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(shiroEncrypt("aaaa","admin"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
