<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.delivo.model.Restaurant"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Delivo – Restaurants</title>

<link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/common.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/restaurant.css">
</head>
<body>

<header>
    <div class="navbar">
        <div class="logo-box">
            <img src="<%= request.getContextPath() %>/static/images/logo/delivologo.png">
            <h1>Delivo</h1>
        </div>
        <div class="nav-links">
            <a href="<%= request.getContextPath() %>/restaurants" class="active">Home</a>
            <a href="<%= request.getContextPath() %>/cart">Cart</a>
            <a href="<%= request.getContextPath() %>/orderhistory">Orders</a>
            <a href="<%= request.getContextPath() %>/profile">Profile</a>
            <a href="<%= request.getContextPath() %>/jsp/signin.jsp" class="login-btn">Login</a>
        </div>
    </div>
</header>

<div class="banner-video-container">
    <div class="banner-video">
        <video autoplay muted loop playsinline>
            <source src="<%= request.getContextPath() %>/static/videos/food.mp4" type="video/mp4">
        </video>
        
        <div class="banner-overlay">
            <h1>Order Your Food</h1>
            <section class="video-search-bar">
                <input type="text" placeholder="Search for restaurants or cuisines...">
                <button type="button" class="search-submit-btn">Search</button>
            </section>
        </div>
    </div>
</div>

<div class="main-content">
    <section class="section-title">
        <h2>Popular Restaurants Near You</h2>
        <div class="title-underline"></div>
    </section>

    <section class="restaurant-list">
        <%
        List<Restaurant> restaurants = (List<Restaurant>) request.getAttribute("restaurants");
        if (restaurants != null && !restaurants.isEmpty()) {
            for (Restaurant r : restaurants) {
                String imagePath = r.getImagePath().trim();
        %>
        <div class="restaurant-card">
            <a href="<%= request.getContextPath() %>/MenuServlet?restaurantId=<%= r.getRestaurantId() %>" class="card-link">
                <div class="img-container">
                    <img src="<%= imagePath %>" alt="<%= r.getName() %>" class="restaurant-img">
                </div>
                
                <div class="res-details">
                    <h3><%= r.getName() %></h3>
                    <p class="cuisine"><%= r.getCuisineType() %></p>
                    
                    <div class="res-meta">
                        <span class="rating-badge">
                            <i class="star-icon">⭐</i> <%= r.getRating() %>
                        </span>
                        <span class="dot">•</span>
                        <span class="eta"><%= r.getEta() %> MINS</span>
                    </div>
                </div>
            </a>
        </div>
        <%
            }
        } else {
        %>
            <div class="empty-state">
                <p>No restaurants found. Please try a different search.</p>
            </div>
        <% } %>
    </section>
</div>


<footer> © 2025 Delivo | All Rights Reserved </footer>
</body>
</html>