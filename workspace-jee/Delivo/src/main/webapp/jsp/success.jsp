<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thank You - Delivo</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/success.css">
</head>
<body>

<%
    // Capturing the orderId passed from your CheckoutServlet
    String orderId = request.getParameter("orderId");
%>

<div class="success-wrapper">
    <div class="success-card">
        <a href="<%= request.getContextPath() %>/orderconfirmation?orderId=<%= orderId %>" class="close-x">×</a>
        
        <div class="success-icon">✔</div>
        
        <h1>🎉 Thank You!</h1>
        <p>Your order has been confirmed</p>
        <p>🍽️ We’re preparing it with love</p>

        <div class="button-group">
            <a href="<%= request.getContextPath() %>/orderconfirmation?orderId=<%= orderId %>" class="btn btn-secondary">
                View Order Summary
            </a>
            <a href="<%= request.getContextPath() %>/restaurants" class="btn">Back to Home</a>
        </div>
    </div>
</div>

</body>
</html>