package com.jt.easymall.job;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.jt.easymall.mapper.OrderMapper;

public class OrderOTJob extends QuartzJobBean{
	//��ǰ������ִ���ߣ���Ҫ���ø�������������ʵ��
	//��ʱ��������ʱִ�еĴ���
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		/*
		 * ʵ��mapper��ot�����ĵ���
		 * ��ǰ�������������Ҫ���ð�jobdetail trigger scheduler��ЩʯӢ�ӵĺ������һ��ʹ�õģ��޷�ע��spring�Ķ���
		 * ��ȡspring�������Ķ���application
		 */
		ApplicationContext applicationContext= (ApplicationContext) context.getJobDetail().getJobDataMap().get("applicationContext");
		
		
			System.out.println("ִ��ǰ");
		
		//�������Ķ����ȡorderMapper,ִ��ɾ����ʱ�����ķ���
		Date lastDay=new Date(new Date().getTime()-1000*60*60*24);
		applicationContext.getBean(OrderMapper.class).deleteOTOrders(lastDay);
	
			System.out.println("ִ�к�");
	
		
	
	}
}
