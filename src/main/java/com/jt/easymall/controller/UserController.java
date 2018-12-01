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
	 * ����url��http://localhost/user_ajax/checkUserName
	����ʽ: post
	�������: String userName;
	��ע: select * from t_user where user_name=#{userName};
	�������ֵ�ǿ�,˵����ǰ�û����Ѿ���ʹ����
	���Ϊ�ղſ���ʹ��;
	����ֵ:SysResult����,ת��json(@ResponseBody)
	status:0��ʾ����,1��ʾ�Ѵ���
	 */
	@Autowired
	private UserService userService;
	@RequestMapping("/user_ajax/checkUserName")
	@ResponseBody
	public SysResult checkUserName(String userName){
		//ҵ��㲻�Ƿ���1��ʾ����,���Ƿ���0��ʾ������
		int exists=userService.checkUserName(userName);
		SysResult result=new SysResult();
		result.setStatus(exists);
		return result;
	}
	
	/*
	 * 	�ӿ��ļ�
	����url:http://localhost/user_ajax/regist
	����ʽ:post
	�������:User user 
	��������:SysResult�Ķ���json�ַ���,1��ʾע��ɹ�,0��ʾʧ��
	���سɹ�֮��,ҳ����ת��/page/login
	 */
	@RequestMapping("/user_ajax/regist")
	@ResponseBody
	public SysResult regist(User user) throws Exception{
		SysResult result =new SysResult();
		//�յ�һ��û��type����,û��userId�Ķ���
		int success=userService.regist(user);
		result.setStatus(success);
		if(success==1){//�ɹ�
			//����ajax����
			String msg="��ǰ�û�:"+user.getUserName()+"ע��ɹ�";
			result.setMsg(msg);
		}else{
			String msg="��ǰ�û�:"+user.getUserName()+"ע��ʧ��";
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
		
		//���ص�user�Ѵ���,���Ϊ��,˵����¼ʧ��
		if(null==exists){
			//��¼ʧ��
			result.setStatus(0);
			result.setMsg("�û����������");
			return result;
		}else{
			//��ǰһ�λỰ�洢session����������,
			//�ͻ���ֻҪ���ر��������ǰҳ��
			//��30������ʹ��ͬһ��session���ʷ�����,
			//Я��user��Ϣ�жϵ�¼
			//�Ƿ�����
			session.setAttribute("userName", exists.getUserName());
			session.setAttribute("userId", exists.getUserId());
			result.setStatus(1);
			return result;
		}
		
	}
	//ע����¼
	
	@RequestMapping("/user_ajax/logout")
	public String logout(HttpSession session){
		//���session�е�����
		session.removeAttribute("userId");
		
		session.removeAttribute("userName");
		return "redirect:/";
	}
}

















































