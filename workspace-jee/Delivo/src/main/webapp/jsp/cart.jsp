<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.delivo.model.CartItem"%>

<!DOCTYPE html>
<html>
<head>
    <title>Cart - Delivo</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/cart.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/common.css">
    
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
        </div>
    </div>
</header>


<section class="hero"><h2>Your Cart</h2></section>

<section class="cart-section">
<h2>Items in Your Cart</h2>

<%
@SuppressWarnings("unchecked")
List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
int grandTotal = 0;

if (cart == null || cart.isEmpty()) {
%>

<p>Your cart is empty.</p>

<%
} else {
%>

<table class="cart-table">
    <thead>
        <tr>
            <th>Dish</th>
            <th>Qty</th>
            <th>Price (₹)</th>
            <th>Total (₹)</th>
            <th>Action</th>
        </tr>
    </thead>

 <tbody>
    <%
        for (CartItem item : cart) {
            grandTotal += item.getTotalPrice();
            
            // Apply trim() to handle hidden characters/spaces from the DB
            String imagePath = item.getImagePath().trim(); 
    %>

        <tr>
            <td data-label="Dish">
                <img src="<%= imagePath %>" 
                     alt="<%= item.getItemName() %>" 
                     style="width:80px;height:60px;object-fit:cover;">

                <div><strong><%= item.getItemName() %></strong></div>
            </td>

            <td data-label="Qty">
                <form action="<%= request.getContextPath() %>/cart" method="post">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                    <input type="number" name="quantity" value="<%= item.getQuantity() %>" min="1" style="width:60px;">
                    <button type="submit">Update</button>
                </form>
            </td>

            <td data-label="Price">₹<%= item.getPrice() %></td>
            
            <td data-label="Total">₹<%= item.getTotalPrice() %></td>

            <td data-label="Action">
                <form action="<%= request.getContextPath() %>/cart" method="post">
                    <input type="hidden" name="action" value="remove">
                    <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                    <button type="submit">Remove</button>
                </form>
            </td>
        </tr>

    <%
        }
    %>

        <tr>
            <td colspan="3" style="text-align:right;"><strong>Grand Total</strong></td>
            <td colspan="2"><strong>₹<%= grandTotal %></strong></td>
        </tr>

    </tbody>
</table>
</table>

<%
    // Get current restaurant ID to redirect back to the correct menu
    int currentRestId = (cart != null && !cart.isEmpty()) ? cart.get(0).getRestaurantId() : 0;
%>

<div class="cart-button-container" style="margin-top:25px; display: flex; align-items: center; gap: 15px;">
    
    <a href="<%= request.getContextPath() %>/MenuServlet?restaurantId=<%= currentRestId %>" 
       class="order-more-link">
       + Add More Items
    </a>

    <form action="<%= request.getContextPath() %>/checkout" method="get" style="display:inline-block;">
        <button type="submit" class="checkout-btn">Proceed to Checkout</button>
    </form>

    <form action="<%= request.getContextPath() %>/cart" method="post" style="display:inline-block;">
        <input type="hidden" name="action" value="clear">
        <button type="submit" class="clear-btn">Clear Cart</button>
    </form>
</div>

<%
} // end else
%>

</section>

<footer>&copy; 2025 Delivo</footer>
</body>
</html>