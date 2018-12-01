package com.jt.easymall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.easymall.mapper.CartMapper;
import com.jt.easymall.mapper.ProductMapper;
import com.jt.easymall.pojo.Cart;
import com.jt.easymall.pojo.Product;

@Service
public class CartService {
	@Autowired
	private CartMapper cartMapper;
	public List<Cart> queryMycart(String userId) {
		return cartMapper.queryMycart(userId);
	}
	@Autowired
	private ProductMapper productMapper;
	public int addCart(String userId, String productId, Integer num) {
		int success=0;
		//��һ������,�����Ƿ��Ѿ��ڹ��ﳵ������,userId,productId
		//��һ����ѯ�Ķ���,_cart
		Cart _cart=new Cart();
		_cart.setUserId(userId);
		_cart.setProductId(productId);
		//��ѯһ�����ﳵ��Ʒ����
		Cart exists=cartMapper.findOne(_cart);
		if(null==exists){//��������
			//�ڶ�������,�������ֶ� productImage productPrice name
			//��Ʒ���;
			Product product=productMapper.
					findProductById(productId);
			_cart.setNum(num);
			_cart.setProductImage(product.getProductImgurl());
			_cart.setProductName(product.getProductName());
			_cart.setProductPrice(product.getProductPrice());
			success=cartMapper.addCart(_cart);
		}else{//�Ѿ����ڵĹ��ﳵ��Ʒ,ֱ���޸�num����
			exists.setNum(exists.getNum()+num);
			success=cartMapper.updateCartNum(exists);
			//update t_cart set num=#{num} where id=#{id}
		}
		return success;
	}
	public void updateCartNum(String userId, String productId, Integer num) {
		//��װһ������,cart
		Cart cart =new Cart();
		cart.setNum(num);
		cart.setUserId(userId);
		cart.setProductId(productId);
		cartMapper.updateCartNum(cart);
	}

}








