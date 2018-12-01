package com.jt.easymall.pojo;

import java.io.Serializable;

public class User implements Serializable{
/*
 * user_idchar(36) NOT NULL用户id uuid 主键
	user_namevarchar(20) NOT NULL用户登陆名称
	user_passwordvarchar(32) NOT NULL用户密码 md5
	user_nicknamevarchar(50) NULL用户昵称
	user_emailvarchar(30) NULL用户邮箱
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
