import loghmeh_server.domain.Loghmeh;
import loghmeh_server.repository.customer.Customer;
import loghmeh_server.repository.customer.CustomerMapper;
import loghmeh_server.repository.delivery.Delivery;
import loghmeh_server.repository.delivery.DeliveryMapper;
import loghmeh_server.repository.foodparty_food.FoodPartyFood;
import loghmeh_server.repository.foodparty_food.FoodPartyFoodMapper;
import loghmeh_server.repository.location.Location;
import loghmeh_server.repository.location.LocationMapper;
import loghmeh_server.repository.order.Order;
import loghmeh_server.repository.order.OrderMapper;
import loghmeh_server.repository.order_item.OrderItem;
import loghmeh_server.repository.restaurant.Restaurant;
import loghmeh_server.repository.restaurant.RestaurantMapper;
import loghmeh_server.security.JWTUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;

public class test {

    String token;
    Customer customer = new Customer(1, "احسان", "خامس‌پناه", "۰۹۱۲۳۴۵۶۷۸۹", "ekhamespanah@yahoo.com", 0f, 0f, "pass");

    @Before
    public void setUp() throws SQLException {
        token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLYp9it2LPYp9mGINiu2KfZhdiz4oCM2b7Zhtin2YciLCJpYXQiOjE1ODg5MDM3NjUsImlzcyI6InNlY3VyZS1hcGkiLCJleHAiOjE1ODg5MDM3NzAsInVzZXJJZCI6MX0.A-7WD3KZp_qbLDO691U_O2OO9C3Sk9drAPpdQkUcJD4";
    }

    @Test
    public void whenLoaded_find() throws SQLException {
        Object body = JWTUtils.getInstance().verifyJWTToken(JWTUtils.getInstance().generateJWTToken(customer));
        System.out.println(body);
    }

}
