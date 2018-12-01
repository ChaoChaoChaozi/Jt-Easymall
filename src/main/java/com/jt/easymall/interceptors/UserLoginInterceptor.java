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
		//判断登录的逻辑
		//获取session会话对象
		HttpSession session= request.getSession();
		String userId=(String) session.getAttribute("userId");
		if (!StringUtils.isNotEmpty(userId)) {
			//重定向 登录界面
			response.sendRedirect("/page/login");
			return false;//当前逻辑拦截后不放行；controller接不到了
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
