package com.jt.easymall.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.easymall.pojo.Cart;
import com.jt.easymall.pojo.Order;
import com.jt.easymall.service.CartService;
import com.jt.easymall.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private CartService cartService;
	/*
	 * 	请求url:http://localhost/order/order-cart
		请求方式:get
		请求参数:空
		userId从拦截器判断,在controller的session获取数据
		使用userId查询购物车的数据(List<Cart>对象)
		返回数据:返回页面链接名称 "order_add"
		model携带从购物车查询的数据cartList添加到页面循环展示;
		
		持久层在order订单中不需要编写,使用的是cartMapper利用userId查询购物车商品;
	 */
	@RequestMapping("/order/order-cart")
	public String collertOrderInfo(HttpSession session,Model model){
		//从session中获取userId
		String userId=(String) session.getAttribute("userId");
		List<Cart> cartList=cartService.queryMycart(userId);
		model.addAttribute("cartList",cartList);
		return "order_add";
	}
	
	
	
	
	
	@Autowired
	private OrderService orderService;
	/**
	 * 新增订单
		请求url:http://localhost/order/addOrder
		请求方式:post
		请求参数:Order order
		备注:页面前端格式已经完成了对应一个Order对象的数据传递,由springmvc自动封装(名称要对应正确)
		返回的数据: 转向首页(因为没有订单成功页面)
		"index"; 可以从index首页直接访问我的订单查询
	----------------------------------------------------------------------------------
		Order对象如何在Mapper中insert新增到2张数据库表格
		利用的mysql一个特性;支持多条语句的插入
		• 一次命令传递多个insert语句
		INSERT INTO USER (id,NAME) VALUES('5','aaa');
		INSERT INTO USER (id,NAME) VALUES('6','bbb');
		INSERT INTO USER (id,NAME) VALUES('7','ccc');
		
		• 对相同表格的相同字段一次写入多组数据
		INSERT INTO USER (id,NAME) 
		VALUES('5','aaa'),('6','bbb'),('7','ccc')
	----------------------------------------------------------------------------------
		
		mybatis调用的连接对象是通过jdbc:mysql协议获取的,在协议中需要制定allowMultiadd的参数为true,才能开启这种多数据一次同时写入的操作
		jdbc_url=jdbc:mysql:///easymalldb?userUnicode=true&characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true


	 */
	@RequestMapping("order/addOrder")
	public String saveOrder(Order order,HttpSession session,Model model){
		//传递的order没有userId,需要从session获取封装
		order.setUserId(session.getAttribute("userId")+"");
		//没有成功或错误页面,不判断新增成败
		orderService.saveOrder(order);
		//return "index";
		
//		String userId=(String) session.getAttribute("userId");
//		List<Order> orderList=orderService.queryMyorder(userId);
//		model.addAttribute("orderList", orderList);
		
		return "redirect:/order/list";
	}
	
		
	//查询订单
	@RequestMapping("/order/list")
	public String queryMyorder(HttpSession session,Model model){
		String userId=(String) session.getAttribute("userId");
		List<Order> orderList=orderService.queryMyorder(userId);
		model.addAttribute("orderList", orderList);
		
		return "order_list";
	}
	
	
	/*
	 * 	请求url:/order/deleteOrder/{orderId}
	请求方式:get
	请求参数:String orderId
	restFul路径传参
	返回数据:重定向到"redirect:/order/list"
	DELETE a,b FROM t_order a,t_order_item b WHERE a.order_id=
b.order_id AND a.order_id=#{orderId}
	 */
	//删除订单
	@RequestMapping("order/deleteOrder/{orderId}")
	public String deleteOrder(@PathVariable String orderId){
		orderService.deleteOrder(orderId);
		//重定向到order/list，走到order_list.jsp;重新发起请求，走了一遍
		//查询controller
		//直接在当前方法返回页面解析到order_list.jsp
		return "redirect:/order/list";
	}
	
	
	//检查超时订单
	@RequestMapping("/check/orderOT")
	public String checkOrderOT(){
		orderService.deleteOTOrders();
		return "index";
	}
		
	
}
