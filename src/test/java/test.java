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
        String jsonInput = "[{\"id\":\"5e4fcf14af68ed25d5900f40\",\"name\":\"فست فود پیانو (شهرآرا)\",\"location\":{\"x\":-55,\"y\":-62},\"logo\":\"https://static.snapp-food.com/media/cache/vendor_logo/uploads/images/vendors/logos/5db1a7b9a3360.jpg\",\"menu\":[{\"name\":\"پیتزا سامورایی یکم پیانو (نیم متر)\",\"description\":\"پیتزا سامورایی یکم پیانو (نیم متر) تهیه شده از بهترین مواد اولیه\",\"price\":19000,\"popularity\":0.9,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca42fa552f7.jpg\"},{\"name\":\"پیتزا سامورایی دوم پیانو (نیم متر)\",\"description\":\"پیتزا سامورایی دوم پیانو (نیم متر) تهیه شده از بهترین مواد اولیه\",\"price\":25000,\"popularity\":0.4,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca43015e925.jpg\"},{\"name\":\"پیتزا غول افکن (کیک پیتزا)\",\"description\":\"پیتزا غول افکن (کیک پیتزا) تهیه شده از بهترین مواد اولیه\",\"price\":20000,\"popularity\":0.8,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca41bec3485.jpg\"},{\"name\":\"پیتزا فیل افکن (کیک پیتزا)\",\"description\":\"پیتزا فیل افکن (کیک پیتزا) تهیه شده از بهترین مواد اولیه\",\"price\":32000,\"popularity\":0.5,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/vendor/5e1cc38c4e34d.jpg\"},{\"name\":\"پیتزا بمب افکن (کیک پیتزا)\",\"description\":\"پیتزا بمب افکن (کیک پیتزا) تهیه شده از بهترین مواد اولیه\",\"price\":26000,\"popularity\":0.6,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca413729eb6.jpg\"},{\"name\":\"پیتزا هیولای پیانو (آمریکایی)\",\"description\":\"پیتزا هیولای پیانو (آمریکایی) تهیه شده از بهترین مواد اولیه\",\"price\":15000,\"popularity\":0.1,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/vendor/5c5c6e6941ca8.jpg\"},{\"name\":\"پیتزا چهار مزه (آمریکایی)\",\"description\":\"پیتزا چهار مزه (آمریکایی) تهیه شده از بهترین مواد اولیه\",\"price\":31000,\"popularity\":0.3,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/vendor/5e1cc48eb3c38.jpg\"},{\"name\":\"پیتزا بیف باربیکیو (24 سانتی‌متری)\",\"description\":\"پیتزا بیف باربیکیو (24 سانتی‌متری) تهیه شده از بهترین مواد اولیه\",\"price\":33000,\"popularity\":0.7,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/vendor/5e1cc4ee456d0.jpg\"},{\"name\":\"پیتزا بیف باربیکیو (35 سانتی‌متری)\",\"description\":\"پیتزا بیف باربیکیو (35 سانتی‌متری) تهیه شده از بهترین مواد اولیه\",\"price\":40000,\"popularity\":0.2,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/vendor/5e1cc5125870b.jpg\"},{\"name\":\"پیتزا چیکن باربیکیو (24 سانتی‌متری)\",\"description\":\"پیتزا چیکن باربیکیو (24 سانتی‌متری) تهیه شده از بهترین مواد اولیه\",\"price\":26000,\"popularity\":0.3,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/vendor/5e1cc76232384.jpg\"},{\"name\":\"پیتزا چیکن باربیکیو (35 سانتی‌متری)\",\"description\":\"پیتزا چیکن باربیکیو (35 سانتی‌متری) تهیه شده از بهترین مواد اولیه\",\"price\":16000,\"popularity\":0.7,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/vendor/5e1cc7b396f2d.jpg\"},{\"name\":\"پیتزا سالامی (24 سانتی‌متری)\",\"description\":\"پیتزا سالامی (24 سانتی‌متری) تهیه شده از بهترین مواد اولیه\",\"price\":30000,\"popularity\":0.0,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/vendor/5e1cc83faf2cf.jpg\"},{\"name\":\"پیتزا سالامی (35 سانتی‌متری)\",\"description\":\"پیتزا سالامی (35 سانتی‌متری) تهیه شده از بهترین مواد اولیه\",\"price\":38000,\"popularity\":0.4,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/vendor/5e1cc8564f454.jpg\"},{\"name\":\"پیتزا VIP (24 سانتی‌متری)\",\"description\":\"پیتزا VIP (24 سانتی‌متری) تهیه شده از بهترین مواد اولیه\",\"price\":32000,\"popularity\":0.3,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca445fc6fea.jpg\"},{\"name\":\"پیتزا VIP (35 سانتی‌متری)\",\"description\":\"پیتزا VIP (35 سانتی‌متری) تهیه شده از بهترین مواد اولیه\",\"price\":11000,\"popularity\":0.7,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca446c88f32.jpg\"},{\"name\":\"پیتزا استیک (24 سانتی‌متری)\",\"description\":\"پیتزا استیک (24 سانتی‌متری) تهیه شده از بهترین مواد اولیه\",\"price\":18000,\"popularity\":0.7,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca437f7a24f.jpg\"},{\"name\":\"پیتزا استیک (35 سانتی‌متری)\",\"description\":\"پیتزا استیک (35 سانتی‌متری) تهیه شده از بهترین مواد اولیه\",\"price\":36000,\"popularity\":0.6,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca43a03254e.jpg\"},{\"name\":\"پیتزا مرغ و قارچ (24 سانتی‌متری)\",\"description\":\"پیتزا مرغ و قارچ (24 سانتی‌متری) تهیه شده از بهترین مواد اولیه\",\"price\":22000,\"popularity\":0.7,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca414052068.jpg\"},{\"name\":\"پیتزا مرغ و قارچ (35 سانتی‌متری)\",\"description\":\"پیتزا مرغ و قارچ (35 سانتی‌متری) تهیه شده از بهترین مواد اولیه\",\"price\":27000,\"popularity\":0.4,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca414d6998d.jpg\"},{\"name\":\"پیتزا هات داگ (24 سانتی‌متری)\",\"description\":\"پیتزا هات داگ (24 سانتی‌متری) تهیه شده از بهترین مواد اولیه\",\"price\":36000,\"popularity\":0.0,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/vendor/5e2472c606b4e.jpg\"},{\"name\":\"پیتزا هات داگ (35 سانتی‌متری)\",\"description\":\"پیتزا هات داگ (35 سانتی‌متری) تهیه شده از بهترین مواد اولیه\",\"price\":36000,\"popularity\":0.9,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/vendor/5e2472cdc9ea5.jpg\"},{\"name\":\"پیتزا چیلی (24 سانتی‌متری)\",\"description\":\"پیتزا چیلی (24 سانتی‌متری) تهیه شده از بهترین مواد اولیه\",\"price\":25000,\"popularity\":0.7,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca4163ba527.jpg\"},{\"name\":\"پیتزا چیلی (35 سانتی‌متری)\",\"description\":\"پیتزا چیلی (35 سانتی‌متری) تهیه شده از بهترین مواد اولیه\",\"price\":23000,\"popularity\":0.9,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca415280c18.jpg\"},{\"name\":\"پیتزا مخصوص (24 سانتی‌متری)\",\"description\":\"پیتزا مخصوص (24 سانتی‌متری) تهیه شده از بهترین مواد اولیه\",\"price\":40000,\"popularity\":0.3,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca4266f0a58.jpg\"},{\"name\":\"پیتزا مخصوص (35 سانتی‌متری)\",\"description\":\"پیتزا مخصوص (35 سانتی‌متری) تهیه شده از بهترین مواد اولیه\",\"price\":13000,\"popularity\":0.9,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca425654f32.jpg\"},{\"name\":\"پیتزا پپرونی (24 سانتی‌متری)\",\"description\":\"پیتزا پپرونی (24 سانتی‌متری) تهیه شده از بهترین مواد اولیه\",\"price\":21000,\"popularity\":0.0,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca42cfccb43.jpg\"},{\"name\":\"پیتزا پپرونی (35 سانتی‌متری)\",\"description\":\"پیتزا پپرونی (35 سانتی‌متری) تهیه شده از بهترین مواد اولیه\",\"price\":10000,\"popularity\":0.1,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca42c1b3cc9.jpg\"},{\"name\":\"پیتزا بندری (24 سانتی‌متری)\",\"description\":\"پیتزا بندری (24 سانتی‌متری) تهیه شده از بهترین مواد اولیه\",\"price\":31000,\"popularity\":1.0,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca40ea61543.jpg\"},{\"name\":\"پیتزا بندری (35 سانتی‌متری)\",\"description\":\"پیتزا بندری (35 سانتی‌متری) تهیه شده از بهترین مواد اولیه\",\"price\":22000,\"popularity\":0.8,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca40f175ca1.jpg\"},{\"name\":\"پیتزا سبزیجات (آمریکایی)\",\"description\":\"پیتزا سبزیجات (آمریکایی) تهیه شده از بهترین مواد اولیه\",\"price\":15000,\"popularity\":0.1,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca42d9a31ec.jpg\"},{\"name\":\"پیتزا اسطوره یک\",\"description\":\"پیتزا اسطوره یک تهیه شده از بهترین مواد اولیه\",\"price\":24000,\"popularity\":0.8,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca42905e721.jpg\"},{\"name\":\"چوریتسو برگر\",\"description\":\"چوریتسو برگر تهیه شده از بهترین مواد اولیه\",\"price\":30000,\"popularity\":0.5,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca4183c4c99.jpg\"},{\"name\":\"همبرگر ذغالی ویژه\",\"description\":\"همبرگر ذغالی ویژه تهیه شده از بهترین مواد اولیه\",\"price\":14000,\"popularity\":0.8,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca4108495fa.jpg\"},{\"name\":\"چیز برگر فرانسوی\",\"description\":\"چیز برگر فرانسوی تهیه شده از بهترین مواد اولیه\",\"price\":12000,\"popularity\":0.4,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/vendor/5c5f189a10296.jpg\"},{\"name\":\"برگر ویژه پیانو\",\"description\":\"برگر ویژه پیانو تهیه شده از بهترین مواد اولیه\",\"price\":17000,\"popularity\":0.6,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca444d22716.jpg\"},{\"name\":\"چیلی برگر\",\"description\":\"چیلی برگر تهیه شده از بهترین مواد اولیه\",\"price\":13000,\"popularity\":0.7,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca4178335e2.jpg\"},{\"name\":\"برگر بمب مرد افکن پیانو\",\"description\":\"برگر بمب مرد افکن پیانو تهیه شده از بهترین مواد اولیه\",\"price\":38000,\"popularity\":0.7,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca4275c429b.jpg\"},{\"name\":\"ساندویچ TNT یک تا دو نفره\",\"description\":\"ساندویچ TNT یک تا دو نفره تهیه شده از بهترین مواد اولیه\",\"price\":22000,\"popularity\":0.4,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/vendor/5c5c56d5082c8.jpg\"},{\"name\":\"ساندویچ بمب سوخاری مولوتف یک تا دو نفره\",\"description\":\"ساندویچ بمب سوخاری مولوتف یک تا دو نفره تهیه شده از بهترین مواد اولیه\",\"price\":14000,\"popularity\":0.5,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca436070afb.jpg\"},{\"name\":\"ساندویچ بمب سوخاری مولوتف سه تا چهار نفره\",\"description\":\"ساندویچ بمب سوخاری مولوتف سه تا چهار نفره تهیه شده از بهترین مواد اولیه\",\"price\":29000,\"popularity\":0.8,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca4368d0796.jpg\"},{\"name\":\"ساندویچ بمب بچه کراکف یک تا دو نفره\",\"description\":\"ساندویچ بمب بچه کراکف یک تا دو نفره تهیه شده از بهترین مواد اولیه\",\"price\":30000,\"popularity\":0.3,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca40a6bf466.jpg\"},{\"name\":\"ساندویچ بمب بچه کراکف سه تا چهار نفره\",\"description\":\"ساندویچ بمب بچه کراکف سه تا چهار نفره تهیه شده از بهترین مواد اولیه\",\"price\":24000,\"popularity\":0.5,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca40af06704.jpg\"},{\"name\":\"ساندویچ بمب ژامبون تنوری یک تا دو نفره\",\"description\":\"ساندویچ بمب ژامبون تنوری یک تا دو نفره تهیه شده از بهترین مواد اولیه\",\"price\":32000,\"popularity\":0.7,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/vendor/5c5d8df3237c3.jpg\"},{\"name\":\"ساندویچ بمب موسیو بندری یک تا دو نفره\",\"description\":\"ساندویچ بمب موسیو بندری یک تا دو نفره تهیه شده از بهترین مواد اولیه\",\"price\":19000,\"popularity\":0.6,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca40c89331d.jpg\"},{\"name\":\"ساندویچ بمب موسیو بندری سه تا چهار نفره\",\"description\":\"ساندویچ بمب موسیو بندری سه تا چهار نفره تهیه شده از بهترین مواد اولیه\",\"price\":25000,\"popularity\":0.3,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca40cf71596.jpg\"},{\"name\":\"ساندویچ بمب هسته ای پیانو (6 تا 8 نفره)\",\"description\":\"ساندویچ بمب هسته ای پیانو (6 تا 8 نفره) تهیه شده از بهترین مواد اولیه\",\"price\":32000,\"popularity\":0.3,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca41cca529c.jpg\"},{\"name\":\"ساندویچ تی ان تی پلاس یک تا دو نفره\",\"description\":\"ساندویچ تی ان تی پلاس یک تا دو نفره تهیه شده از بهترین مواد اولیه\",\"price\":23000,\"popularity\":0.5,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca43c71e510.jpg\"},{\"name\":\"ساندویچ تی ان تی پلاس سه تا چهار نفره\",\"description\":\"ساندویچ تی ان تی پلاس سه تا چهار نفره تهیه شده از بهترین مواد اولیه\",\"price\":21000,\"popularity\":0.9,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca442867f4b.jpg\"},{\"name\":\"ساندویچ هات داگ ویژه   \",\"description\":\"ساندویچ هات داگ ویژه    تهیه شده از بهترین مواد اولیه\",\"price\":40000,\"popularity\":0.5,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca41d86935d.jpg\"},{\"name\":\"فیله مرغ ویژه     \",\"description\":\"فیله مرغ ویژه      تهیه شده از بهترین مواد اولیه\",\"price\":12000,\"popularity\":0.2,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca41b3e187e.jpg\"},{\"name\":\"ساندویچ ژامبون مرغ (سرد)\",\"description\":\"ساندویچ ژامبون مرغ (سرد) تهیه شده از بهترین مواد اولیه\",\"price\":21000,\"popularity\":0.1,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca44824b5e2.jpg\"},{\"name\":\"ساندویچ ژامبون مخلوط (سرد)\",\"description\":\"ساندویچ ژامبون مخلوط (سرد) تهیه شده از بهترین مواد اولیه\",\"price\":29000,\"popularity\":0.4,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca447d19654.jpg\"},{\"name\":\"ساندویچ ژامبون گوشت (سرد)\",\"description\":\"ساندویچ ژامبون گوشت (سرد) تهیه شده از بهترین مواد اولیه\",\"price\":11000,\"popularity\":0.0,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca4477b2a4c.jpg\"},{\"name\":\"پاستا پنیر سوسیس یک نفره\",\"description\":\"پاستا پنیر سوسیس یک نفره تهیه شده از بهترین مواد اولیه\",\"price\":25000,\"popularity\":1.0,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca42b2cfc51.jpg\"},{\"name\":\"پاستا پنیر سوسیس 2 نفره\",\"description\":\"پاستا پنیر سوسیس 2 نفره تهیه شده از بهترین مواد اولیه\",\"price\":24000,\"popularity\":0.7,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca42b8d972c.jpg\"},{\"name\":\"پاستا پنیر فیله یک نفره\",\"description\":\"پاستا پنیر فیله یک نفره تهیه شده از بهترین مواد اولیه\",\"price\":33000,\"popularity\":0.2,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca42a7a7b47.jpg\"},{\"name\":\"پاستا پنیر فیله 2 نفره\",\"description\":\"پاستا پنیر فیله 2 نفره تهیه شده از بهترین مواد اولیه\",\"price\":14000,\"popularity\":0.3,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca42ad021cd.jpg\"},{\"name\":\"فیله استریپس\",\"description\":\"فیله استریپس تهیه شده از بهترین مواد اولیه\",\"price\":24000,\"popularity\":0.1,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/vendor/5c5f1a1d10eb9.jpg\"},{\"name\":\"بشقاب سوخاری\",\"description\":\"بشقاب سوخاری تهیه شده از بهترین مواد اولیه\",\"price\":36000,\"popularity\":0.9,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/vendor/5c5f1a3408051.jpg\"},{\"name\":\"جعبه شادی یک\",\"description\":\"جعبه شادی یک تهیه شده از بهترین مواد اولیه\",\"price\":35000,\"popularity\":0.1,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca424275977.jpg\"},{\"name\":\"جعبه شادی دو\",\"description\":\"جعبه شادی دو تهیه شده از بهترین مواد اولیه\",\"price\":35000,\"popularity\":0.9,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca424c2f5bb.jpg\"},{\"name\":\"نان سیر\",\"description\":\"نان سیر تهیه شده از بهترین مواد اولیه\",\"price\":28000,\"popularity\":0.7,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/vendor/5e4a6d92cb9c8.jpeg\"},{\"name\":\"قارچ سوخاری\",\"description\":\"قارچ سوخاری تهیه شده از بهترین مواد اولیه\",\"price\":12000,\"popularity\":0.8,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/vendor/5c65aca9af13b.jpg\"},{\"name\":\"سیب زمینی سرخ شده\",\"description\":\"سیب زمینی سرخ شده تهیه شده از بهترین مواد اولیه\",\"price\":17000,\"popularity\":0.0,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/vendor/5c5f1a4998a0e.jpg\"},{\"name\":\"سیب زمینی پنیری کوچک\",\"description\":\"سیب زمینی پنیری کوچک تهیه شده از بهترین مواد اولیه\",\"price\":11000,\"popularity\":0.4,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca433953c7b.jpg\"},{\"name\":\"سیب زمینی پنیری متوسط\",\"description\":\"سیب زمینی پنیری متوسط تهیه شده از بهترین مواد اولیه\",\"price\":14000,\"popularity\":0.1,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca4324b7155.jpg\"},{\"name\":\"سیب زمینی ویژه کوچک\",\"description\":\"سیب زمینی ویژه کوچک تهیه شده از بهترین مواد اولیه\",\"price\":19000,\"popularity\":0.7,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca434265ac6.jpg\"},{\"name\":\"سیب زمینی ویژه متوسط\",\"description\":\"سیب زمینی ویژه متوسط تهیه شده از بهترین مواد اولیه\",\"price\":38000,\"popularity\":0.3,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca432eb64eb.jpg\"},{\"name\":\"سینی سیب زمینی پیانو\",\"description\":\"سینی سیب زمینی پیانو تهیه شده از بهترین مواد اولیه\",\"price\":14000,\"popularity\":0.5,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca43150ea47.jpg\"},{\"name\":\"سالاد کلم\",\"description\":\"سالاد کلم تهیه شده از بهترین مواد اولیه\",\"price\":24000,\"popularity\":0.6,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/vendor/5c6155badd4be.jpg\"},{\"name\":\"سالاد فصل\",\"description\":\"سالاد فصل تهیه شده از بهترین مواد اولیه\",\"price\":26000,\"popularity\":0.5,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/vendor/5c6155dc1a892.jpg\"},{\"name\":\"سالاد سزار\",\"description\":\"سالاد سزار تهیه شده از بهترین مواد اولیه\",\"price\":17000,\"popularity\":0.0,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca42e760320.jpg\"},{\"name\":\"سس پنیر چدار (اضافه)\",\"description\":\"سس پنیر چدار (اضافه) تهیه شده از بهترین مواد اولیه\",\"price\":29000,\"popularity\":0.9,\"image\":\"https://static.snapp-food.com/200x201/cdn/35/24/3/product_image/vendor/5dca4372e7de1.jpg\"}]}]";
        ArrayList<Restaurant> restaurants = loghmeh_server.deserializer.restaurantDeserializer.deserializeRestaurants(jsonInput);
        for(Restaurant restaurant: restaurants) {
            RestaurantMapper.getInstance().insert(restaurant);
        }
        Restaurant restaurant = RestaurantMapper.getInstance().find("5e4fcf14af68ed25d5900eca");
        Customer customer = CustomerMapper.getInstance().find(1);
        Order order = new Order(3, restaurant, customer);
        OrderMapper.getInstance().insert(order);
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
