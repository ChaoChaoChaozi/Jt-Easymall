package com.jt.easymall.pojo;

import java.io.Serializable;

public class User implements Serializable{
/*
 * user_idchar(36) NOT NULL�û�id uuid ����
	user_namevarchar(20) NOT NULL�û���½����
	user_passwordvarchar(32) NOT NULL�û����� md5
	user_nicknamevarchar(50) NULL�û��ǳ�
	user_emailvarchar(30) NULL�û�����
	user_typeint(11) NULL
 */
	private String userId;
	private String userName;
	private String userPassword;
	private String userNickname;
	private String userEmail;
	private int userType;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	
	
}
