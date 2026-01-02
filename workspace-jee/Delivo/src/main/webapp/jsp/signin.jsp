<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Sign In - Delivo</title>

    <!-- Common styles (colors, variables) -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/common.css">

    <!-- Signin page styles -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/signin.css">
</head>

<body class="auth-page">

    <!-- Background Video -->
    <div class="video-bg">
        <video autoplay muted loop playsinline>
            <source src="<%= request.getContextPath() %>/static/videos/food.mp4" type="video/mp4">
            Your browser does not support the video tag.
        </video>
    </div>

    <!-- Dark overlay -->
    <div class="video-overlay"></div>

    <!-- Auth Card -->
    <section class="auth-box">

        <h2>Sign In</h2>

        <%-- Error message from servlet --%>
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
            <p style="color:red; font-weight:bold; text-align:center; margin-bottom:12px;">
                <%= error %>
            </p>
        <% } %>

        <form action="<%= request.getContextPath() %>/signin" method="post">

            <input
                type="text"
                name="username"
                placeholder="Username"
                required
            >

            <input
                type="password"
                name="password"
                placeholder="Password"
                required
            >

            <button type="submit">Sign In</button>

            <p>
                Don’t have an account?
                <a href="<%= request.getContextPath() %>/signup">Sign Up</a>
            </p>

        </form>

    </section>

</body>
</html>
