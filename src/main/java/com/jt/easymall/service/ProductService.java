package com.jt.easymall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.easymall.mapper.ProductMapper;
import com.jt.easymall.pojo.Product;
import com.jt.easymall.service.ProductService;
import com.jt.easymall.util.UUIDUtil;

@Service
public class ProductService{
	@Autowired
	private ProductMapper productMapper;
	
	public List<Product> queryProducts(Integer currentPage, Integer rows) {
		//处理业务逻辑,page变成start,
		if(currentPage<=0){currentPage=1;}
		int start=(currentPage-1)*rows;//最小是0
		List<Product> pList=productMapper.
				queryProducts(start,rows);
		return pList;
	}
	
	public Integer queryTotalPage(Integer rows) {
		//商品的总个数,select count(*) from t_product
		int total=productMapper.queryCount();
		//如何利用total和rows计算总页数totalPage
		int totalPage=total%rows==0?(total/rows):((total/rows)+1);
		return totalPage;
	}

	public Product findProductById(String id) {
		Product product=productMapper.findProductById(id);
		return product;
	}

	public int saveProduct(Product product) {
		//ȱ��id,ʹ��uuid�Ĺ�����
		String id=UUIDUtil.getUUID();
		product.setProductId(id);
		int success=productMapper.saveProduct(product);
		return success;
	}

	public int queryTotal() {
		return productMapper.queryCount();
	}

	public int updateProduct(Product product) {
		int success=productMapper.updateProduct(product);
		return success;
	}

	public int deleteProduct(String[] ids) {
		int success=0;
		for (String id : ids) {
			 success+=productMapper.deleteProduct(id);
		}
		return success;
	}

}
