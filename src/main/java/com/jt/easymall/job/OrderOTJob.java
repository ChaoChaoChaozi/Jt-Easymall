package com.jt.easymall.job;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.jt.easymall.mapper.OrderMapper;

public class OrderOTJob extends QuartzJobBean{
	//当前的任务执行者，需要调用父类的这个方法来实现
	//定时触发任务时执行的代码
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		/*
		 * 实现mapper的ot方法的调用
		 * 当前这个方法，是需要配置绑定jobdetail trigger scheduler这些石英钟的核心组件一起使用的；无法注入spring的对象
		 * 获取spring的上下文对象application
		 */
		ApplicationContext applicationContext= (ApplicationContext) context.getJobDetail().getJobDataMap().get("applicationContext");
		
		
			System.out.println("执行前");
		
		//用上下文对象获取orderMapper,执行删除超时订单的方法
		Date lastDay=new Date(new Date().getTime()-1000*60*60*24);
		applicationContext.getBean(OrderMapper.class).deleteOTOrders(lastDay);
	
			System.out.println("执行后");
	
		
	
	}
}
