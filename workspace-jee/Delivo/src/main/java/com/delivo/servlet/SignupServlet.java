package com.delivo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

import com.delivo.dao.UserDAO;
import com.delivo.dao.impl.UserDAOImpl;
import com.delivo.model.Role;
import com.delivo.model.User;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAOImpl();   // SAME LOGIC
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // -------- READ FORM DATA (SAME LOGIC) --------
            String name = request.getParameter("name");
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            long phone = Long.parseLong(request.getParameter("phone"));
            String address = request.getParameter("address");

            // -------- CREATE USER MODEL --------
            User user = new User();
            user.setName(name);
            user.setUserName(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhone(phone);
            user.setAddress(address);
            user.setRole(Role.USER);                     // SAME LOGIC
            user.setCreatedDate(LocalDateTime.now());    // SAME LOGIC
            user.setLastLoginDate(null);

            // -------- SAVE USER --------
            userDAO.addUser(user);

            // -------- REDIRECT TO LOGIN PAGE --------
            response.sendRedirect(request.getContextPath() + "/jsp/signin.jsp");
            return;

        } catch (Exception e) {
            e.printStackTrace();

            // -------- ERROR FLOW (SAME LOGIC) --------
            request.setAttribute("error", "Signup failed. Please try again.");
            request.getRequestDispatcher("/jsp/signup.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/signup.jsp").forward(request, response);
    }

}


