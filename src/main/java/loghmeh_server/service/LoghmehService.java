package loghmeh_server.service;

import loghmeh_server.domain.Customer;
import loghmeh_server.domain.Loghmeh;
import loghmeh_server.domain.Order;
import loghmeh_server.domain.Restaurant;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
public class LoghmehService {

    @RequestMapping(value = "/customer/{userId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerInfo getCustomerInfo(HttpServletResponse servletResponse, @PathVariable(value = "userId") int customerId) {
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
    public ArrayList<Restaurant> getOrdinaryRestaurants(HttpServletResponse servletResponse) {
        servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa get rest" + Loghmeh.getInstance().getOrdinaryRestaurants().size());
        return Loghmeh.getInstance().getOrdinaryRestaurants();
    }

    @RequestMapping(value = "/cart/{userId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Order getRestaurants(HttpServletResponse servletResponse, @PathVariable(value = "userId") int customerId) {
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

    @RequestMapping(value = "/credit", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PutResult increaseCredit(HttpServletResponse servletResponse,
            @RequestParam(value = "userId") int userId,
            @RequestParam(value = "creditIncrease") int creditIncrease){
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa put credit");
        Customer customer = Loghmeh.getInstance().getCustomerById(userId);
        PutResult result = new PutResult();
        if(customer != null){
            customer.increaseCredit(creditIncrease);
            result.setSuccessful(true);
            servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
            System.out.println("customer credit" + customer.getCredit());
        }
        else{
            result.setSuccessful(false);
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return result;
    }

    @RequestMapping(value = "/put_cart", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PutResult addToCart(HttpServletResponse servletResponse,
                                     @RequestParam(value = "userId") int userId,
                                     @RequestParam(value = "restaurantId") String restaurantId,
                                     @RequestParam(value = "foodName") String foodName,
                                     @RequestParam(value = "isFoodParty") boolean isFoodParty){
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa put in cart");
        String result = Loghmeh.getInstance().addToCart(userId, restaurantId, foodName, isFoodParty);
        PutResult resp = new PutResult();
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
    public PutResult addToCart(HttpServletResponse servletResponse,
                               @RequestParam(value = "userId") int userId){
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa put in finalize");
        String result = Loghmeh.getInstance().finalizeOrder(userId);
        PutResult resp = new PutResult();
        if(result.equals("done")){
            resp.setSuccessful(true);
            servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        }
        else if(result.equals("not found")){
            resp.setSuccessful(false);
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        else{
            resp.setSuccessful(false);
            servletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN, result);
        }
        return resp;
    }


}
