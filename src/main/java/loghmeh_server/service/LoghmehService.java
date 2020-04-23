package loghmeh_server.service;

import loghmeh_server.domain.*;
import loghmeh_server.repository.customer.Customer;
import loghmeh_server.repository.foodparty_food.FoodPartyFood;
import loghmeh_server.repository.order.Order;
import loghmeh_server.repository.restaurant.Restaurant;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@CrossOrigin("*")
@RestController
public class LoghmehService {

    @RequestMapping(value = "/customer/{userId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerInfo getCustomerInfoController(HttpServletResponse servletResponse, @PathVariable(value = "userId") int customerId) {
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa get cust");
        CustomerInfo info = new CustomerInfo();
        Customer customer = Loghmeh.getInstance().getCustomerById(customerId);
        if(customer != null){
            info.setFirstName(customer.getFirstName());
            info.setLastName(customer.getLastName());
            info.setEmail(customer.getEmail());
            info.setPhoneNumber(customer.getPhoneNumber());
            info.setCredit(customer.getCredit());
            servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
            return info;
        }
        else{
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @RequestMapping(value = "/ordinary_restaurants", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Restaurant> getOrdinaryRestaurantsController(HttpServletResponse servletResponse) {
        servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa get rest" + Loghmeh.getInstance().getSpecifiedRestaurants("ordinary").size());
        return Loghmeh.getInstance().getSpecifiedRestaurants("ordinary");
    }

    @RequestMapping(value = "/foodparty_restaurants", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Restaurant> getFoodPartyRestaurantsController(HttpServletResponse servletResponse) {
        servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa get food partyrest" + Loghmeh.getInstance().getSpecifiedRestaurants("foodparty").size());
        return Loghmeh.getInstance().getSpecifiedRestaurants("foodparty");
    }

    @RequestMapping(value = "/foodparty_foods", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<FoodPartyFood> getFoodPartyFoodsController(HttpServletResponse servletResponse) {
        servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa get food " + Loghmeh.getInstance().getFoodPartyFoods().size());
        return Loghmeh.getInstance().getFoodPartyFoods();
    }

    @RequestMapping(value = "/foodparty_food/{restaurantId}/{foodName}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public FoodPartyFood getFoodPartyFoodController(HttpServletResponse servletResponse
            , @PathVariable(value = "restaurantId") String restaurantId,  @PathVariable(value = "foodName") String foodName) {
        FoodPartyFood foodPartyFood = Loghmeh.getInstance().getFoodPartyFood(restaurantId, foodName);
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa get foooood " + restaurantId + foodName);
        if(foodPartyFood != null) {
            servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        }
        else {
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return foodPartyFood;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Order> getOrdersController(HttpServletResponse servletResponse) {
        servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        return Loghmeh.getInstance().getCustomer(0).getOrders();
    }

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Order getOrder(HttpServletResponse servletResponse,
                                         @PathVariable(value = "orderId") int orderId){
        for(Order order : Loghmeh.getInstance().getCustomer(0).getOrders()) {
            if (order.getId() == orderId) {
                servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
                return order;
            }
        }
        servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return null;
    }

    @RequestMapping(value = "/cart/{userId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Order getRestaurantsController(HttpServletResponse servletResponse, @PathVariable(value = "userId") int customerId) {
        servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa get cart");
        Customer customer = Loghmeh.getInstance().getCustomerById(customerId);
        if(customer == null){
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        Order cart = customer.getCart();
        if(cart != null)
            servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        else
            servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
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
                                              @RequestParam(value = "userId") int userId,
                                              @RequestParam(value = "creditIncrease") int creditIncrease){
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa put credit");
        Customer customer = Loghmeh.getInstance().getCustomerById(userId);
        ReqResult result = new ReqResult();
        if(customer != null){
            customer.increaseCredit(creditIncrease);
            result.setSuccessful(true);
            servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        }
        else{
            result.setSuccessful(false);
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return result;
    }

    @RequestMapping(value = "/put_cart", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ReqResult addToCartController(HttpServletResponse servletResponse,
                                         @RequestParam(value = "userId") int userId,
                                         @RequestParam(value = "restaurantId") String restaurantId,
                                         @RequestParam(value = "foodName") String foodName,
                                         @RequestParam(value = "foodCount") int foodCount,
                                         @RequestParam(value = "isFoodParty") boolean isFoodParty){
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa put in cart");
        String result = Loghmeh.getInstance().updateCart(userId, restaurantId, foodName, foodCount, isFoodParty, "add");
        ReqResult resp = new ReqResult();
        if(result.equals("added")){
            resp.setSuccessful(true);
            servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        }
        else if(result.equals("not found")){
            resp.setSuccessful(false);
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        else if(result.equals("different restaurant order")){
            resp.setSuccessful(false);
            servletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        return resp;
    }

    @RequestMapping(value = "/finalize", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ReqResult finalizeController(HttpServletResponse servletResponse,
                                        @RequestParam(value = "userId") int userId){
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa put in finalize");
        Order order = Loghmeh.getInstance().getCart(0);
        String result = Loghmeh.getInstance().finalizeOrder(userId);
        ReqResult resp = new ReqResult();
        if(result.equals("done")){
            Loghmeh.getInstance().findDelivery(order);
            resp.setSuccessful(true);
            servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        }
        else if(result.equals("not found")){
            resp.setSuccessful(false);
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        else{
            System.out.println("result when 403: " + result);
            resp.setSuccessful(false);
            servletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN, result);
            resp.setErrorMsg(result);

        }
        return resp;
    }


    @RequestMapping(value = "/del_cart", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ReqResult removeFromCartController(HttpServletResponse servletResponse,
                                              @RequestParam(value = "userId") int userId,
                                              @RequestParam(value = "restaurantId") String restaurantId,
                                              @RequestParam(value = "foodName") String foodName,
                                              @RequestParam(value = "isFoodParty") boolean isFoodParty){
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa del from cart");
        String result = Loghmeh.getInstance().updateCart(userId, restaurantId, foodName, 1, isFoodParty, "remove");
        ReqResult resp = new ReqResult();
        if(result.equals("removed")){
            resp.setSuccessful(true);
            servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        }
        else if(result.equals("not found")){
            resp.setSuccessful(false);
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return resp;
    }


}
