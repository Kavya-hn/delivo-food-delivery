package com.delivo.dao.impl;
import java.sql.Timestamp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.delivo.dao.OrderDAO;
import com.delivo.model.Order;
import com.delivo.util.DBConnection;

public class OrderDAOImpl implements OrderDAO{

	
	private static final String INSERT_ORDER_QUERY = "INSERT into orders(user_id,restaurant_id,order_date,total_amount,status,payment_mode) VALUES (?, ?, ?, ?, ?, ?)";
	
	private static final String UPDATE_ORDER_QUERY ="UPDATE orders SET user_id=?, restaurant_id=?, order_date=?, total_amount=?, status=?, payment_mode=? WHERE order_id=?";
	
	private static final String DELETE_ORDER_QUERY = "DELETE FROM orders WHERE order_id=?";
	
	private static final String GET_ORDER_BY_RESTAURANT_ID_QUERY =  "SELECT * FROM orders WHERE restaurant_id=?";
	
	private static final String GET_ORDER_BY_USER_QUERY =  "SELECT * FROM orders WHERE user_id=?";
	
	private static final String GET_ALL_ORDER_QUERY=  "SELECT * FROM orders";
	
	private static final String GET_ORDER_QUERY = "SELECT * FROM orders WHERE order_id = ?";

	@Override
	public void addOrder(Order order) {

		try(Connection connection=DBConnection.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(INSERT_ORDER_QUERY)){
					
			preparedStatement.setInt(1,order.getUserId());
			preparedStatement.setInt(2,order.getRestaurantid());
			preparedStatement.setTimestamp(3,Timestamp.valueOf(order.getOrderDate()));
			preparedStatement.setDouble(4,order.getTotalAmount());
			preparedStatement.setString(5,order.getStatus());
			preparedStatement.setString(6, order.getPaymentMode());
			
			preparedStatement.executeUpdate();
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	

	@Override
	public Order getOrder(int orderId) {

		Order order = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_QUERY)) {

        	preparedStatement.setInt(1, orderId);
            ResultSet resultset = preparedStatement.executeQuery();

            if (resultset.next()) {
                order = mapOrder(resultset);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
	}

	

	@Override
	public void updateOrder(Order order) {

		 try (Connection connection = DBConnection.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_QUERY)) {

	            preparedStatement.setInt(1, order.getUserId());
	            preparedStatement.setInt(2, order.getRestaurantid());
	            preparedStatement.setTimestamp(3, Timestamp.valueOf(order.getOrderDate()));
	            preparedStatement.setDouble(4, order.getTotalAmount());
	            preparedStatement.setString(5, order.getStatus());
	            preparedStatement.setString(6, order.getPaymentMode());
	            preparedStatement.setInt(7, order.getOrderId());
             
	            preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
		
		
		
	}

	@Override
	public void deleteOrder(int orderId) {

	       try (Connection connection = DBConnection.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_QUERY);) {

	            preparedStatement.setInt(1, orderId);
	            preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		
	@Override
	public List<Order> getAllOrders() {
		 List<Order> orderesultset = new ArrayList<>();
	       

	        try (Connection connection = DBConnection.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDER_QUERY);
	             ResultSet resultset = preparedStatement.executeQuery()) {

	            while (resultset.next()) {
	                orderesultset.add(mapOrder(resultset));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return orderesultset;
		

	}
		
		
		
		
	

	@Override
	public List<Order> getOrdersByUser(int userId) {


        List<Order> list = new ArrayList<>();
    

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( GET_ORDER_BY_USER_QUERY)) {

        	preparedStatement.setInt(1, userId);
            ResultSet resultset = preparedStatement.executeQuery();

            while (resultset.next()) {
                list.add(mapOrder(resultset));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
		

	}

	@Override
	public List<Order> getOrdersByRestaurantId(int restaurantId) {

		List<Order> list = new ArrayList<>();
       

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_RESTAURANT_ID_QUERY)) {

            preparedStatement.setInt(1, restaurantId);
            ResultSet resultset = preparedStatement.executeQuery();

            while (resultset.next()) {
                list.add(mapOrder(resultset));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
		
	}

	private Order mapOrder(ResultSet resultset) throws SQLException {

        Order order = new Order();

        order.setOrderId(resultset.getInt("order_id"));
        order.setUserId(resultset.getInt("user_id"));
        order.setRestaurantid(resultset.getInt("restaurant_id"));
        order.setOrderDate(resultset.getTimestamp("order_date").toLocalDateTime());
        order.setTotalAmount(resultset.getInt("total_amount"));
        order.setStatus(resultset.getString("status"));
        order.setPaymentMode(resultset.getString("payment_mode"));

        return order;
    }
	
	
	@Override
	public int insertOrderReturnId(Order order) {

	    int generatedId = -1;

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(
	                 INSERT_ORDER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {

	        preparedStatement.setInt(1, order.getUserId());
	        preparedStatement.setInt(2, order.getRestaurantid());
	        preparedStatement.setTimestamp(3, Timestamp.valueOf(order.getOrderDate()));
	        preparedStatement.setDouble(4, order.getTotalAmount());
	        preparedStatement.setString(5, order.getStatus());
	        preparedStatement.setString(6, order.getPaymentMode());

	        preparedStatement.executeUpdate();

	        // Retrieve generated key (order_id)
	        try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
	            if (keys.next()) {
	                generatedId = keys.getInt(1);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return generatedId;
	}

	
	
	
}
