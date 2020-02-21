package controller;

import domain.Loghmeh;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/finalize")
public class FinalizeOrder extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String finalizePageName;
        if (Loghmeh.getInstance().getCart() == null) {
            System.out.println("here");
            request.setAttribute("badFinalize", "true");
            request.setAttribute("noCart", "true");
            response.setStatus(400);
            finalizePageName = "/error.jsp";
        } else {
            if (Loghmeh.getInstance().getCart().getPrice() > Loghmeh.getInstance().getCustomer(0).getCredit()) {
                request.setAttribute("badFinalize", "true");
                request.setAttribute("noCredit", "true");
                response.setStatus(400);
                finalizePageName = "/error.jsp";
            } else {
                String result = Loghmeh.getInstance().finalizeOrder();
                request.setAttribute("result", result);
                response.setStatus(200);
                finalizePageName = "/finalize.jsp";
            }
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(finalizePageName);
        requestDispatcher.forward(request, response);
    }
}
