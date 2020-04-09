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
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
public class LoghmehService {

    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerInfo getCustomerInfo(HttpServletResponse servletResponse, @PathVariable(value = "customerId") int customerId) {
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
    public ArrayList<Restaurant> getRestaurants(HttpServletResponse servletResponse) {
        servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa get rest" + Loghmeh.getInstance().getOrdinaryRestaurants().size());
        return Loghmeh.getInstance().getOrdinaryRestaurants();
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Order> getOrders(HttpServletResponse servletResponse) {
        servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        System.out.println("orrrrdeeeerrrrsss" + Loghmeh.getInstance().getCustomer(0).getOrders().size());
        return Loghmeh.getInstance().getCustomer(0).getOrders();
    }

    @RequestMapping(value = "/credit", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PostResult increaseCredit(HttpServletResponse servletResponse,
            @RequestParam(value = "userId") int userId,
            @RequestParam(value = "creditIncrease") int creditIncrease){
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa put credit");
        Customer customer = Loghmeh.getInstance().getCustomerById(userId);
        PostResult result = new PostResult();
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




}
