package com.delivo.dao;

import java.util.List;

import com.delivo.model.OrderItem;

public interface OrderItemDAO {

    List<OrderItem> getItemsByOrder(int orderId);

    OrderItem getOrderItem(int orderItemId);

    void updateOrderItem(OrderItem item);

    void deleteOrderItem(int orderItemId);

    void deleteItemsByOrder(int orderId);  // when cancelling order

	//void addOrderItem(OrderItem item);

	

	void addOrderItem(OrderItem item);
}
