package com.jipengblog.webadmin.test;

import java.security.MessageDigest;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		MessageDigest md = MessageDigest.getInstance("MD5");
		String s="df9f";    //将要加密的字符串 
		System.out.println("加密之前的字符串："+s); 
		md.update(s.getBytes()); //MD5加密算法只是对字符数组而不是字符串进行加密计算，得到要加密的对象 
	}

}
