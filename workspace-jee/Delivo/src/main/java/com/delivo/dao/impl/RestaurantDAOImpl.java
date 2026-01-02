package com.delivo.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.delivo.dao.RestaurantDAO;
import com.delivo.model.Restaurant;
import com.delivo.util.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO {

    private static final String INSERT_RESTAURANT_QUERY ="INSERT INTO restaurants (name, address, phone, rating, cuisine_type, is_active, eta, admin_user_id, image_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_ALL_RESTAURANTS_QUERY =  "SELECT * FROM restaurants";

    private static final String SELECT_RESTAURANT_BY_ID_QUERY =  "SELECT * FROM restaurants WHERE restaurant_id = ?";

    private static final String UPDATE_RESTAURANT_QUERY ="UPDATE restaurants SET name=?, address=?, phone=?, rating=?, cuisine_type=?, is_active=?, eta=?, admin_user_id=?, image_path=? WHERE restaurant_id=?";

    private static final String DELETE_RESTAURANT_QUERY ="DELETE FROM restaurants WHERE restaurant_id=?";

    private static final String SELECT_ACTIVE_RESTAURANTS_QUERY = "SELECT * FROM restaurants WHERE is_active = 1";

    private static final String SELECT_RESTAURANTS_BY_CUISINE_QUERY = "SELECT * FROM restaurants WHERE cuisine_type = ?";

    private static final String BASE_PATH = "/Delivo/";

    @Override
    public List<Restaurant> getAllRestaurants() {

        List<Restaurant> restaurantList = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_RESTAURANTS_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                restaurantList.add(mapResultSetToRestaurant(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurantList;
    }

    @Override
    public Restaurant getRestaurant(int restaurantId) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RESTAURANT_BY_ID_QUERY)) {

            preparedStatement.setInt(1, restaurantId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToRestaurant(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // restaurant not found
    }

    @Override
    public void insertRestaurant(Restaurant restaurant) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RESTAURANT_QUERY)) {

            preparedStatement.setString(1, restaurant.getName());
            preparedStatement.setString(2, restaurant.getAddress());
            preparedStatement.setLong(3, restaurant.getPhone());
            preparedStatement.setDouble(4, restaurant.getRating());
            preparedStatement.setString(5, restaurant.getCuisineType());
            preparedStatement.setBoolean(6, restaurant.isActive());
            preparedStatement.setInt(7, restaurant.getEta());
            preparedStatement.setInt(8, restaurant.getAdminUserId());
            preparedStatement.setString(9, restaurant.getImagePath());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESTAURANT_QUERY)) {

            preparedStatement.setString(1, restaurant.getName());
            preparedStatement.setString(2, restaurant.getAddress());
            preparedStatement.setLong(3, restaurant.getPhone());
            preparedStatement.setDouble(4, restaurant.getRating());
            preparedStatement.setString(5, restaurant.getCuisineType());
            preparedStatement.setBoolean(6, restaurant.isActive());
            preparedStatement.setInt(7, restaurant.getEta());
            preparedStatement.setInt(8, restaurant.getAdminUserId());
            preparedStatement.setString(9, restaurant.getImagePath());

            preparedStatement.setInt(10, restaurant.getRestaurantId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRestaurant(int restaurantId) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESTAURANT_QUERY)) {

            preparedStatement.setInt(1, restaurantId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Restaurant> getActiveRestaurants() {

        List<Restaurant> restaurantList = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACTIVE_RESTAURANTS_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                restaurantList.add(mapResultSetToRestaurant(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurantList;
    }

    @Override
    public List<Restaurant> getRestaurantsByCuisine(String cuisineType) {

        List<Restaurant> restaurantList = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RESTAURANTS_BY_CUISINE_QUERY)) {

            preparedStatement.setString(1, cuisineType);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    restaurantList.add(mapResultSetToRestaurant(resultSet));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurantList;
    }
    
    
    
    
    
    //Helper method
    private Restaurant mapResultSetToRestaurant(ResultSet rs) throws SQLException {

        Restaurant restaurant = new Restaurant();

        restaurant.setRestaurantId(rs.getInt("restaurant_id"));
        restaurant.setName(rs.getString("name"));
        restaurant.setAddress(rs.getString("address"));
        restaurant.setPhone(rs.getLong("phone"));
        restaurant.setRating(rs.getDouble("rating"));
        restaurant.setCuisineType(rs.getString("cuisine_type"));
        restaurant.setActive(rs.getBoolean("is_active"));
        restaurant.setEta(rs.getInt("eta"));
        restaurant.setAdminUserId(rs.getInt("admin_user_id"));
        
        String img = rs.getString("image_path");
        restaurant.setImagePath(BASE_PATH + img);
        
        return restaurant;
    }
}
