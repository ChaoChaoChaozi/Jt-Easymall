package com.jt.easymall.service;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jt.easymall.util.UUIDUtil;
import com.jt.easymall.util.UploadUtil;
import com.jt.easymall.vo.PicUploadResult;

@Service
public class PicService {

	public PicUploadResult uploadPic(MultipartFile uploadFile) {
		/*
		 * 判断后缀
		 * 判断木马
		 * 生成路径
		 * 生成url
		 * 存盘
		 * 返回
		 * 一场一旦出现，不是有木马就是其他问题，返回result对象
		 * 
		 */
			//获取文件原名称
			String oldFileName=	uploadFile.getOriginalFilename();
		//截取后缀，从最后一个“.”，开始到名称末尾截取
			String extName=oldFileName.substring(oldFileName.lastIndexOf("."));
		//判断是否是jpg，png，gif一员，用正则表达式分组
			
			PicUploadResult result=new PicUploadResult();
			

			if (!extName.matches("^.(jpg|png|gif)$")) {
				
				result.setError(1);
				return result;
			}
			
			//判断木马，数据中，如果有宽和高，就证明是图片，如果没有，就不是图片
			try {//利用imageIO判断是否为图片数据
				BufferedImage image=ImageIO.read(uploadFile.getInputStream());
				result.setHeight(image.getHeight()+"");
				result.setWidth(image.getWidth()+"");
				
				String dir=UploadUtil.getUploadPath(oldFileName, "/upload");
				//根据传递测upload生成一个头
				//根据源文件名称生成散列的一个多级文件路径
				
				//upload/4/d....
				//生成粗盘的文件夹路径，可以用System.getProper
				String path="./src/main/webapp/"+dir+"/";
				File _dir=new File(path);
				if (!_dir.exists()) {
					_dir.mkdirs();
				}
				String fileName=UUIDUtil.getUUID()+extName;
				uploadFile.transferTo(new File(path+fileName));
				return result;

			} catch (Exception e) {
				result.setError(1);
				return result;
			}
			
	}

}
