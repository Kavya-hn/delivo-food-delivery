package com.delivo.model;

import java.time.LocalDateTime;




public class User {

	private int userId;
	private String name;
	private String userName;
	private String password;
	private String email;
	private long phone;
	private String address;
	private Role role;
	private LocalDateTime createdDate;
	private LocalDateTime lastLoginDate;
	
	
	public User() {
		super();
		
	}


	
	public User(int userId, String name, String userName, String password, String email, long phone, String address,
			Role role, LocalDateTime createdDate, LocalDateTime lastLoginDate) {
		super();
		this.userId = userId;
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.role = role;
		this.createdDate = createdDate;
		this.lastLoginDate = lastLoginDate;
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public long getPhone() {
		return phone;
	}



	public void setPhone(long phone) {
		this.phone = phone;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public Enum<?> getRole() {
		return role;
	}
	
	

	public void setRole(Role role) {
		this.role = role;
	}



	public LocalDateTime getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}



	public LocalDateTime getLastLoginDate() {
		return lastLoginDate;
	}



	public void setLastLoginDate(LocalDateTime lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}



	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", userName=" + userName + ", password=" + password
				+ ", email=" + email + ", phone=" + phone + ", address=" + address + ", role=" + role + ", createdDate="
				+ createdDate + ", lastLoginDate=" + lastLoginDate + "]";
	}



	public String getCreatedAt() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
