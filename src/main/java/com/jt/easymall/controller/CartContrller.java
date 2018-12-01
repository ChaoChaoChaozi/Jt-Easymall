package com.jt.easymall.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.easymall.pojo.Cart;
import com.jt.easymall.service.CartService;

@Controller
public class CartContrller{
	
	/*
	 * 	请求地址:/cart/mycart
	请求参数:空
	备注:controller中需要使用session对象判断登录是否成功
	String userId=session.getAttribute(userId);
	if(""||null) 没登录,转向登录页面
	if(有值)走后续逻辑,该查就查,该改就改
	返回数据: 返回购物车的页面字符串"cart"
	model需要携带一个名为cartList的利用userId查到的购物车集合对象,通过foreach循环展示;
	数据库查询语句:select * from t_cart where user_id=#{userId}
	 */
	@Autowired
	private CartService cartService;
	@RequestMapping("cart/mycart")
	public String showMyCart(HttpSession session,Model model){
		//判断当前请求中对应的session是否保存userId,有就登录了,
		String userId=(String)session.getAttribute("userId");
		//apache.common.lang3 对java.lang的扩充
		//StringUtils.isNotEmpty判断参数是否为null或""
		//登录成功;调用service查询数据;
		List<Cart> cartList=cartService.queryMycart(userId);
		model.addAttribute("cartList", cartList);
		return "cart";
	}
	
	//新增购物车
	@RequestMapping("cart/addCart/{productId}/{num}")
	public String addCart(@PathVariable String productId,
			@PathVariable Integer num,HttpSession session){
		String userId=(String)session.getAttribute("userId");
		int success=cartService.addCart(userId,productId,num);
		if(success==1){//新增成功,update,insert
			return "redirect:/cart/mycart";//重定向到
			//我的购物车,做重新查询,新增和更新的数据就会做最新的展示
		}else{
			return "index";
		}
	}
	
	//更新商品数量
	@RequestMapping("cart/editCart/{productId}/{num}")
	public String updateCartNum(@PathVariable String productId,
			@PathVariable Integer num,HttpSession session){
		//判断登录
		String userId=(String)session.getAttribute("userId");
		cartService.updateCartNum(userId,productId,num);
		return "redirect:/cart/mycart";
	}
}









