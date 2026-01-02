package com.delivo.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.delivo.dao.MenuDAO;
import com.delivo.model.Menu;
import com.delivo.util.DBConnection;

public class MenuDAOImpl implements MenuDAO {

    private static final String INSERT_MENU_QUERY ="INSERT INTO menu (restaurant_id, item_name, description, price, rating, is_available, image_path) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_MENU_BY_RESTAURANT_QUERY = "SELECT * FROM menu WHERE restaurant_id = ? AND is_available = TRUE";

    private static final String SELECT_MENU_ITEM_QUERY = "SELECT * FROM menu WHERE menu_id = ?";

    private static final String UPDATE_MENU_ITEM_QUERY ="UPDATE menu SET item_name = ?, description = ?, price = ?, rating = ?, is_available = ?, image_path = ? WHERE menu_id = ?";

    private static final String DELETE_MENU_ITEM_QUERY ="DELETE FROM menu WHERE menu_id = ?";

    private static final String SEARCH_MENU_QUERY ="SELECT * FROM menu WHERE item_name LIKE ? OR description LIKE ?";

    private static final String BASE_PATH = "/Delivo/";  
    
    @Override
    public List<Menu> getMenuByRestaurant(int restaurantId) {

        List<Menu> menuList = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_MENU_BY_RESTAURANT_QUERY)) {

            preparedStatement.setInt(1, restaurantId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    menuList.add(mapToMenu(resultSet));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menuList;
    }


    
    @Override
    public Menu getMenuItem(int menuId) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_MENU_ITEM_QUERY)) {

            preparedStatement.setInt(1, menuId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return mapToMenu(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    
    
    
    @Override
    public void addMenuItem(Menu menu) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_MENU_QUERY)) {

            preparedStatement.setInt(1, menu.getRestaurantId());
            preparedStatement.setString(2, menu.getItemName());
            preparedStatement.setString(3, menu.getDescription());
            preparedStatement.setDouble(4, menu.getPrice());
            preparedStatement.setDouble(5, menu.getRating());
            preparedStatement.setBoolean(6, menu.isAvailable());
            preparedStatement.setString(7, menu.getImagePath());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    
    
    @Override
    public void updateMenuItem(Menu menu) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(UPDATE_MENU_ITEM_QUERY)) {

            preparedStatement.setString(1, menu.getItemName());
            preparedStatement.setString(2, menu.getDescription());
            preparedStatement.setDouble(3, menu.getPrice());
            preparedStatement.setDouble(4, menu.getRating());
            preparedStatement.setBoolean(5, menu.isAvailable());
            preparedStatement.setString(6, menu.getImagePath());
            preparedStatement.setInt(7, menu.getMenuId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    
    
    @Override
    public void deleteMenuItem(int menuId) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(DELETE_MENU_ITEM_QUERY)) {

            preparedStatement.setInt(1, menuId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    
    
    
    @Override
    public List<Menu> searchMenuItems(String keyword) {

        List<Menu> menuList = new ArrayList<>();
        String searchKeyword = "%" + keyword + "%";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SEARCH_MENU_QUERY)) {

            preparedStatement.setString(1, searchKeyword);
            preparedStatement.setString(2, searchKeyword);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    menuList.add(mapToMenu(resultSet));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menuList;
    }


    
    private Menu mapToMenu(ResultSet rs) throws SQLException {

        Menu menu = new Menu();

        menu.setMenuId(rs.getInt("menu_id"));
        menu.setRestaurantId(rs.getInt("restaurant_id"));
        menu.setItemName(rs.getString("item_name"));
        menu.setDescription(rs.getString("description"));
        menu.setPrice(rs.getDouble("price"));
        menu.setRating(rs.getDouble("rating"));
        menu.setAvailable(rs.getBoolean("is_available"));
        String img = rs.getString("image_path");  
        menu.setImagePath(BASE_PATH + img);

        return menu;
    }
}
