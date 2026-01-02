package com.delivo.dao;

import java.util.List;

import com.delivo.model.Menu;

public interface MenuDAO {



    Menu getMenuItem(int menuId);

    void updateMenuItem(Menu menu);

    void deleteMenuItem(int menuId);
    
    void addMenuItem(Menu menu);
    
    List<Menu> getMenuByRestaurant(int restaurantId);
    
    
    // Optional: search
    List<Menu> searchMenuItems(String keyword);

	
}