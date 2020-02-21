package controller;

import domain.Loghmeh;
import domain.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cart")
public class GetCart extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Loghmeh loghmeh = Loghmeh.getInstance();
        Order cart = loghmeh.getCart();
        String cartPageName;
        request.setAttribute("restaurantName", cart.getRestaurant().getName());
        request.setAttribute("cart", cart);
        cartPageName = "cart.jsp";
        response.setStatus(200);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(cartPageName);
        requestDispatcher.forward(request, response);
    }
}
