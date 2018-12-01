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
	 * 	�����ַ:/cart/mycart
	�������:��
	��ע:controller����Ҫʹ��session�����жϵ�¼�Ƿ�ɹ�
	String userId=session.getAttribute(userId);
	if(""||null) û��¼,ת���¼ҳ��
	if(��ֵ)�ߺ����߼�,�ò�Ͳ�,�øľ͸�
	��������: ���ع��ﳵ��ҳ���ַ���"cart"
	model��ҪЯ��һ����ΪcartList������userId�鵽�Ĺ��ﳵ���϶���,ͨ��foreachѭ��չʾ;
	���ݿ��ѯ���:select * from t_cart where user_id=#{userId}
	 */
	@Autowired
	private CartService cartService;
	@RequestMapping("cart/mycart")
	public String showMyCart(HttpSession session,Model model){
		//�жϵ�ǰ�����ж�Ӧ��session�Ƿ񱣴�userId,�о͵�¼��,
		String userId=(String)session.getAttribute("userId");
		//apache.common.lang3 ��java.lang������
		//StringUtils.isNotEmpty�жϲ����Ƿ�Ϊnull��""
		//��¼�ɹ�;����service��ѯ����;
		List<Cart> cartList=cartService.queryMycart(userId);
		model.addAttribute("cartList", cartList);
		return "cart";
	}
	
	//�������ﳵ
	@RequestMapping("cart/addCart/{productId}/{num}")
	public String addCart(@PathVariable String productId,
			@PathVariable Integer num,HttpSession session){
		String userId=(String)session.getAttribute("userId");
		int success=cartService.addCart(userId,productId,num);
		if(success==1){//�����ɹ�,update,insert
			return "redirect:/cart/mycart";//�ض���
			//�ҵĹ��ﳵ,�����²�ѯ,�����͸��µ����ݾͻ������µ�չʾ
		}else{
			return "index";
		}
	}
	
	//������Ʒ����
	@RequestMapping("cart/editCart/{productId}/{num}")
	public String updateCartNum(@PathVariable String productId,
			@PathVariable Integer num,HttpSession session){
		//�жϵ�¼
		String userId=(String)session.getAttribute("userId");
		cartService.updateCartNum(userId,productId,num);
		return "redirect:/cart/mycart";
	}
}









