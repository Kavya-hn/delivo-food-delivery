<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Sign Up - Delivo</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/common.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/signup.css">
</head>
<body>
<body>

<div class="video-bg">
    <video autoplay muted loop playsinline>
        <source src="<%= request.getContextPath() %>/static/videos/food.mp4" type="video/mp4">
    </video>
</div>

<div class="video-overlay"></div>

<section class="auth-wrapper">
    <section class="auth-box">



    <h2>Create Account</h2>

    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <p style="color:red; font-weight:bold;"><%= error %></p>
    <% } %>

    <form action="<%= request.getContextPath() %>/signup" method="post">

        <input type="text" name="name" placeholder="Full Name" required>

        <input type="text" name="username" placeholder="Username" required>

        <input type="email" name="email" placeholder="Email" required>

        <input type="password" name="password" placeholder="Password" required>

        <input type="text" name="phone" placeholder="Phone Number" required>

        <textarea name="address" placeholder="Address" rows="3" required></textarea>

        <button type="submit">Sign Up</button>

        <p>Already have an account?
            <!-- FIXED LINK: Correct servlet mapping -->
            <a href="<%= request.getContextPath() %>/signin">Sign In</a>
        </p>

    </form>
</section>
</section>
</body>
</html>
