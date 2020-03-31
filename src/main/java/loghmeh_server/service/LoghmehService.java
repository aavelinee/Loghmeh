package loghmeh_server.service;

import loghmeh_server.domain.Customer;
import loghmeh_server.domain.Loghmeh;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoghmehService {

    @RequestMapping(value = "/getCustomerInfo/{customerId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerInfo getCustomerInfo(@PathVariable(value = "customerId") int customerId) {
        CustomerInfo info = new CustomerInfo();
        Customer customer = Loghmeh.getInstance().getCustomer(0);
        info.setFirstName(customer.getFirstName());
        info.setLastName(customer.getLastName());
        info.setEmail(customer.getEmail());
        info.setPhoneNumber(customer.getPhoneNumber());
        info.setCredit(customer.getCredit());
        return info;
    }

}
