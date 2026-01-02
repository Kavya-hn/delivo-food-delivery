package com.delivo.servlet;

import java.io.IOException;
import java.util.List;

import com.delivo.dao.RestaurantDAO;
import com.delivo.dao.impl.RestaurantDAOImpl;
import com.delivo.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;

@WebServlet("/restaurants")  // URL mapping
public class RestaurantServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private RestaurantDAO restaurantDAO;

    @Override
    public void init() throws ServletException {
        restaurantDAO = new RestaurantDAOImpl();  // EXACT SAME LOGIC
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // -------- FETCH ALL RESTAURANTS (same logic) --------
        List<Restaurant> restaurantList = restaurantDAO.getAllRestaurants();

        // Attach restaurants to request
        request.setAttribute("restaurants", restaurantList);

        // -------- FORWARD TO restaurant.jsp (same logic) --------
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/jsp/restaurant.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // POST should behave like GET — SAME LOGIC KEPT
        doGet(request, response);
    }
}
