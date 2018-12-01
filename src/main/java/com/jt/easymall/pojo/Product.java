package com.jt.easymall.pojo;

public class Product {
	/*
	 * product_idchar(36) NOT NULL
	product_namevarchar(100) NULL
	product_pricedouble NULL
	product_categoryvarchar(100) NULL
	product_imgurlvarchar(500) NULL
	product_numint(11) NULL
	product_descriptionvarchar(255) NULL
	 * 
	 */
	
	private String productId;
	private String productName;
	private Double productPrice;
	private String productCategory;
	private String productImgurl;
	private Integer productNum;
	private String productDescription;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getProductImgurl() {
		return productImgurl;
	}
	public void setProductImgurl(String productImgurl) {
		this.productImgurl = productImgurl;
	}
	public Integer getProductNum() {
		return productNum;
	}
	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	
}
