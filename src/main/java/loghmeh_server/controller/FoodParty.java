package loghmeh_server.controller;

import loghmeh_server.domain.Loghmeh;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/foodparty")
public class FoodParty extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("foodPartyRestaurants", Loghmeh.getInstance().getCustomer(0).getCloseFoodPartyRestaurants());
        response.setStatus(200);
        String foodPartyPageName = "foodParty.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(foodPartyPageName);
        requestDispatcher.forward(request, response);
    }
}
