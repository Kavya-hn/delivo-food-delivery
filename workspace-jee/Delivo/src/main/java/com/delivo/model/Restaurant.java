package com.delivo.model;

public class Restaurant {

	private int restaurantId;
	private String name;
	private String address;
	private long phone;
	private double rating;
	private String cuisineType;
	private boolean isActive;
	private int eta;
	private int adminUserId;
	private String imagePath;
	
	
	
	
	public Restaurant(int restaurantId, String name, String address, long phone, double rating, String cusineType,
			boolean isActive, int eta, int adminUserId, String imagePath) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.rating = rating;
		this.cuisineType = cusineType;
		this.isActive = isActive;
		this.eta = eta;
		this.adminUserId = adminUserId;
		this.imagePath = imagePath;
	}





	public Restaurant() {
		super();
		// TODO Auto-generated constructor stub
	}



	


	public int getRestaurantId() {
		return restaurantId;
	}





	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public String getAddress() {
		return address;
	}





	public void setAddress(String address) {
		this.address = address;
	}





	public long getPhone() {
		return phone;
	}





	public void setPhone(long phone) {
		this.phone = phone;
	}





	public double getRating() {
		return rating;
	}





	public void setRating(double rating) {
		this.rating = rating;
	}





	public String getCuisineType() {
		return cuisineType;
	}





	public void setCuisineType(String cusineType) {
		this.cuisineType = cusineType;
	}





	public boolean isActive() {
		return isActive;
	}





	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}





	public int getEta() {
		return eta;
	}





	public void setEta(int eta) {
		this.eta = eta;
	}





	public int getAdminUserId() {
		return adminUserId;
	}





	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
	}





	public String getImagePath() {
		return imagePath;
	}





	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}





	@Override
	public String toString() {
		return "Restaurant [RestaurantId=" + restaurantId + ", name=" + name + ", address=" + address + ", phone="
				+ phone + ", rating=" + rating + ", cusineType=" + cuisineType + ", isActive=" + isActive + ", eta="
				+ eta + ", adminUserId=" + adminUserId + ", imagePath=" + imagePath + "]";
	}
	
	
	
	
}
