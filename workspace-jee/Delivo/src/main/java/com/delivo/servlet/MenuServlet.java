package com.delivo.servlet;

import java.io.IOException;
import java.util.List;

import com.delivo.dao.MenuDAO;
import com.delivo.dao.impl.MenuDAOImpl;
import com.delivo.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private MenuDAO menuDAO = new MenuDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String rid = request.getParameter("restaurantId");

        // No restaurant ID → redirect back to restaurant listing
        if (rid == null) {
            response.sendRedirect(request.getContextPath() + "/restaurants");
            return;
        }

        int restaurantId = Integer.parseInt(rid);

        // Fetch menu items for this restaurant
        List<Menu> menuList = menuDAO.getMenuByRestaurant(restaurantId);

        // Attach data to request
        request.setAttribute("menuList", menuList);
        request.setAttribute("restaurantId", restaurantId);

        // Forward to menu JSP
        request.getRequestDispatcher("/jsp/menu.jsp").forward(request, response);
    }
}
