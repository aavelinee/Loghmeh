package controller;

import domain.Loghmeh;
import domain.Order;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order")
public class GetOrder extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.valueOf(request.getParameter("orderId"));
        String orderPageName;
        if(orderId > Loghmeh.getInstance().getCustomer(0).getOrders().size() || orderId < 1){
            request.setAttribute("badOrder", "true");
            request.setAttribute("noId", "true");
            response.setStatus(400);
            orderPageName = "/error.jsp";
        }
        else if(StringUtils.isBlank(request.getParameter("orderId"))){
            request.setAttribute("badOrder", "true");
            request.setAttribute("noId", "true");
            response.setStatus(403);
            orderPageName = "/error.jsp";
        }else{
            request.setAttribute("order", Loghmeh.getInstance().getCustomer(0).getOrders().get(orderId - 1));
            response.setStatus(200);
            orderPageName = "/order.jsp";
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(orderPageName);
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String finalizePageName;
        if (Loghmeh.getInstance().getCart(0) == null) {
            request.setAttribute("badFinalize", "true");
            request.setAttribute("noCart", "true");
            response.setStatus(400);
            finalizePageName = "/error.jsp";
        } else {
            if (Loghmeh.getInstance().getCart(0).getPrice() > Loghmeh.getInstance().getCustomer(0).getCredit()) {
                request.setAttribute("badFinalize", "true");
                request.setAttribute("noCredit", "true");
                response.setStatus(400);
                finalizePageName = "/error.jsp";
            } else {
                Order order = Loghmeh.getInstance().getCart(0);
                request.setAttribute("order", order);
                if(Loghmeh.getInstance().finalizeOrder()){
                    Loghmeh.getInstance().findDelivery(order);
                    response.setStatus(200);
                    finalizePageName = "/order.jsp";
                }
                else{
                    request.setAttribute("badFinalize", "true");
                    request.setAttribute("notAvailable", "true");
                    response.setStatus(400);
                    finalizePageName = "/error.jsp";
                }
            }
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(finalizePageName);
        requestDispatcher.forward(request, response);
    }
}

