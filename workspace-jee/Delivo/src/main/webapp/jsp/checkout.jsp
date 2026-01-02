<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.delivo.model.CartItem"%>

<!DOCTYPE html>
<html>
<head>
    <title>Checkout - Delivo</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/checkout.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/common.css">
    
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
            <a href="<%= request.getContextPath() %>/restaurants">Home</a>
            <a href="<%= request.getContextPath() %>/cart">Cart</a>
            <a href="<%= request.getContextPath() %>/orderhistory">Orders</a>
            <a href="<%= request.getContextPath() %>/profile">Profile</a>
            <a href="<%= request.getContextPath() %>/signin">Login</a>
        </div>
    </div>
</header>


<section class="hero">
    <h2>Checkout</h2>
</section>

<%
@SuppressWarnings("unchecked")
List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

if (cart == null || cart.isEmpty()) {
%>

<p>Your cart is empty. <a href="<%= request.getContextPath() %>/cart">Go back</a></p>

<%
    return; // stop further execution
}

int grandTotal = 0;
for (CartItem item : cart) {
    grandTotal += item.getTotalPrice();
}
%>

<section class="checkout-section">
    <form action="<%= request.getContextPath() %>/checkout" method="post" class="checkout-container">
        
        <div class="checkout-left">
            <div class="checkout-card">
                <h3>Delivery Address</h3>
                <textarea name="address" required placeholder="Enter your full address here..."></textarea>
            </div>

            <div class="checkout-card">
                <h3>Payment Method</h3>
                <div class="payment-options">
                    <label class="payment-label">
                        <input type="radio" name="payment" value="COD" checked> 
                        <span>Cash on Delivery</span>
                    </label>
                    <label class="payment-label">
                        <input type="radio" name="payment" value="ONLINE"> 
                        <span>Online Payment</span>
                    </label>
                </div>
            </div>
        </div>

        <div class="checkout-right">
            <div class="summary-card">
                <h3>Order Summary</h3>
                <ul class="summary-list">
                    <% for (CartItem item : cart) { %>
                        <li>
                            <span class="item-name"><%= item.getItemName() %> x <%= item.getQuantity() %></span>
                            <span class="item-total">₹<%= item.getTotalPrice() %></span>
                        </li>
                    <% } %>
                </ul>

                <div class="bill-details">
                    <div class="total-row">
                        <span>Total Payable</span>
                        <span>₹<%= grandTotal %></span>
                    </div>
                </div>

                <input type="hidden" name="totalAmount" value="<%= grandTotal %>">
                <button type="submit" class="place-order-btn">Place Order</button>
            </div>
        </div>
        
    </form>
</section>
<footer>© 2025 Delivo</footer>
</body>
</html>
