package com.jt.easymall.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.easymall.mapper.OrderMapper;
import com.jt.easymall.pojo.Order;
import com.jt.easymall.util.UUIDUtil;

@Service
public class OrderService {
	@Autowired
	private OrderMapper orderMapper;
	public void saveOrder(Order order) {
		//orderȱʧid,֧��״̬(����0���ж�δ֧����ʱ�Ķ���),����ʱ��
		order.setOrderId(UUIDUtil.getUUID());
		order.setOrderPaystate(0);
		order.setOrderTime(new Date());
		orderMapper.saveOrder(order);
	}
	public List<Order> queryMyorder(String userId) {
		
		return orderMapper.queryMyorder(userId);
	}
	public void deleteOrder(String orderId) {
		orderMapper.deleteOrder(orderId);
		
	}
	public void deleteOTOrders() {
		//����һ���ȵ�ǰʱ��С��1������ڲ���
		Date lastDay=new Date(new Date().getTime()-1000*60*60*24);
		orderMapper.deleteOTOrders(lastDay);
		
	}

}









