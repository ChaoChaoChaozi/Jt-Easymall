package com.jt.easymall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexContriller {
	
	//添加首页的映射地址"/"
	@RequestMapping("/")
	public String goIndex(){
		return "index";//WEB-INF/views/index.jsp
	}
	
	//page/{pageName}的动态接残，返回页面名称
	@RequestMapping("/page/{pageName}")
	public String goPage(@PathVariable String pageName){
		return pageName;
		
	}
}
