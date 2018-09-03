package com.whiuni.fastshop.vo;

public class OrderVO {
	private int id;
	private int userId;
	private String productId;
	private String address;
	private String productName;
	private String price;
	private String imageUrl;
	
	@Override
	public String toString() {
		return "OrderVO [id=" + id + ", userId=" + userId + ", productId=" + productId + ", address=" + address
				+ ", productName=" + productName + ", price=" + price + ", imageUrl=" + imageUrl + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}