package com.delivo.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.delivo.dao.OrderItemDAO;
import com.delivo.model.OrderItem;
import com.delivo.util.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO {

    private static final String INSERT_ORDER_ITEM_QUERY = "INSERT INTO order_items (order_item, order_id, menu_id, quantity, total_price) VALUES (?, ?, ?, ?, ?)";

    private static final String SELECT_ORDER_ITEMS_BY_ORDER_QUERY =  "SELECT * FROM order_items WHERE order_id = ?";

    private static final String SELECT_ORDER_ITEM_QUERY = "SELECT * FROM order_items WHERE order_item_id = ?";

    private static final String UPDATE_ORDER_ITEM_QUERY =   "UPDATE order_items SET order_item=?, order_id=?, menu_id=?, quantity=?, total_price=? WHERE order_item_id=?";

    private static final String DELETE_ORDER_ITEM_QUERY =  "DELETE FROM order_items WHERE order_item_id=?";

    private static final String DELETE_ITEMS_BY_ORDER_QUERY =  "DELETE FROM order_items WHERE order_id=?";


    @Override
    public List<OrderItem> getItemsByOrder(int orderId) {

        List<OrderItem> itemsList = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_ITEMS_BY_ORDER_QUERY)) {

            preparedStatement.setInt(1, orderId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    itemsList.add(mapToOrderItem(resultSet));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itemsList;
    }

    @Override
    public OrderItem getOrderItem(int orderItemId) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_ITEM_QUERY)) {

            preparedStatement.setInt(1, orderItemId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToOrderItem(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void addOrderItem(OrderItem item) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_ITEM_QUERY)) {

            preparedStatement.setString(1, item.getOrderItem());
            preparedStatement.setInt(2, item.getOrderId());
            preparedStatement.setInt(3, item.getMenuId());
            preparedStatement.setInt(4, item.getQuantity());
            preparedStatement.setDouble(5, item.getTotalPrice());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    @Override
    public void updateOrderItem(OrderItem item) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_ITEM_QUERY)) {

            preparedStatement.setString(1, item.getOrderItem());
            preparedStatement.setInt(2, item.getOrderId());
            preparedStatement.setInt(3, item.getMenuId());
            preparedStatement.setInt(4, item.getQuantity());
            preparedStatement.setDouble(5, item.getTotalPrice());
            preparedStatement.setInt(6, item.getOrderItemId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderItem(int orderItemId) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_ITEM_QUERY)) {

            preparedStatement.setInt(1, orderItemId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    
    @Override
    public void deleteItemsByOrder(int orderId) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ITEMS_BY_ORDER_QUERY)) {

            preparedStatement.setInt(1, orderId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    
    //helper method
    private OrderItem mapToOrderItem(ResultSet rs) throws SQLException {

        OrderItem item = new OrderItem();

        item.setOrderItemId(rs.getInt("order_item_id"));
        item.setOrderItem(rs.getString("order_item"));
        item.setOrderId(rs.getInt("order_id"));
        item.setMenuId(rs.getInt("menu_id"));
        item.setQuantity(rs.getInt("quantity"));
        item.setTotalPrice(rs.getInt("total_price"));

        return item;
    }
}
