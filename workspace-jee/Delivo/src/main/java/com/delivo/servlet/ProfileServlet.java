package com.delivo.servlet;

import java.io.IOException;

import com.delivo.dao.UserDAO;
import com.delivo.dao.impl.UserDAOImpl;
import com.delivo.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ---------- CHECK LOGIN ----------
        HttpSession session = request.getSession(false);
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/signin.jsp");
            return;
        }

        // ---------- FETCH USER DETAILS ----------
        User user = userDAO.getUser(userId);
        request.setAttribute("user", user);

        // ---------- SHOW PROFILE PAGE ----------
        request.getRequestDispatcher("/jsp/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ---------- CHECK LOGIN ----------
        HttpSession session = request.getSession(false);
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/signin.jsp");
            return;
        }

        // ---------- READ FORM DATA ----------
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneStr = request.getParameter("phone");
        String address = request.getParameter("address");

        long phone = Long.parseLong(phoneStr);

        // ---------- FETCH USER FROM DB ----------
        User user = userDAO.getUser(userId);

        // ---------- UPDATE USER DATA ----------
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);

        // ---------- SAVE CHANGES ----------
        userDAO.updateUser(user);

        // Update session name if displayed in navbar
        session.setAttribute("userName", user.getName());

        // ---------- REDIRECT TO PROFILE WITH SUCCESS MESSAGE ----------
        response.sendRedirect(request.getContextPath() + "/profile?success=1");
    }
}
