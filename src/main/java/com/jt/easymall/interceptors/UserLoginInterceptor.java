package com.jt.easymall.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserLoginInterceptor implements HandlerInterceptor{
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//�жϵ�¼���߼�
		//��ȡsession�Ự����
		HttpSession session= request.getSession();
		String userId=(String) session.getAttribute("userId");
		if (!StringUtils.isNotEmpty(userId)) {
			//�ض��� ��¼����
			response.sendRedirect("/page/login");
			return false;//��ǰ�߼����غ󲻷��У�controller�Ӳ�����
		}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

	
	
}
