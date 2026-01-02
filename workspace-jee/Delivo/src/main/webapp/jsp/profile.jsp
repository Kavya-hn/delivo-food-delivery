<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.delivo.model.User"%>

<%
    User user = (User) request.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/signin");
        return;
    }
    String updated = request.getParameter("success");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Profile - Delivo</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/common.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/profile.css">
</head>
<body>

<header>
    <div class="navbar">
        <div class="logo-box">
            <img src="<%= request.getContextPath() %>/static/images/logo/delivologo.png">
            <h1>Delivo</h1>
        </div>
        <div class="nav-links">
            <a href="<%= request.getContextPath() %>/restaurants">Home</a>
            <a href="<%= request.getContextPath() %>/cart">Cart</a>
            <a href="<%= request.getContextPath() %>/orderhistory">Orders</a>
            <a href="<%= request.getContextPath() %>/profile" class="active">Profile</a>
            <a href="<%= request.getContextPath() %>/logout">Logout</a>
        </div>
    </div>
</header>

<main class="profile-container">
    <aside class="profile-sidebar">
        <ul class="side-menu">
            <li class="active"><a href="#">Edit Profile</a></li>
            <li><a href="<%= request.getContextPath() %>/orderhistory">My Orders</a></li>
            <li><a href="#">Manage Addresses</a></li>
            <li><a href="#">Super Coins</a></li>
        </ul>
    </aside>

    <section class="profile-content">
        <div class="content-header">
            <h2>Edit Profile</h2>
            <p>Update your personal details and delivery address.</p>
        </div>

        <% if ("1".equals(updated)) { %>
            <div class="success-banner">
                <span class="icon">✔</span> Profile Updated Successfully
            </div>
        <% } %>

        <form action="<%= request.getContextPath() %>/profile" method="post" class="swiggy-form">
            <div class="input-group">
                <input type="text" name="name" value="<%= user.getName() %>" required>
                <label>Full Name</label>
            </div>

            <div class="input-group">
                <input type="email" name="email" value="<%= user.getEmail() %>" required>
                <label>Email Address</label>
            </div>

            <div class="input-group">
                <input type="text" name="phone" value="<%= user.getPhone() %>" required>
                <label>Phone Number</label>
            </div>

            <div class="input-group">
                <textarea name="address" required><%= user.getAddress() %></textarea>
                <label>Delivery Address</label>
            </div>

            <button type="submit" class="save-btn">Save Changes</button>
        </form>
    </section>
</main>

<footer>© 2025 Delivo</footer>
</body>
</html>