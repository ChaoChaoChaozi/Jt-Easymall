package com.jt.easymall.mapper;

import java.util.List;

import com.jt.easymall.pojo.Cart;

public interface CartMapper {

	public  List<Cart> queryMycart(String userId);

	public Cart findOne(Cart _cart);

	public int updateCartNum(Cart exists);

	public int addCart(Cart _cart);

	//public int updateCartNum(Cart exists);

}
