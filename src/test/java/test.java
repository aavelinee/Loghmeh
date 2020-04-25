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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;

public class test {

    Customer customer;
    @Before
    public void setUp() throws SQLException {
        customer = new Customer(1, "احسان", "خامس‌پناه", "۰۹۱۲۳۴۵۶۷۸۹", "ekhamespanah@yahoo.com", 0f, 0f);
    }

    @Test
    public void whenLoaded_find() throws SQLException {
        String json = "[{\"id\":\"5e4fcf14af68ed25d5900fdd\",\"name\":\"کترینگ حس تازگی\",\"location\":{\"x\":-94,\"y\":95},\"logo\":\"https://static.snapp-food.com/media/cache/vendor_logo/uploads/images/vendors/logos/5d25e69346ec0.jpg\",\"menu\":[{\"count\":3,\"oldPrice\":16000,\"name\":\"زرشک پلو با مرغ دودی ویژه\",\"description\":\"زرشک پلو با مرغ دودی ویژه تهیه شده از بهترین مواد اولیه\",\"price\":10270,\"popularity\":0.5,\"image\":\"https://static.snapp-food.com/200x201/cdn/41/46/1/vendor/5def788383435.jpg\"}]},{\"id\":\"5e4fcf14af68ed25d5900f27\",\"name\":\"مرغ بریان و فست فود سفیر\",\"location\":{\"x\":43,\"y\":-40},\"logo\":\"https://static.snapp-food.com/media/cache/vendor_logo/uploads/images/vendors/logos/5da9699131842.jpg\",\"menu\":[{\"count\":1,\"oldPrice\":25000,\"name\":\"مرغ بریان (پرسی)\",\"description\":\"مرغ بریان (پرسی) تهیه شده از بهترین مواد اولیه\",\"price\":11932,\"popularity\":0.9,\"image\":\"https://static.snapp-food.com/200x201/cdn/18/48/5/vendor/5a048cc78a1b2.jpg\"}]}]";
        Loghmeh.getInstance().addFoodPartyRestaurants(json);

        customer.increaseCredit(300000);
        FoodPartyFood foodPartyFood = FoodPartyFoodMapper.getInstance().find("5e4fcf14af68ed25d5900fdd", "زرشک پلو با مرغ دودی ویژه");
        String res;
        res = Loghmeh.getInstance().updateCart(1, foodPartyFood.getRestaurantId(), foodPartyFood.getName(), 2, true, "add");
        assert (res.equals("added"));

        res = Loghmeh.getInstance().updateCart(1, foodPartyFood.getRestaurantId(), foodPartyFood.getName(), 1, true, "remove");
        assert (res.equals("removed"));

        res = Loghmeh.getInstance().finalizeOrder(1);
        assert (res.equals("done"));

        res = Loghmeh.getInstance().updateCart(1, foodPartyFood.getRestaurantId(), foodPartyFood.getName(), 2, true, "add");
        assert (res.equals("added"));

        Order cart = Loghmeh.getInstance().getCustomerById(1).getCart();
        for(OrderItem orderItem : cart.getOrders()) {
            assert (orderItem.equals(foodPartyFood));
        }
    }

}
