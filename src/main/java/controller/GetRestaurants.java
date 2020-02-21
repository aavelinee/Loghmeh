package controller;

import domain.Loghmeh;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/restaurants")
public class GetRestaurants extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("restaurants", Loghmeh.getInstance().getCustomer(0).getCloseRestaurants());
        String restaurantsPageName = "restaurants.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(restaurantsPageName);
        requestDispatcher.forward(request, response);
    }
}
