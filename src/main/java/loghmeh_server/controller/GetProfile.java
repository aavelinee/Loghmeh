package loghmeh_server.controller;

import loghmeh_server.domain.Loghmeh;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class GetProfile extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("customer", Loghmeh.getInstance().getCustomer(0));
        response.setStatus(200);
        String profilePageName = "profile.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(profilePageName);
        requestDispatcher.forward(request, response);
    }
}
