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
		 * �жϺ�׺
		 * �ж�ľ��
		 * ����·��
		 * ����url
		 * ����
		 * ����
		 * һ��һ�����֣�������ľ������������⣬����result����
		 * 
		 */
			//��ȡ�ļ�ԭ����
			String oldFileName=	uploadFile.getOriginalFilename();
		//��ȡ��׺�������һ����.������ʼ������ĩβ��ȡ
			String extName=oldFileName.substring(oldFileName.lastIndexOf("."));
		//�ж��Ƿ���jpg��png��gifһԱ����������ʽ����
			
			PicUploadResult result=new PicUploadResult();
			

			if (!extName.matches("^.(jpg|png|gif)$")) {
				
				result.setError(1);
				return result;
			}
			
			//�ж�ľ�������У�����п�͸ߣ���֤����ͼƬ�����û�У��Ͳ���ͼƬ
			try {//����imageIO�ж��Ƿ�ΪͼƬ����
				BufferedImage image=ImageIO.read(uploadFile.getInputStream());
				result.setHeight(image.getHeight()+"");
				result.setWidth(image.getWidth()+"");
				
				String dir=UploadUtil.getUploadPath(oldFileName, "/upload");
				//���ݴ��ݲ�upload����һ��ͷ
				//����Դ�ļ���������ɢ�е�һ���༶�ļ�·��
				
				//upload/4/d....
				//���ɴ��̵��ļ���·����������System.getProper
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
