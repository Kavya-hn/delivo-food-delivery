package com.delivo.servlet;

import java.io.IOException;
import java.util.List;

import com.delivo.dao.OrderDAO;
import com.delivo.dao.OrderItemDAO;
import com.delivo.dao.impl.OrderDAOImpl;
import com.delivo.dao.impl.OrderItemDAOImpl;
import com.delivo.model.Order;
import com.delivo.model.OrderItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/orderhistory")
public class OrderHistoryServlet extends HttpServlet {

    private OrderDAO orderDAO = new OrderDAOImpl();
    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        // ------------------- CHECK LOGIN -------------------
        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/signin.jsp");
            return;
        }

        // ------------------- FETCH ALL ORDERS -------------------
        List<Order> orders = orderDAO.getOrdersByUser(userId);

        // ------------------- FETCH ITEMS FOR EACH ORDER -------------------
        for (Order order : orders) {
            List<OrderItem> items = orderItemDAO.getItemsByOrder(order.getOrderId());
            order.setOrderItems(items);    // <-- EXACT SAME LOGIC
        }

        // ------------------- SEND TO JSP -------------------
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/jsp/orderhistory.jsp").forward(request, response);
    }
}
