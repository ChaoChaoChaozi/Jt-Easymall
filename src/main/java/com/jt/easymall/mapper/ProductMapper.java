package com.jt.easymall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.easymall.pojo.Product;

public interface ProductMapper {
	//mybatis的注解,实现单个数据内容的参数传递
	List<Product> queryProducts(@Param("start")int start,@Param("rows")int rows);

	int queryCount();

	Product findProductById(String id);

	int saveProduct(Product product);

	int updateProduct(Product product);

	int deleteProduct(String id);
}
