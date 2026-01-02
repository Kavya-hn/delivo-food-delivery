package com.delivo.dao;

import java.util.List;

import com.delivo.model.Order;

public interface OrderDAO {
	 List<Order> getAllOrders();

	    Order getOrder(int orderId);

	    void addOrder(Order order);

	    void updateOrder(Order order);

	    void deleteOrder(int orderId);

	    // Useful extras
	    List<Order> getOrdersByUser(int userId);

	  

		List<Order> getOrdersByRestaurantId(int restaurantId);

		int insertOrderReturnId(Order order);
}
