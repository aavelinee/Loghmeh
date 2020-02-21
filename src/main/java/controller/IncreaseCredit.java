package controller;

import domain.Loghmeh;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile/increaseCredit")
public class IncreaseCredit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int credit = Integer.valueOf(request.getParameter("credit"));
        if(credit > 0){
            Loghmeh.getInstance().getCustomer(0).increaseCredit(credit);
            response.sendRedirect("/profile");
        }
        else{
            request.setAttribute("customer", Loghmeh.getInstance().getCustomer(0));
            response.setStatus(403);
            request.setAttribute("badCredit", "true");
            String profilePageName = "/profile.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(profilePageName);
            requestDispatcher.forward(request, response);
        }

    }
}
