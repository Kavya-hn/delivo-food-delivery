package com.delivo.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import com.delivo.dao.OrderDAO;
import com.delivo.dao.OrderItemDAO;
import com.delivo.dao.impl.OrderDAOImpl;
import com.delivo.dao.impl.OrderItemDAOImpl;
import com.delivo.model.CartItem;
import com.delivo.model.Order;
import com.delivo.model.OrderItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private OrderDAO orderDAO = new OrderDAOImpl();
    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

    // ---------------------- SHOW CHECKOUT PAGE -----------------------
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        // If cart empty → redirect back
        if (cart == null || cart.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/jsp/cart.jsp");
            return;
        }

        // Show checkout page
        request.getRequestDispatcher("/jsp/checkout.jsp").forward(request, response);
    }

    // ---------------------- PLACE ORDER ------------------------------
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        Integer userId = (Integer) session.getAttribute("userId");

        // Validate session and cart
        if (cart == null || cart.isEmpty() || userId == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/cart.jsp");
            return;
        }

        int totalAmount = Integer.parseInt(request.getParameter("totalAmount"));
        String paymentMode = request.getParameter("payment");
        String address = request.getParameter("address"); // For future use

        // All items are from one restaurant
        int restaurantId = cart.get(0).getRestaurantId();

        // ---------------------- CREATE ORDER --------------------------
        Order order = new Order();
        order.setUserId(userId);
        order.setRestaurantid(restaurantId);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(totalAmount);
        order.setStatus("Pending");
        order.setPaymentMode(paymentMode);

        // Insert order → get generated orderId
        int orderId = orderDAO.insertOrderReturnId(order);

        // ---------------------- INSERT ORDER ITEMS ---------------------
        for (CartItem c : cart) {
            OrderItem item = new OrderItem();
            item.setOrderId(orderId);
            item.setMenuId(c.getMenuId());
            item.setOrderItem(c.getItemName());
            item.setQuantity(c.getQuantity());
            item.setTotalPrice(c.getTotalPrice());

            orderItemDAO.addOrderItem(item);  // LOGIC 100% KEPT
        }

     // ---------------------- CLEAR CART -----------------------------
        session.removeAttribute("cart");

        // ---------------------- GO TO INTERMEDIARY SUCCESS PAGE ----------------
        // We pass orderId in the URL so success.jsp knows where to send the user next
        response.sendRedirect(request.getContextPath() + "/jsp/success.jsp?orderId=" + orderId);
    }
}
