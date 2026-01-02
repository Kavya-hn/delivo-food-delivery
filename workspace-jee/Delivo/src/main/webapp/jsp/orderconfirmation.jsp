<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.delivo.model.Order"%>
<%@ page import="com.delivo.model.OrderItem"%>
<%@ page import="com.delivo.model.Restaurant"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Success - Delivo</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/common.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/orderconfirmation.css">
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
            <a href="<%= request.getContextPath() %>/profile">Profile</a>
            <a href="<%= request.getContextPath() %>/signin">Login</a>
        </div>
    </div>
</header>

<%
    Order order = (Order) request.getAttribute("order");
    Restaurant rest = (Restaurant) request.getAttribute("restaurant");
    List<OrderItem> items = (List<OrderItem>) request.getAttribute("items");

    if (order == null || rest == null) {
%>
    <div class="error-container">
        <p>Oops! We couldn't find that order. <a href="<%= request.getContextPath() %>/restaurants">Browse Restaurants</a></p>
    </div>
<%
        return;
    }
%>

<main class="confirmation-wrapper">
    <section class="status-header-card">
        <div class="success-animation">
            <div class="check-icon">✓</div>
        </div>
        <h2>Order Placed Successfully!</h2>
        <p class="order-id-label">Order ID: <span>#<%= order.getOrderId() %></span></p>
        
        <div class="eta-pill">
            <span class="icon">🕒</span>
            <p>Arriving in <strong><%= rest.getEta() %> mins</strong></p>
        </div>
    </section>

    <div class="order-info-grid">
        
        <div class="info-column">
            <div class="info-card">
                <p class="section-label">Restaurant Details</p>
                <h3 class="res-name"><%= rest.getName() %></h3>
                <span class="status-badge"><%= order.getStatus() %></span>
            </div>

            <div class="info-card">
                <p class="section-label">Payment & Delivery</p>
                <div class="details-row">
                    <span class="label">Payment Mode</span>
                    <span class="value"><%= order.getPaymentMode() %></span>
                </div>
                <div class="details-row">
                    <span class="label">Deliver to</span>
                    <span class="value">Your Saved Address</span>
                </div>
            </div>
            
            <div class="action-footer">
                <a href="<%= request.getContextPath() %>/orderhistory" class="track-order-btn">Track Order History</a>
            </div>
        </div>

        <aside class="bill-column">
            <div class="bill-card">
                <p class="section-label">Bill Details</p>
                <ul class="item-summary-list">
                    <% for (OrderItem item : items) { %>
                        <li>
                            <span class="item-title"><%= item.getOrderItem() %> x <%= item.getQuantity() %></span>
                            <span class="item-cost">₹<%= item.getTotalPrice() %></span>
                        </li>
                    <% } %>
                </ul>
                
                <div class="bill-divider"></div>
                
                <div class="final-payable">
                    <span>Total Paid</span>
                    <span>₹<%= order.getTotalAmount() %></span>
                </div>
            </div>
        </aside>
    </div>
</main>

<footer>
    <p>© 2025 Delivo - Premium Food Delivery</p>
</footer>

</body>
</html>