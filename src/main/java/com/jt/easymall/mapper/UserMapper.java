package com.jt.easymall.mapper;

import com.jt.easymall.pojo.User;

public interface UserMapper {

	int checkUserName(String userName) ;

	int insertUser(User user);

	User checkLogin(User user);

}
