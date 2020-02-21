package controller;

import com.sun.xml.internal.ws.util.StringUtils;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String restaurantId = request.getParameter("restaurantId");

//        if (org.apache.commons.lang.StringUtils.isBlank(restaurantId)) {
//            String indexPageName = "index.jsp";
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher(indexPageName);
//            request.setAttribute("nullRestaurantId", "true");
//            requestDispatcher.forward(request, response);
//        }else if (){
//
//        } else {
            Restaurant restaurant = Loghmeh.getInstance().getRestaurantById(restaurantId);
//            if(restaurant == null) {
//
//            }
            request.setAttribute("restaurant", restaurant);
            String restaurantPageName = "restaurant.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(restaurantPageName);
            requestDispatcher.forward(request, response);
//        }


    }
}
