<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.delivo.model.Menu"%>

<!DOCTYPE html>
<html>
<head>
<title>Menu - Delivo</title>
<link rel="stylesheet"
	href="<%= request.getContextPath() %>/static/css/menu.css">
<link rel="stylesheet"
	href="<%= request.getContextPath() %>/static/css/common.css">

</head>
<body>

	<header>
		<div class="navbar">

			<!-- Logo -->
			<div class="logo-box">
				<img src="<%= request.getContextPath() %>/static/images/logo/delivologo.png">
				<h1>Delivo</h1>
			</div>


			<!-- Links -->
			<div class="nav-links">
				<a href="<%= request.getContextPath() %>/restaurants">Home</a> <a
					href="<%= request.getContextPath() %>/cart">Cart</a> <a
					href="<%= request.getContextPath() %>/orderhistory">Orders</a> <a
					href="<%= request.getContextPath() %>/profile">Profile</a> <a
					href="<%= request.getContextPath() %>/signin">Login</a>
			</div>
		</div>
	</header>


	<section class="hero">
		<h2>Our Menu</h2>
	</section>

	<section class="menu-list">

		<%
    List<Menu> menuList = (List<Menu>) request.getAttribute("menuList");
    int restaurantId = (Integer) request.getAttribute("restaurantId");

    if (menuList == null || menuList.isEmpty()) {
%>

		<p>No menu items available.</p>

		<%
    } else {
        for (Menu m : menuList) {
%>

		<div class="menu-card">
			<%
    // Get the path from the DB
    String imagePath = m.getImagePath().trim(); 
    
    // Check if the path starts with a slash and remove it if necessary
    if (imagePath.startsWith("/")) {
        imagePath = imagePath.substring(1); 
    }
    
    // Check if the path starts with the context root and remove it if necessary
    if (imagePath.startsWith("Delivo/")) {
        imagePath = imagePath.substring("Delivo/".length());
    }

    // Since the database provides the full path, use it directly after the context path
%>
			<h3><%= m.getItemName() %></h3>
			<p><%= m.getDescription() %></p>

			<p>
				<strong>₹<%= m.getPrice() %></strong>
			</p>
			<p>
				Rating: ⭐<%= m.getRating() %></p>

			<img src="<%= m.getImagePath().trim() %>"
				alt="<%= m.getItemName() %>" class="menu-img">




			<form action="<%= request.getContextPath() %>/cart" method="post">
				<input type="hidden" name="action" value="add"> <input
					type="hidden" name="menuId" value="<%= m.getMenuId() %>">
				<button type="submit" class="add-btn">Add </button>
			</form>

		</div>

		<%
        }
    }
%>

	</section>

	<footer>© 2025 Delivo</footer>
</body>
</html>
