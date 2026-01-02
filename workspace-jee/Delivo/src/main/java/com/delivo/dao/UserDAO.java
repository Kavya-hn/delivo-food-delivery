package com.delivo.dao;

import java.util.List;

import com.delivo.model.User;

public interface UserDAO {

	
	List<User> getAllUser();
	
	User getUser(int id);
	
	
	
	void addUser(User user);
	
	void deleteUser(int id);

	void updateUser(User user);

	User getUserByUsernameAndPassword(String username, String password);


	//User validateUser(String email,String password);
	
	
	
}
