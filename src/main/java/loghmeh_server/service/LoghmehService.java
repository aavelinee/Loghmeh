package loghmeh_server.service;

import loghmeh_server.domain.*;
import loghmeh_server.repository.customer.Customer;
import loghmeh_server.repository.foodparty_food.FoodPartyFood;
import loghmeh_server.repository.order.Order;
import loghmeh_server.repository.restaurant.Restaurant;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import loghmeh_server.security.JWTUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import static loghmeh_server.security.SecurityConstants.HEADER_STRING;
import static loghmeh_server.security.SecurityConstants.TOKEN_PREFIX;

@RestController
public class LoghmehService {

    @RequestMapping(value = "/customer", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer getCustomerInfoController(HttpServletResponse servletResponse, @RequestAttribute(value = "userId") int customerId) {
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa get cust");
        Customer customer = Loghmeh.getInstance().getCustomerById(customerId);
        servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        return customer;
    }

    @RequestMapping(value = "/ordinary_restaurants/{page}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Restaurant> getOrdinaryRestaurantsController(HttpServletResponse servletResponse,
                                                                  @PathVariable(value = "page") int page) {
        servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa get rests" + Loghmeh.getInstance().getSpecifiedRestaurants("ordinary", page).size());
        return Loghmeh.getInstance().getSpecifiedRestaurants("ordinary", page);
    }

    @RequestMapping(value = "/foodparty_restaurants", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Restaurant> getFoodPartyRestaurantsController(HttpServletResponse servletResponse) {
        servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        int page = 0;
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa get foodparty rests" + Loghmeh.getInstance().getSpecifiedRestaurants("foodparty", page).size());
        return Loghmeh.getInstance().getSpecifiedRestaurants("foodparty", page);
    }

    @RequestMapping(value = "/searched_restaurants", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Restaurant> getSearchedRestaurantsController(HttpServletResponse servletResponse,
                                                                  @RequestParam(value = "restaurantName") String restaurantName,
                                                                  @RequestParam(value = "foodName") String foodName,
                                                                  @RequestParam(value = "page") int page) {
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa get searched rests" + restaurantName + foodName);
        servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        return Loghmeh.getInstance().getSearchedRestaurants(restaurantName, foodName, page);
    }

    @RequestMapping(value = "/foodparty_foods", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<FoodPartyFood> getFoodPartyFoodsController(HttpServletResponse servletResponse) {
        servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa get foodparty foods " + Loghmeh.getInstance().getFoodPartyFoods().size());
        ArrayList<FoodPartyFood>foodPartyFoods = Loghmeh.getInstance().getFoodPartyFoods();
        System.out.println("even here?://");
        return foodPartyFoods;
    }

    @RequestMapping(value = "/foodparty_food", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public FoodPartyFood getFoodPartyFoodController(HttpServletResponse servletResponse
            , @RequestParam(value = "restaurantId") String restaurantId, @RequestParam(value = "foodName") String foodName) {
        FoodPartyFood foodPartyFood = Loghmeh.getInstance().getFoodPartyFood(restaurantId, foodName);
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa get foooood " + restaurantId + foodName);
        if (foodPartyFood != null) {
            servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        } else {
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        System.out.println("inja get food done");
        return foodPartyFood;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Order> getOrdersController(HttpServletResponse servletResponse, @RequestAttribute(value = "userId") int userId) {
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa get orders ");
        Customer customer = Loghmeh.getInstance().getCustomerById(userId);
        servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        return customer.getOrders();
    }

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Order getOrder(HttpServletResponse servletResponse,
                          @PathVariable(value = "orderId") int orderId, @RequestAttribute(value = "userId") int userId) {

        Customer customer = Loghmeh.getInstance().getCustomerById(userId);
        for (Order order : customer.getOrders()) {
            if (order.getId() == orderId) {
                servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
                return order;
            }
        }
        servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return null;
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Order getRestaurantsController(HttpServletResponse servletResponse, @RequestAttribute(value = "userId") int userId) {
        servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa get cart");
        Customer customer = Loghmeh.getInstance().getCustomerById(userId);
        Order cart = customer.getCart();
        if (cart != null) {
            System.out.println("Cart Found");
            servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        } else {
            System.out.println("No cart found");
            servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        }
        return cart;
    }

    @RequestMapping(value = "/next_time", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public float getRestaurantsController(HttpServletResponse servletResponse) {
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa get timeee");
        servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);

        return Loghmeh.getInstance().getNextFoodPartySchedulerFire();
    }

    @RequestMapping(value = "/credit", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ReqResult increaseCreditController(HttpServletResponse servletResponse,
                                              @RequestParam(value = "creditIncrease") int creditIncrease,
                                              @RequestAttribute(value = "userId") int userId) {
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa put credit");
        Customer customer = Loghmeh.getInstance().getCustomerById(userId);
        ReqResult result = new ReqResult();
        customer.increaseCredit(creditIncrease);
        result.setSuccessful(true);
        servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);

        return result;
    }

    @RequestMapping(value = "/put_cart", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ReqResult addToCartController(HttpServletResponse servletResponse,
                                         @RequestParam(value = "restaurantId") String restaurantId,
                                         @RequestParam(value = "foodName") String foodName,
                                         @RequestParam(value = "foodCount") int foodCount,
                                         @RequestParam(value = "isFoodParty") boolean isFoodParty,
                                         @RequestAttribute(value = "userId") int userId) {
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa put in cart ");
        String result = Loghmeh.getInstance().updateCart(userId, restaurantId, foodName, foodCount, isFoodParty, "add");
        ReqResult resp = new ReqResult();
        if (result.equals("added")) {
            resp.setSuccessful(true);
            servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        } else if (result.equals("not found")) {
            resp.setSuccessful(false);
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else if (result.equals("different restaurant order")) {
            resp.setSuccessful(false);
            servletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        return resp;
    }

    @RequestMapping(value = "/finalize", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ReqResult finalizeController(HttpServletResponse servletResponse,
                                        @RequestAttribute(value = "userId") int userId) {
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa put in finalize");
        Order order = Loghmeh.getInstance().getCart(userId);
        String result = Loghmeh.getInstance().finalizeOrder(userId);
        ReqResult resp = new ReqResult();
        if (result.equals("done")) {
            Loghmeh.getInstance().findDelivery(order, userId);
            resp.setSuccessful(true);
            servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        } else if (result.equals("not found")) {
            resp.setSuccessful(false);
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            resp.setSuccessful(false);
            servletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN, result);
            resp.setErrorMsg(result);

        }
        return resp;
    }


    @RequestMapping(value = "/del_cart", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ReqResult removeFromCartController(HttpServletResponse servletResponse,
                                              @RequestParam(value = "restaurantId") String restaurantId,
                                              @RequestParam(value = "foodName") String foodName,
                                              @RequestParam(value = "isFoodParty") boolean isFoodParty,
                                              @RequestAttribute(value = "userId") int userId) {
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa del from cart ");
        String result = Loghmeh.getInstance().updateCart(userId, restaurantId, foodName, 1, isFoodParty, "remove");
        ReqResult resp = new ReqResult();
        if (result.equals("removed")) {
            resp.setSuccessful(true);
            servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        } else if (result.equals("not found")) {
            resp.setSuccessful(false);
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return resp;
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ReqResult signUp(HttpServletResponse servletResponse,
                            @RequestParam(value = "firstname") String firstName,
                            @RequestParam(value = "lastname") String lastName,
                            @RequestParam(value = "email") String email,
                            @RequestParam(value = "phone_number") String phoneNumber,
                            @RequestParam(value = "password") String password) {
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa sign up ");
        ReqResult resp = new ReqResult();
        Customer customer = Loghmeh.getInstance().signUp(firstName, lastName, email, phoneNumber, password);
        if(customer != null) {
            resp.setSuccessful(true);
            servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
            servletResponse.setHeader(HEADER_STRING, TOKEN_PREFIX + JWTUtils.getInstance().generateJWTToken(customer));
            servletResponse.setHeader("Access-Control-Expose-Headers", HEADER_STRING);
            System.out.println("set header authorization");
        } else {
            resp.setSuccessful(false);
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return resp;
    }

    @RequestMapping(value = "/google_sign_in", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ReqResult GoogleSignIn(HttpServletResponse servletResponse,
                                  @RequestParam(value = "token") String token) {
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa google sign in");
        System.out.println(token);
//        String res = Loghmeh.getInstance().signInWithGoogle(token);
        ReqResult resp = new ReqResult();
        return resp;
    }

    @RequestMapping(value = "/sign_in", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ReqResult signIn(HttpServletResponse servletResponse,
                            @RequestParam(value = "email") String email,
                            @RequestParam(value = "password") String password) {
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa sign in");
//        String res = Loghmeh.getInstance().signIn(email, password);
        ReqResult resp = new ReqResult();
        return resp;
    }

}
