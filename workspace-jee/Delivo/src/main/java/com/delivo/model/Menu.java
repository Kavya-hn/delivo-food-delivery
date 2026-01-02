package com.delivo.model;

public class Menu {
    
	private int menuId;
	private int restaurantId;
	private String itemName;
	private String description;
	private double price;
	private double rating;
	private boolean isAvailable;
	private String imagePath;
	
	
	
	
	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Menu(int menuId, int restaurantId, String itemName, String description, double price, double rating,
			boolean isAvailable, String imagePath) {
		super();
		this.menuId = menuId;
		this.restaurantId = restaurantId;
		this.itemName = itemName;
		this.description = description;
		this.price = price;
		this.rating = rating;
		this.isAvailable = isAvailable;
		this.imagePath = imagePath;
	}


	


	public int getMenuId() {
		return menuId;
	}




	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}




	public int getRestaurantId() {
		return restaurantId;
	}




	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}




	public String getItemName() {
		return itemName;
	}




	public void setItemName(String itemName) {
		this.itemName = itemName;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public double getPrice() {
		return price;
	}




	public void setPrice(double price) {
		this.price = price;
	}




	public double getRating() {
		return rating;
	}




	public void setRating(double rating) {
		this.rating = rating;
	}




	public boolean isAvailable() {
		return isAvailable;
	}




	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}




	public String getImagePath() {
		return imagePath;
	}




	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}




	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", restaurantId=" + restaurantId + ", itemName=" + itemName + ", description="
				+ description + ", price=" + price + ", rating=" + rating + ", isAvailable=" + isAvailable
				+ ", imagePath=" + imagePath + "]";
	}

	
	
	
	
	
}
