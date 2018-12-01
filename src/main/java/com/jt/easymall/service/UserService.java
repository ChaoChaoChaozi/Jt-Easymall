package com.jt.easymall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.easymall.mapper.UserMapper;
import com.jt.easymall.pojo.User;
import com.jt.easymall.util.MD5Util;
import com.jt.easymall.util.UUIDUtil;


@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	public  int checkUserName(String userName) {
		int exist=userMapper.checkUserName(userName);
		return exist;
	}

	public int regist(User user) {
		user.setUserId(UUIDUtil.getUUID());
		user.setUserType(0);
		user.setUserPassword(MD5Util.md5(user.getUserPassword()));
		return userMapper.insertUser(user);
	}

	public User login(User user){
		user.setUserPassword(MD5Util.md5(user.getUserPassword()));
		User exists=userMapper.checkLogin(user);
		return exists;
	}
	
	
	
	
}
