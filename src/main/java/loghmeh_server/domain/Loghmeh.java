package loghmeh_server.domain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.*;
import loghmeh_server.repository.customer.Customer;
import loghmeh_server.repository.customer.CustomerMapper;
import loghmeh_server.repository.delivery.Delivery;
import loghmeh_server.repository.delivery.DeliveryMapper;
import loghmeh_server.repository.food.Food;
import loghmeh_server.repository.foodparty_food.FoodPartyFood;
import loghmeh_server.repository.foodparty_food.FoodPartyFoodMapper;
import loghmeh_server.repository.location.Location;
import loghmeh_server.repository.order.Order;
import loghmeh_server.repository.order.OrderMapper;
import loghmeh_server.repository.order_item.OrderItem;
import loghmeh_server.repository.restaurant.Restaurant;
import loghmeh_server.repository.restaurant.RestaurantMapper;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

//Singleton class
public class Loghmeh {
    private static Loghmeh loghmeh = null;

    float nextFoodPartySchedulerFire;

    private Loghmeh() {}

    public static Loghmeh getInstance() {
        if(loghmeh == null){
            loghmeh = new Loghmeh();
        }
        return loghmeh;
    }

    public String addRestaurants(String jsonInput) {
        ArrayList<Restaurant> restaurants = loghmeh_server.deserializer.restaurantDeserializer.deserializeRestaurants(jsonInput);
        RestaurantMapper.getInstance().insert_restaurants(restaurants);
        return "Restaurants Added Successfully";
    }

    public String addFoodPartyRestaurants(String jsonInput) {
        ArrayList<Restaurant> restaurants = loghmeh_server.deserializer.restaurantDeserializer.deserializeFoodPartyRestaurants(jsonInput);
        RestaurantMapper.getInstance().insert_foodparty_restaurants(restaurants);
        return "Restaurant With Food Party Added Successfully";
    }

    public ArrayList<Restaurant> getSpecifiedRestaurants(String type, int page) {
        return RestaurantMapper.getInstance().find_restaurants(type, page);
    }

    public ArrayList<Restaurant> getSearchedRestaurants(String restaurantName, String foodName, int page) {
        return RestaurantMapper.getInstance().find_searched_restaurants(restaurantName, foodName, page);
    }

    public ArrayList<FoodPartyFood> getFoodPartyFoods() {
        return FoodPartyFoodMapper.getInstance().find_all_foodparty_foods();
    }

    public FoodPartyFood getFoodPartyFood(String restaurantId, String foodName) {
        return FoodPartyFoodMapper.getInstance().find(restaurantId, foodName);
    }

    public Order getCart(int i) {
        Customer customer = getCustomerById(i);
        if(customer == null)
            return null;
        return customer.getCart();
    }

    public String updateCart(int customerId, String restaurantId, String foodName, int foodCount, boolean isFoodParty, String operation) {
        Restaurant restaurant = RestaurantMapper.getInstance().find(restaurantId);
        if(restaurant == null) {
            System.out.println("There Is No Restaurant With ID " + restaurantId);
            return "not found";
        }

        Customer customer = getCustomerById(customerId);
        if(customer == null){
            System.out.println("There Is No Customer With ID " + customerId);
            return "not found";
        }

        Food food;
        if(isFoodParty){
            food = restaurant.getFoodPartyFoodByName(foodName);
        }
        else{
            food = restaurant.getFoodByName(foodName);
        }
        if(food == null){
            System.out.println("Food Does Not Exist");
            return "not found";
        }


        if(operation.equals("add")){
            if (customer.addToCart(restaurant, food, foodCount)){
                return "added";
            }
            return "different restaurant order";
        }
        else if(operation.equals("remove")) {
            return customer.removeFromCart(restaurantId, foodName);
        }
        return "not found";
    }


    public String finalizeOrder(int customerId) {
        Customer customer = getCustomerById(customerId);
        if(customer == null)
            return "not found";

        Order order = customer.getCart();
        if(order != null){
            String result;
            if(isNewFoodParty(order) && areFoodPartyFoodsAvailable(order)){
                if(customer.finalizeOrder())
                    result = "done";
                else
                    result = "no credit";
            }
            else if(isNewFoodParty(order) && !areFoodPartyFoodsAvailable(order)){
                result = "count problem";
            }
            else if(!isNewFoodParty(order) && areFoodPartyFoodsAvailable(order)){
                customer.removeFoodPartyFoodsFromCart();
                result = "time problem";
            }
            else{
                customer.removeFoodPartyFoodsFromCart();
                result = "both problem";
            }
            return result;
        }
        return "not found";
    }

    public boolean isNewFoodParty(Order order) {

        Restaurant restaurant = RestaurantMapper.getInstance().find(order.getRestaurant().getId());
        if (restaurant == null) {
            return false;
        }
        for (OrderItem orderItem : order.getOrders()) {
            if (orderItem.getFood() instanceof FoodPartyFood) {
                if(FoodPartyFoodMapper.getInstance().is_expired((FoodPartyFood) orderItem.getFood()))
                    return false;
            }
        }
        return true;
    }

    public boolean areFoodPartyFoodsAvailable(Order order) {
        for(OrderItem orderItem: order.getOrders()){
            if(orderItem.getFood() instanceof FoodPartyFood){
                if(!((FoodPartyFood) orderItem.getFood()).checkCount(orderItem.getOrderCount())){
                    return false;
                }
            }
        }
        return true;
    }

    public void assignDelivery(Order order, Customer customer) {
        double deliveryTime = Double.POSITIVE_INFINITY;
        double time, distance;
        Delivery selectedDelivery = null;
        ArrayList<Delivery> deliveries;
        try {
            deliveries = DeliveryMapper.getInstance().find_deliveries();
        } catch (SQLException ex) {
            System.out.println("Exception in find deliveries");
            return;
        }

        for(Delivery delivery: deliveries) {
            Location restaurantLocation = order.getRestaurant().getLocation();
            distance = Math.sqrt(Math.pow(delivery.getLocation().getX() - restaurantLocation.getX(), 2) + Math.pow(delivery.getLocation().getY() - restaurantLocation.getY(), 2));
            distance += Math.sqrt(Math.pow(customer.getLocation().getX() - restaurantLocation.getX(), 2) + Math.pow(customer.getLocation().getY() - restaurantLocation.getY(), 2));
            time = distance / delivery.getVelocity();
            if (time < deliveryTime) {
                deliveryTime = time;
                selectedDelivery = delivery;
            }
        }

        order.setStatus(Order.orderStatus.OnTheWay);
        order.setDelivery(selectedDelivery);
        order.setEstimatedDeliveryTime(deliveryTime);
        order.setDeliveryDate(new Date());
        OrderMapper.getInstance().update_delivery_info(order.getId(), selectedDelivery.getId(), deliveryTime);

        setStatusToDeliveredTimer(order, deliveryTime);
    }

    public void findDelivery(final Order order, final int userId) {
        TimerTask getDeliveries = new TimerTask() {
            public void run() {
                String deliveriesJson = loghmeh_server.external_services.ExternalServices.getFromExtenalAPI("http://138.197.181.131:8080/deliveries");
                ArrayList<Delivery> deliveries = loghmeh_server.deserializer.deliveryDeserializer.deserialize(deliveriesJson);
                for(Delivery delivery: deliveries) {
                    try {
                        DeliveryMapper.getInstance().insert(delivery);
                    } catch (SQLException ex) {
                        return;
                    }
                }
                if(deliveries.size() != 0){
                    try {
                        Customer customer = CustomerMapper.getInstance().find(userId);
                        assignDelivery(order, customer);
                    } catch (SQLException ex) {
                        System.out.println("SQL Exception for getting customer in finding delivery");
                        return;
                    }
                    cancel();
                }
            }
        };
        new Timer().scheduleAtFixedRate(getDeliveries, 0, 1000*3);
    }

    public void setStatusToDeliveredTimer(final Order order, double delay) {
        TimerTask setStatusToDelivered = new TimerTask() {
            public void run() {
                order.setStatus(Order.orderStatus.Delivered);
            }
        };
        Timer timer = new Timer();
        timer.schedule(setStatusToDelivered, (long)delay * 1000);
    }
    public Customer signUp (String name, String lastName, String email, String cellphone, String password) {
//        try{
//            if(CustomerMapper.getInstance().findByEmail(email) != null){
//                System.out.println("invalid email");
//                return null;
//            }
//        } catch (SQLException se) {
//            System.out.println("email check exception");
//        }
        Customer customer = new Customer();
        customer.setFirstName(name);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPhoneNumber(cellphone);
        Location location = new Location(0f, 0f);
        customer.setLocation(location);
        customer.setCredit(0);
        String hashedPassword = getPasswordHash(password);
        if(hashedPassword == null)
            return null;
        customer.setPassword(hashedPassword);
        try {
            CustomerMapper.getInstance().insert(customer);
            customer.setCustomerId(CustomerMapper.getInstance().find(email));
        } catch (SQLException ex) {
            System.out.println("SQL Exception for inserting customer");
            return null;
        }
        return customer;
    }

    public Customer authenticate(String email, String password) {
        String hashedPassword = getPasswordHash(password);
        if(hashedPassword == null)
            return null;
        return CustomerMapper.getInstance().find(email, hashedPassword);
    }

    public String getPasswordHash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest).toUpperCase();

        } catch (NoSuchAlgorithmException ex) {
            System.out.println("NO such algorithm exception");
            return null;
        }
    }
//        public String signInWithGoogle(String token) {
//        GoogleValidator.getInstance().setIdTokenString(token);
//        String email = GoogleValidator.getInstance().verify();
//        if(email == "email not verified") {
//            return
//        } else if(email == "invalid ID") {
//            return
//        } else if(email == "error") {
//            return
//        }
//        Customer customer = new Customer();
//        try {
//            customer = CustomerMapper.getInstance().findByEmail(email);
//        } catch (SQLException ex) {
//            System.out.println("SQL Exception");
//            System.out.println(ex);
//            return "error";
//        }
//        if(customer == null) {
//            return
//        }
//        //return jwt
//    }
//
//    public String signIn(String email, String password) {
//
//    }


    public Customer getCustomerById(int id) {
        try {
            return CustomerMapper.getInstance().find(id);
        } catch (SQLException ex) {
            return null;
        }
    }

    public void setNextFoodPartySchedulerFire(float nextFoodPartySchedulerFire) {
        this.nextFoodPartySchedulerFire = nextFoodPartySchedulerFire;
    }

    public float getNextFoodPartySchedulerFire() {
        return nextFoodPartySchedulerFire;
    }

}
