package com.delivo.servlet;

import java.io.IOException;
import java.util.List;

import com.delivo.dao.OrderDAO;
import com.delivo.dao.OrderItemDAO;
import com.delivo.dao.RestaurantDAO;
import com.delivo.dao.impl.OrderDAOImpl;
import com.delivo.dao.impl.OrderItemDAOImpl;
import com.delivo.dao.impl.RestaurantDAOImpl;
import com.delivo.model.Order;
import com.delivo.model.OrderItem;
import com.delivo.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/orderconfirmation")
public class OrderConfirmationServlet extends HttpServlet {

    private OrderDAO orderDAO = new OrderDAOImpl();
    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    private RestaurantDAO restaurantDAO = new RestaurantDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ---------------------- GET ORDER ID FROM URL ----------------------
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        // ---------------------- FETCH ORDER -------------------------------
        Order order = orderDAO.getOrder(orderId);
        request.setAttribute("order", order);

        // Invalid order → redirect to history
        if (order == null) {
            response.sendRedirect(request.getContextPath() + "/orderhistory");
            return;
        }

        // ---------------------- FETCH RESTAURANT ---------------------------
        Restaurant restaurant = restaurantDAO.getRestaurant(order.getRestaurantid());

        // ---------------------- FETCH ORDER ITEMS --------------------------
        List<OrderItem> items = orderItemDAO.getItemsByOrder(orderId);

        // ---------------------- SET ATTRIBUTES -----------------------------
        request.setAttribute("order", order);
        request.setAttribute("items", items);
        request.setAttribute("restaurant", restaurant);

        // ---------------------- FORWARD TO JSP -----------------------------
        request.getRequestDispatcher("/jsp/orderconfirmation.jsp").forward(request, response);
    }
}
