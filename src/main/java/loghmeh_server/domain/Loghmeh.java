package loghmeh_server.domain;

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

//Singleton class
public class Loghmeh {
    private static Loghmeh loghmeh = null;

    float nextFoodPartySchedulerFire;

    private Loghmeh() {
        try{
            if(CustomerMapper.getInstance().find(1) == null){
                Customer customer = new Customer(1, "احسان", "خامس‌پناه", "۰۹۱۲۳۴۵۶۷۸۹", "ekhamespanah@yahoo.com", 0f, 0f);
                try {
                    CustomerMapper.getInstance().insert(customer);
                } catch (SQLException ex) {
                    return;
                }
            }
        } catch(SQLException ex) {
            System.out.println("SQL Exception in finding customer.");
        }
    }

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
        System.out.println("is food party?" + isFoodParty);
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
                System.out.println("here1");
                if(customer.finalizeOrder())
                    result = "done";
                else
                    result = "no credit";
            }
            else if(isNewFoodParty(order) && !areFoodPartyFoodsAvailable(order)){
                System.out.println("here2");
                result = "count problem";
            }
            else if(!isNewFoodParty(order) && areFoodPartyFoodsAvailable(order)){
                System.out.println("here3");
                customer.removeFoodPartyFoodsFromCart();
                result = "time problem";
            }
            else{
                System.out.println("here4");
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
            System.out.println("No restaurant");
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
            System.out.println("Del location: " + delivery.getLocation().getX() + ", " + delivery.getLocation().getY());
            System.out.println("Rest location: " + restaurantLocation.getX() + ", " + restaurantLocation.getY());
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

    public void findDelivery(final Order order) {
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
                        Customer customer = CustomerMapper.getInstance().find(1);
                        assignDelivery(order, customer);
                    } catch (SQLException ex) {
                        System.out.println("SQL Esception for getting customer in finding delivery");
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

    public Customer getCustomer(int i) {
        try {
            return CustomerMapper.getInstance().find(i);
        } catch (SQLException ex) {
            return null;
        }
    }

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
