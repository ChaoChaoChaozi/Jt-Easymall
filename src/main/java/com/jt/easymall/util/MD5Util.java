package com.jt.easymall.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	/** * ʹ��md5���㷨���м��� */
	public static String md5(String plainText) {
		if(plainText!=null){
			//��Ź�ϣֵ����� byte ���顣
			byte[] secretBytes = null;
			try {
				//getInstance("md5"):����ʵ��ָ��ժҪ�㷨�� MessageDigest ����
				//digest(byte[] ..)ʹ��ָ���� byte �����ժҪ���������£�Ȼ�����ժҪ����
				secretBytes = MessageDigest.getInstance("md5").digest(
						plainText.getBytes());
			} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException("û��md5����㷨��");
			}
			String md5code = new BigInteger(1, secretBytes).toString(16);
			for (int i = 0; i < 32 - md5code.length(); i++) {
				md5code = "0" + md5code;
			}
			return md5code;
		}else{
			return null;
		}
	}
	public static void main(String[] args) {
		System.out.println(md5("123"));
	}
}