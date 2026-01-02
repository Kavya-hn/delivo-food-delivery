package com.delivo.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.delivo.model.CartItem;
import com.delivo.dao.MenuDAO;
import com.delivo.dao.impl.MenuDAOImpl;
import com.delivo.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private MenuDAO menuDAO = new MenuDAOImpl();

    // ------------------------------
    //   POST  (ADD / UPDATE / REMOVE)
    // ------------------------------
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // ⭐ LOGIN VALIDATION
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/signin");
            return;
        }

        // ⭐ YOUR ORIGINAL CART LOGIC
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            int menuId = Integer.parseInt(request.getParameter("menuId"));

            Menu menu = menuDAO.getMenuItem(menuId);

            if (menu != null) {
                boolean exists = false;

                for (CartItem item : cart) {
                    if (item.getMenuId() == menuId) {
                        item.setQuantity(item.getQuantity() + 1);
                        exists = true;
                        break;
                    }
                }

                if (!exists) {
                    CartItem cartItem = new CartItem(
                        menu.getMenuId(),
                        menu.getItemName(),
                        menu.getPrice(),
                        1,
                        menu.getImagePath(),
                        menu.getRestaurantId()
                    );
                    cart.add(cartItem);
                }
            }
        }

        else if ("remove".equals(action)) {
            int menuId = Integer.parseInt(request.getParameter("menuId"));
            cart.removeIf(item -> item.getMenuId() == menuId);
        }

        else if ("update".equals(action)) {
            int menuId = Integer.parseInt(request.getParameter("menuId"));
            int qty = Integer.parseInt(request.getParameter("quantity"));

            for (CartItem item : cart) {
                if (item.getMenuId() == menuId) {
                    item.setQuantity(qty);
                    break;
                }
            }
        }

        else if ("clear".equals(action)) {
            cart.clear();
        }

        session.setAttribute("cart", cart);

        // ⭐ always redirect to cart page
        response.sendRedirect(request.getContextPath() + "/jsp/cart.jsp");
    }

    // ------------------------------
    //   GET (OPEN CART PAGE)
    // ------------------------------
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // ⭐ LOGIN VALIDATION for GET
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/signin");
            return;
        }

        response.sendRedirect(request.getContextPath() + "/jsp/cart.jsp");
    }
}
