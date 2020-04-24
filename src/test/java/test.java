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
        Order order = OrderMapper.getInstance().find_cart(customer);
        System.out.println(order.getId());
        System.out.println(order.getRestaurant().getName());
        System.out.println(order.getOrders().size());
        for(OrderItem orderItem: order.getOrders()) {
            System.out.println(orderItem.getFood().getName());
        }
        System.out.println("***************************");
        ArrayList<Order> orders = OrderMapper.getInstance().find_orders(customer);
        System.out.println(orders.size());
        for(Order ord: orders) {
            System.out.println(ord.getId());
            System.out.println(ord.getRestaurant().getName());
            System.out.println(ord.getOrders().size());
            for(OrderItem orderItem: ord.getOrders()) {
                System.out.println(orderItem.getFood().getName());
            }
        }
    }


//    @Test
//    public void addCustomer() throws SQLException {
//        Customer customer = new Customer(1, "Aylin", "Jamali", "09335159217", "aylinjam@yahoo.com", 0f, 0f);
//        CustomerMapper.getInstance().insert(customer);
////        Customer cust = CustomerMapper.getInstance().find(1);
////        System.out.println(cust.getLocation().getY());
////        assertEquals(cust, customer);
//    }
//
//    @Test
//    public void addOrder() throws SQLException {
//        Restaurant restaurant = RestaurantMapper.getInstance().find("5e4fcf14af68ed25d5900f40");
//        if (restaurant == null) {
//            System.out.println("restaurant nulll");
//        }
//        Customer customer = CustomerMapper.getInstance().find(14);
//        if (customer == null) {
//            System.out.println("customer nulll");
//        }
//        Order order = new Order(restaurant, customer);
////        OrderMapper.getInstance().insert(order);
//        OrderMapper.getInstance().find(5);
//    }
//
//    @Test
//    public void addDelivery() throws SQLException {
//        Location location = new Location(0f, 6f);
//        Delivery delivery = new Delivery("7th delivery", 10f, location);
////        DeliveryMapper.getInstance().insert(delivery);
//        ArrayList<Delivery> deliveries;
//        deliveries = DeliveryMapper.getInstance().find_deliveries();
//        System.out.println(deliveries.size());
//    }


//    @After
//    public void terminate() throws SQLException {
//        try (Connection con = ConnectionPool.getConnection();
//             PreparedStatement ps = con.prepareStatement(
//                     "delete from (?)"
//             )
//        ) {
//            try {
//                ps.setString(1, "restaurants");
//                ps.executeUpdate();
//                ps.setString(1, "locations");
//                ps.executeUpdate();
//            } catch (SQLException ex) {
//                System.out.println("error in Test.delete query.");
//                throw ex;
//            }
//        }
//    }

}
