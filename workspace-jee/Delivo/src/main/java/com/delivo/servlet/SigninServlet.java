package com.delivo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.delivo.dao.UserDAO;
import com.delivo.dao.impl.UserDAOImpl;
import com.delivo.model.User;

@WebServlet("/signin")
public class SigninServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAOImpl();   // SAME LOGIC
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // -------- READ LOGIN DETAILS --------
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // -------- FETCH USER FROM DB --------
        User user = userDAO.getUserByUsernameAndPassword(username, password);

        // -------- INVALID LOGIN --------
        if (user == null) {
            request.setAttribute("error", "Invalid username or password!");
            request.getRequestDispatcher("/jsp/signin.jsp").forward(request, response);
            return;
        }

        // -------- VALID LOGIN --------
        HttpSession session = request.getSession();
        session.setAttribute("loggedUser", user);
        session.setAttribute("userId", user.getUserId());   // IMPORTANT: keep your logic
        session.setAttribute("userName", user.getName());

        // -------- REDIRECT TO RESTAURANT PAGE --------
        response.sendRedirect(request.getContextPath() + "/restaurants");
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/signin.jsp").forward(request, response);
    }
}
