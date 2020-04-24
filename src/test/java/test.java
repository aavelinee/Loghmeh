import com.sun.org.apache.xpath.internal.operations.Or;
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
import loghmeh_server.repository.restaurant.Restaurant;
import loghmeh_server.repository.restaurant.RestaurantMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;

public class test {

    private Location test;

    private LocationMapper locationMapper;
    @Before
    public void setUp() throws SQLException {
//        Connection connection = ConnectionPool.getConnection();
//        PreparedStatement pStatement = connection.prepareStatement(
//                \"insert into locations (x, y) values (?, ?)\");
//        pStatement.setFloat(1, 0.0f);
//        pStatement.setFloat(2, 0.0f);
//                \"insert into customers (first_name, last_name, email, phone_number, credit, location_id) values (?, ?, ?, ?, ?, ?)\");
//        pStatement.setString(1, \"احسان\");
//        pStatement.setString(2, \"خامس‌پناه\");
//        pStatement.setString(3, \"ekhamespanah@yahoo.com\");
//        pStatement.setString(4, \"۰۹۱۲۳۴۵۶۷۸۹\");
//        pStatement.setFloat(5, 0.0f);
//        pStatement.setInt(6, 1);
//        pStatement.executeUpdate();
//        pStatement.close();
//        connection.close();
    }

    @Test
    public void whenLoaded_find() throws SQLException {
//        String restaurantsJson = loghmeh_server.external_services.ExternalServices.getFromExtenalAPI("http://138.197.181.131:8080/restaurants");
//        Loghmeh loghmeh = Loghmeh.getInstance();
//        if(restaurantsJson != null){
//            System.out.println(loghmeh.addRestaurants(restaurantsJson));
//        }
//        String foodPartyJson = loghmeh_server.external_services.ExternalServices.getFromExtenalAPI("http://138.197.181.131:8080/foodparty");
        String foodPartyJson = "[{\"id\":\"5e4fcf14af68ed25d5900fbe\",\"name\":\"شمرون کباب (نیاوران)\",\"location\":{\"x\":66,\"y\":3},\"logo\":\"https://static.snapp-food.com/media/cache/vendor_logo/uploads/images/vendors/logos/59912c08ebb96.jpeg\",\"menu\":[{\"count\":3,\"oldPrice\":27000,\"name\":\"خوراک کباب راسته بره\",\"description\":\"خوراک کباب راسته بره تهیه شده از بهترین مواد اولیه\",\"price\":18581,\"popularity\":0.9,\"image\":\"https://static.snapp-food.com/200x201/cdn/16/24/3/vendor/5bf403df6c7ed.jpg\"}]}]";
        System.out.println(foodPartyJson);
        System.out.println(Loghmeh.getInstance().addFoodPartyRestaurants(foodPartyJson));
//
//        ArrayList<Restaurant> restaurants = RestaurantMapper.getInstance().find_restaurants("foodparty");
//        for(Restaurant restaurant : restaurants)
//            System.out.println("restname: " + restaurant.getName());
//        System.out.println(restaurants.size());

//        ArrayList<FoodPartyFood> foodPartyFoods = FoodPartyFoodMapper.getInstance().find_all_foodparty_foods();
//        for(FoodPartyFood foodPartyFood: foodPartyFoods){
//            System.out.println("name: " + foodPartyFood.getName());
//        }
//        System.out.println(foodPartyFoods.size());
//        Restaurant restaurant = RestaurantMapper.getInstance().find("5e4fcf14af68ed25d5900ef1");
//        Menu menu = MenuMapper.getInstance().find(restaurant);
////        FoodPartyFood foodPartyFood = FoodPartyFoodMapper.getInstance().find("5e4fcf14af68ed25d5900ec8", "پلو میگو با کشمش");
////        System.out.println(foodPartyFood.getName() + foodPartyFood.getOldPrice());
//        ArrayList<FoodPartyFood> foodPartyFoods = FoodPartyFoodMapper.getInstance().find_foodparty_foods(menu);
//        for(FoodPartyFood foodPartyFood: foodPartyFoods){
//            System.out.println(FoodPartyFoodMapper.getInstance().is_expired(foodPartyFood));
//        }
        FoodPartyFood foodPartyFood = FoodPartyFoodMapper.getInstance().find("5e4fcf14af68ed25d5900fbe", "خوراک کباب راسته بره");
        System.out.println(FoodPartyFoodMapper.getInstance().is_expired(foodPartyFood));

    }


    @Test
    public void addCustomer() throws SQLException {
        Customer customer = new Customer(1, "Aylin", "Jamali", "09335159217", "aylinjam@yahoo.com", 0f, 0f);
        CustomerMapper.getInstance().insert(customer);
//        Customer cust = CustomerMapper.getInstance().find(1);
//        System.out.println(cust.getLocation().getY());
//        assertEquals(cust, customer);
    }

    @Test
    public void addOrder() throws SQLException {
        Restaurant restaurant = RestaurantMapper.getInstance().find("5e4fcf14af68ed25d5900f40");
        if (restaurant == null) {
            System.out.println("restaurant nulll");
        }
        Customer customer = CustomerMapper.getInstance().find(14);
        if (customer == null) {
            System.out.println("customer nulll");
        }
        Order order = new Order(restaurant, customer);
//        OrderMapper.getInstance().insert(order);
        OrderMapper.getInstance().find(5);
    }

    @Test
    public void addDelivery() throws SQLException {
        Location location = new Location(0f, 6f);
        Delivery delivery = new Delivery("7th delivery", 10f, location);
//        DeliveryMapper.getInstance().insert(delivery);
        ArrayList<Delivery> deliveries;
        deliveries = DeliveryMapper.getInstance().find_deliveries();
        System.out.println(deliveries.size());
    }


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
