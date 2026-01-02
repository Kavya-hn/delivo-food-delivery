package com.delivo.dao;

import java.util.List;

import com.delivo.model.Restaurant;

public interface RestaurantDAO {

   

    Restaurant getRestaurant(int restaurantId);

    void insertRestaurant(Restaurant restaurant);

    void updateRestaurant(Restaurant restaurant);

    void deleteRestaurant(int restaurantId);
    
    List<Restaurant> getAllRestaurants();
    

    // Helpful features
    List<Restaurant> getActiveRestaurants();

    List<Restaurant> getRestaurantsByCuisine(String cuisineType);
}
