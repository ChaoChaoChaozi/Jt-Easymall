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
		//第一个问题,数据是否已经在购物车存在了,userId,productId
		//做一个查询的对象,_cart
		Cart _cart=new Cart();
		_cart.setUserId(userId);
		_cart.setProductId(productId);
		//查询一个购物车商品数据
		Cart exists=cartMapper.findOne(_cart);
		if(null==exists){//新增数据
			//第二个问题,差三个字段 productImage productPrice name
			//商品表格;
			Product product=productMapper.
					findProductById(productId);
			_cart.setNum(num);
			_cart.setProductImage(product.getProductImgurl());
			_cart.setProductName(product.getProductName());
			_cart.setProductPrice(product.getProductPrice());
			success=cartMapper.addCart(_cart);
		}else{//已经存在的购物车商品,直接修改num更新
			exists.setNum(exists.getNum()+num);
			success=cartMapper.updateCartNum(exists);
			//update t_cart set num=#{num} where id=#{id}
		}
		return success;
	}
	public void updateCartNum(String userId, String productId, Integer num) {
		//封装一个变量,cart
		Cart cart =new Cart();
		cart.setNum(num);
		cart.setUserId(userId);
		cart.setProductId(productId);
		cartMapper.updateCartNum(cart);
	}

}








