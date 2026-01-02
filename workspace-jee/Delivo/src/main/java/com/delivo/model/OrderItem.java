package com.delivo.model;

public class OrderItem {

	private String orderItem;
	private int orderItemId;
	private int orderId;
	private int menuId;
	private int quantity;
	private double totalPrice;
	
	
	

	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}



	public OrderItem(String orderItem, int orderItemId, int orderId, int menuId, int quantity, double totalPrice) {
		super();
		this.orderItem = orderItem;
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}
	
	
	public String getOrderItem() {
		return orderItem;
	}







	public void setOrderItem(String orderItem) {
		this.orderItem = orderItem;
	}







	public int getOrderItemId() {
		return orderItemId;
	}







	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}







	public int getOrderId() {
		return orderId;
	}







	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}







	public int getMenuId() {
		return menuId;
	}







	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}







	public int getQuantity() {
		return quantity;
	}







	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}







	public double getTotalPrice() {
		return totalPrice;
	}







	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}







	@Override
	public String toString() {
		return "OrderItem [menuId=" + menuId + ", orderId=" + orderId + ", orderItem=" + orderItem + ", orderItemId="
				+ orderItemId + ", quantity=" + quantity + ", totalPrice=" + totalPrice + "]";
	}













	
	
	
	
	
	
	
	
	
	
}
