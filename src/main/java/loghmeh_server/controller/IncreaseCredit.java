package loghmeh_server.controller;

import loghmeh_server.domain.Loghmeh;
import org.apache.commons.lang.StringUtils;

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
        if(StringUtils.isBlank(request.getParameter("credit")) || Integer.valueOf(request.getParameter("credit")) <= 0){
            request.setAttribute("customer", Loghmeh.getInstance().getCustomer(0));
            response.setStatus(403);
            request.setAttribute("badCredit", "true");
            String profilePageName = "/profile.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(profilePageName);
            requestDispatcher.forward(request, response);
        }
        else{
            Loghmeh.getInstance().getCustomer(0).increaseCredit(Integer.valueOf(request.getParameter("credit")));
            response.setStatus(200);
            response.sendRedirect("/profile");
        }

    }
}
