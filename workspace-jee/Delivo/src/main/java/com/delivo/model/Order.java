package com.delivo.model;

import java.time.LocalDateTime;
import java.util.List;


public class Order {

	
	private int orderId;
	private int userId;
	private int restaurantid;
	private LocalDateTime orderDate;
	private double totalAmount;
	private String status;
	private String paymentMode;
	

	private List<OrderItem> orderItems;
	
	
	
	
	public Order(int orderId, int userId, int restaurantid, LocalDateTime orderDate, double totalAmount, String status,
			String paymentMode) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.restaurantid = restaurantid;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentMode = paymentMode;
	}



	



	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}


	

	public List<OrderItem> getOrderItems() {
	    return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
	    this.orderItems = orderItems;
	}





	public int getOrderId() {
		return orderId;
	}







	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}







	public int getUserId() {
		return userId;
	}







	public void setUserId(int userId) {
		this.userId = userId;
	}







	public int getRestaurantid() {
		return restaurantid;
	}







	public void setRestaurantid(int restaurantid) {
		this.restaurantid = restaurantid;
	}







	public LocalDateTime getOrderDate() {
		return orderDate;
	}







	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}







	public double getTotalAmount() {
		return totalAmount;
	}







	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}







	public String getStatus() {
		return status;
	}







	public void setStatus(String status) {
		this.status = status;
	}







	public String getPaymentMode() {
		return paymentMode;
	}







	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}







	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", userId=" + userId + ", restaurantid=" + restaurantid + ", orderDate="
				+ orderDate + ", totalAmount=" + totalAmount + ", status=" + status + ", paymentMode=" + paymentMode
				+ "]";
	}
	
	
	
	
}
