package controller;

import org.apache.commons.lang.StringUtils;
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
        Restaurant restaurant = Loghmeh.getInstance().getRestaurantById(restaurantId);
        String restaurantPageName;

        if(StringUtils.isBlank(restaurantId)){
            request.setAttribute("badRestaurant", "true");
            request.setAttribute("nullRestaurantId", "true");
            response.setStatus(403);
            restaurantPageName = "error.jsp";
        } else if(restaurant == null){
            request.setAttribute("badRestaurant", "true");
            request.setAttribute("restaurantIdNotFound", "true");
            response.setStatus(403);

            restaurantPageName = "error.jsp";
        } else if(!Loghmeh.getInstance().getCustomer(0).isRestaurantClose(restaurant.getLocation())){
            request.setAttribute("badRestaurant", "true");
            request.setAttribute("restaurantNotClose", "true");
            response.setStatus(403);

            restaurantPageName = "error.jsp";
        } else{
            request.setAttribute("restaurant", restaurant);
            restaurantPageName = "restaurant.jsp";
            response.setStatus(200);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(restaurantPageName);
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String restaurantId = request.getParameter("restaurantId");
        String foodName = request.getParameter("foodName");
        String isFoodParty = request.getParameter("isFoodParty");

        Restaurant restaurant = Loghmeh.getInstance().getRestaurantById(Loghmeh.getInstance().getIndexFromRestaurantId(restaurantId));
        String restaurantPageName;
        if(StringUtils.isBlank(restaurantId) || StringUtils.isBlank(foodName)) {
            if(StringUtils.isBlank(restaurantId)){
                request.setAttribute("badAddToCart", "true");
                request.setAttribute("nullRestaurantId", "true");
            }
            if(StringUtils.isBlank(foodName)){
                request.setAttribute("badAddToCart", "true");
                request.setAttribute("nullFoodName", "true");
            }
            response.setStatus(403);
            request.setAttribute("restaurantId", restaurantId);
            restaurantPageName = "error.jsp";
        } else{
            String addToCart = Loghmeh.getInstance().addToCart(Loghmeh.getInstance().getIndexFromRestaurantId(restaurantId), foodName, isFoodParty);
            if(addToCart.equals("added")){
                request.setAttribute("restaurant", restaurant);
                response.setStatus(200);
                if(isFoodParty.equals("false")) {
                    restaurantPageName = "restaurant.jsp";
                }
                else {
                    request.setAttribute("foodPartyRestaurants", Loghmeh.getInstance().getCustomer(0).getCloseFoodPartyRestaurants());
                    restaurantPageName = "foodParty.jsp";
                }
            }
            else if(addToCart.equals("no restaurant")){
                request.setAttribute("badAddToCart", "true");
                request.setAttribute("noRestaurant", "true");
                restaurantPageName = "error.jsp";
            }
            else if(addToCart.equals("no food")){
                request.setAttribute("badAddToCart", "true");
                request.setAttribute("noFood", "true");
                restaurantPageName = "error.jsp";
            }
            else{
                request.setAttribute("badAddToCart", "true");
                request.setAttribute("differentRestaurants", "true");
                response.setStatus(403);
                restaurantPageName = "error.jsp";
            }
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(restaurantPageName);
        requestDispatcher.forward(request, response);

    }
}
