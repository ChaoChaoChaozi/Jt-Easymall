package com.jt.easymall.util;



public class UploadUtil {
	
	public static String getUploadPath(String fileName,String upload){
		
		//��Ŀ¼�洢,����洢·��
		String hash = Integer.toHexString(fileName.hashCode());
		while(hash.length()<8){
			hash += "0";
		}
		for (int i = 0; i < hash.length(); i++) {
			upload += "/"+hash.charAt(i);
		}
		
		
		return upload;
	}
}
