package com.delivo.dao.impl;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.delivo.dao.UserDAO;
import com.delivo.model.Role;
import com.delivo.model.User;
import com.delivo.util.DBConnection;

public class UserDAOImpl implements UserDAO {

	private static final String INSERT_QUERY = "INSERT INTO users (name, username, password, email, phone, address, role, created_at, last_login_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_USER_QUERY = "SELECT id, name, username, password, email, phone, address, role, created_at, last_login_at FROM users WHERE id = ?";
	private static final String UPDATE_USER_QUERY = "UPDATE users SET name = ?, username = ?, password = ?, email = ?, phone = ?, address = ?, role = ? WHERE id = ?";
	private static final String DELETE_USER_QUERY= "DELETE FROM users WHERE id = ?";
	private static final String GET_ALL_USER_QUERY= "SELECT id, name, username, password, email, phone, address, role, created_at, last_login_at FROM users";
	private static final String  GET_USER_BY_USERNAME_AND_PASSWORD= "SELECT * FROM users WHERE username = ? AND password = ?";
	 @Override
	    public void addUser(User user) {
	       
	        try (Connection connection = DBConnection.getConnection();
	             PreparedStatement  preparedStatement =connection .prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

	             preparedStatement.setString(1, user.getName());
	             preparedStatement.setString(2, user.getUserName());
	             preparedStatement.setString(3, user.getPassword());
	             preparedStatement.setString(4, user.getEmail());
	             preparedStatement.setLong(5, user.getPhone());
	             preparedStatement.setString(6, user.getAddress());
	             preparedStatement.setString(7, user.getRole() != null ? user.getRole().name() : null);
	             preparedStatement.setTimestamp(8, user.getCreatedDate() != null ? Timestamp.valueOf(user.getCreatedDate()) : Timestamp.valueOf(LocalDateTime.now()));
	             preparedStatement.setTimestamp(9, user.getLastLoginDate() != null ? Timestamp.valueOf(user.getLastLoginDate()) : null);

	             preparedStatement.executeUpdate();

	            // if you want to get generated id back and set it in the object
	            try (ResultSet keys =  preparedStatement.getGeneratedKeys()) {
	                if (keys.next()) {
	                    user.setUserId(keys.getInt(1));
	                }
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
	 
	 
	
  






	@Override
    public User getUser(int id) {
        User user = null;
        
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement  preparedStatement = connection .prepareStatement(GET_USER_QUERY )) {

        	 preparedStatement.setInt(1, id);
            try (ResultSet resultSet =  preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = extractUser(resultSet);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    
    @Override
    public void updateUser(User user) {
      
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection .prepareStatement(UPDATE_USER_QUERY)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getPassword()); // consider hashing before storing
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setLong(5, user.getPhone());
            preparedStatement.setString(6, user.getAddress());
            preparedStatement.setString(7, user.getRole() != null ? user.getRole().name() : null);
            preparedStatement.setInt(8, user.getUserId());

            preparedStatement .executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   

    @Override
    public void deleteUser(int id) {
       

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement  = connection .prepareStatement(DELETE_USER_QUERY)) {

        	preparedStatement .setInt(1, id);
        	preparedStatement .executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
    
    @Override
    public List<User> getAllUser() {
        List<User> usersList = new ArrayList<>();
       
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement  = connection .prepareStatement(GET_ALL_USER_QUERY);
             ResultSet resultset = preparedStatement .executeQuery()) {

            while (resultset.next()) {
            	usersList.add(extractUser(resultset));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usersList;
    }

    
    // Helper to map ResultSet -> User
    private User extractUser(ResultSet resultset) throws SQLException {
        User user = new User();

        user.setUserId(resultset.getInt("id"));
        user.setName(resultset.getString("name"));
        user.setUserName(resultset.getString("username"));
        user.setPassword(resultset.getString("password"));
        user.setEmail(resultset.getString("email"));
        user.setPhone(resultset.getLong("phone"));
        user.setAddress(resultset.getString("address"));

        String roleStr = resultset.getString("role");
        if (roleStr != null) {
            try {
                user.setRole(Role.valueOf(roleStr));
            } catch (IllegalArgumentException ex) {
                // unknown role string in DB; you may want to log this
            	user.setRole(null);
            }
        } else {
        	user.setRole(null);
        }

        Timestamp createdTs = resultset.getTimestamp("created_at");
        if (createdTs != null) {
        	user.setCreatedDate(createdTs.toLocalDateTime());
        }

        Timestamp lastLoginTs = resultset.getTimestamp("last_login_at");
        if (lastLoginTs != null) {
        	user.setLastLoginDate(lastLoginTs.toLocalDateTime());
        }

        return user;
    }
    
    

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {


        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_USERNAME_AND_PASSWORD)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return extractUser(resultSet); // Reuse existing method
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // No user found
    }

    
    
    
    
    
    
    
    
}
