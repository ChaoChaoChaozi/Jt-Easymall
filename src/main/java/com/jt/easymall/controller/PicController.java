package com.jt.easymall.controller;

import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jt.easymall.service.PicService;
import com.jt.easymall.vo.PicUploadResult;

@Controller
public class PicController {
	@Autowired
	private PicService picService;
	
	//ÉÏ´«Í¼Æ¬µÄÂß¼­
	@RequestMapping("/pic/upload")
	@ResponseBody
	public PicUploadResult uploadPic(MultipartFile uploadFile){
		PicUploadResult result=picService.uploadPic(uploadFile);
		return result;
	}
}
