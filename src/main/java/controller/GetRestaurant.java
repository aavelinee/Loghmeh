package controller;

import domain.Loghmeh;
import domain.Restaurant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/restaurant")
public class GetRestaurant extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String restaurantId = request.getParameter("restaurantId");
        Restaurant restaurant = Loghmeh.getInstance().getRestaurantById(restaurantId);
        request.setAttribute("restaurant", restaurant);
        String restaurantPageName = "restaurant.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(restaurantPageName);
        requestDispatcher.forward(request, response);

    }
}
