package com.jt.easymall.controller;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.easymall.pojo.User;
import com.jt.easymall.service.UserService;
import com.jt.easymall.vo.SysResult;

@Controller
public class UserController {
	
	/*
	 * 请求url：http://localhost/user_ajax/checkUserName
	请求方式: post
	请求参数: String userName;
	备注: select * from t_user where user_name=#{userName};
	如果返回值非空,说明当前用户名已经被使用了
	如果为空才可以使用;
	返回值:SysResult对象,转化json(@ResponseBody)
	status:0表示可用,1表示已存在
	 */
	@Autowired
	private UserService userService;
	@RequestMapping("/user_ajax/checkUserName")
	@ResponseBody
	public SysResult checkUserName(String userName){
		//业务层不是返回1表示存在,就是返回0表示不存在
		int exists=userService.checkUserName(userName);
		SysResult result=new SysResult();
		result.setStatus(exists);
		return result;
	}
	
	/*
	 * 	接口文件
	请求url:http://localhost/user_ajax/regist
	请求方式:post
	请求参数:User user 
	返回数据:SysResult的对象json字符串,1表示注册成功,0表示失败
	返回成功之后,页面跳转到/page/login
	 */
	@RequestMapping("/user_ajax/regist")
	@ResponseBody
	public SysResult regist(User user) throws Exception{
		SysResult result =new SysResult();
		//收到一个没有type类型,没有userId的对象
		int success=userService.regist(user);
		result.setStatus(success);
		if(success==1){//成功
			//处理ajax乱码
			String msg="当前用户:"+user.getUserName()+"注册成功";
			result.setMsg(msg);
		}else{
			String msg="当前用户:"+user.getUserName()+"注册失败";
			result.setMsg(msg);
		}
		return result;
	}
	
	//user_ajax/login
	@RequestMapping("/user_ajax/login")
	@ResponseBody
	public SysResult login(User user,HttpSession session){
		User exists=userService.login(user);
		SysResult result=new SysResult();
		
		//返回的user已存在,如果为空,说明登录失败
		if(null==exists){
			//登录失败
			result.setStatus(0);
			result.setMsg("用户名密码错误");
			return result;
		}else{
			//当前一次会话存储session域对象的数据,
			//客户端只要不关闭浏览器当前页面
			//就30分钟内使用同一个session访问服务器,
			//携带user信息判断登录
			//是否正常
			session.setAttribute("userName", exists.getUserName());
			session.setAttribute("userId", exists.getUserId());
			result.setStatus(1);
			return result;
		}
		
	}
	//注销登录
	
	@RequestMapping("/user_ajax/logout")
	public String logout(HttpSession session){
		//清空session中的数据
		session.removeAttribute("userId");
		
		session.removeAttribute("userName");
		return "redirect:/";
	}
}

















































