<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.delivo.model.Order"%>
<%@ page import="com.delivo.model.OrderItem"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Orders - Delivo</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/common.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/orderhistory.css">
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
            <a href="<%= request.getContextPath() %>/orderhistory" class="active">Orders</a>
            <a href="<%= request.getContextPath() %>/profile">Profile</a>
        </div>
    </div>
</header>

<main class="history-container">
    <div class="history-header">
        <h2>Past Orders</h2>
        <p>Manage and track your recent meals</p>
    </div>

    <section class="orders-list">
    <%
        List<Order> orders = (List<Order>) request.getAttribute("orders");

        if (orders == null || orders.isEmpty()) {
    %>
        <div class="empty-state">
            <img src="<%= request.getContextPath() %>/static/images/empty-orders.png" alt="No orders">
            <h3>No Orders Yet</h3>
            <p>Hungry? Discover the best foods around you!</p>
            <a href="<%= request.getContextPath() %>/restaurants" class="browse-btn">Browse Restaurants</a>
        </div>
    <%
        } else {
            for (Order order : orders) {
                String status = order.getStatus();
                // Create a CSS-friendly class name (e.g., "Delivered" -> "delivered")
                String statusClass = status.toLowerCase().replace(" ", "-");
    %>
        <div class="order-card">
            <div class="card-header">
                <div class="order-meta">
                    <span class="order-id">Order #<%= order.getOrderId() %></span>
                    <span class="order-date"><%= order.getOrderDate() %></span>
                </div>
                <div class="status-badge badge-<%= statusClass %>">
                    <%= status %>
                </div>
            </div>

            <div class="card-body">
                <div class="items-preview">
                    <h4>Items Ordered:</h4>
                    <ul>
                        <%
                            List<OrderItem> items = order.getOrderItems();
                            if (items != null) {
                                for (OrderItem item : items) {
                        %>
                            <li>
                                <span class="item-text"><%= item.getOrderItem() %> × <%= item.getQuantity() %></span>
                                <span class="item-price">₹<%= item.getTotalPrice() %></span>
                            </li>
                        <%
                                }
                            }
                        %>
                    </ul>
                </div>
                
                <div class="order-footer">
                    <div class="payment-info">
                        <small>Paid via</small>
                        <p><%= order.getPaymentMode() %></p>
                    </div>
                    <div class="total-info">
                        <small>Total Bill</small>
                        <p class="grand-total">₹<%= order.getTotalAmount() %></p>
                    </div>
                </div>
            </div>
            
            <div class="card-actions">
                <a href="<%= request.getContextPath() %>/restaurants" class="reorder-btn">Order Something Else</a>
                <button class="view-details">Get Help</button>
            </div>
        </div>
    <%
            }
        }
    %>
    </section>
</main>

<footer>© 2025 Delivo - Crafted with ❤️ for Foodies</footer>
</body>
</html>