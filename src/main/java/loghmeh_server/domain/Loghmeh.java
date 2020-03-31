package loghmeh_server.domain;

import java.util.*;
import loghmeh_server.deserializer.*;

//Singleton class
public class Loghmeh {
    private static Loghmeh loghmeh = null;

    private ArrayList<Restaurant> restaurants;
    private ArrayList<Customer> customers;

    private HashMap<String, String> idToIndex; //from user view id to restaurant id
    private HashMap<String, String> indexToId; //from restaurant id to user view id



    private Loghmeh() {
        restaurants = new ArrayList<Restaurant>();
        customers = new ArrayList<Customer>();
        customers.add(new Customer(1, "احسان", "خامس‌پناه", "۰۹۱۲۳۴۵۶۷۸۹", "ekhamespanah@yahoo.com", 0f, 0f));
        idToIndex = new HashMap<String, String>();
        indexToId = new HashMap<String , String>();
    }

    public static Loghmeh getInstance() {
        if(loghmeh == null){
            loghmeh = new Loghmeh();
        }
        return loghmeh;
    }

    public Restaurant restaurantAlreadyExists(Restaurant restaurant) {
        for (Restaurant rest : restaurants){
            if (restaurant.equals(rest)) {
                return rest;
            }
        }
        return null;
    }

    public Restaurant sameRestaurant(Restaurant restaurant) {
        for (Restaurant rest : restaurants){
            if(rest.getName().equals(restaurant.getName()) && !rest.getLocation().equals(restaurant.getLocation()) &&
                    rest.getMenu().equals(restaurant.getMenu())){//chain restaurant
                return restaurant;
            }
        }
        return null;
    }

    public String addRestaurants(String jsonInput) {
        ArrayList<Restaurant> restaurants = loghmeh_server.deserializer.restaurantDeserializer.deserializeRestaurants(jsonInput, "normal");
        for (Restaurant restaurant : restaurants){
            if(restaurantAlreadyExists(restaurant) != null){
                return "Restaurant Already Exists\n";
            }
            Restaurant otherBranch = sameRestaurant(restaurant);
            if(otherBranch != null){
                restaurant.setMenu(otherBranch.getMenu());
            }
            addRestaurantInfo(restaurant);
        }
        return "Restaurants Added Successfully";
    }

    public String addFoodPartyRestaurants(String jsonInput) {
        ArrayList<Restaurant> restaurants = loghmeh_server.deserializer.restaurantDeserializer.deserializeRestaurants(jsonInput, "foodparty");
        for (Restaurant restaurant: restaurants){
            Restaurant existedRestaurant = restaurantAlreadyExists(restaurant);
            if(existedRestaurant != null) {
                existedRestaurant.getMenu().setFoodPartyFoods(restaurant.getMenu().getFoodPartyFoods());
                return "FoodParty Menu Added To Existed Restaurant Successfully";
            }
            Restaurant otherBranch = sameRestaurant(restaurant);
            if(otherBranch != null){
                restaurant.setMenu(otherBranch.getMenu());
            }
            addRestaurantInfo(restaurant);

        }
        return "Restaurant With Food Party Added Successfully";
    }

    public void addRestaurantInfo(Restaurant restaurant) {
        indexToId.put(Integer.toString(this.restaurants.size()+1), restaurant.getId());
        idToIndex.put(restaurant.getId(), Integer.toString(this.restaurants.size()+1));
        this.restaurants.add(restaurant);
    }

    public void deleteFoodParty() {
        for(Restaurant restaurant: this.restaurants){
            if(restaurant.getMenu().getFoodPartyFoods() != null && restaurant.getMenu().getFoods() == null){
                restaurants.remove(restaurant);
            }
        }
    }


    public Restaurant getRestaurant(String restaurantId) {
        Restaurant restaurant = getRestaurantById(restaurantId);
        if(restaurant == null)
            return null;
        return restaurant;
    }

    public String addToCart(String restaurantId, String foodName, String isFoodParty) {
        Restaurant restaurant = getRestaurantById(restaurantId);
        if(restaurant == null) {
            System.out.println("There Is No Restaurant With ID " + restaurantId);
            return "no restaurant";
        }

        if(isFoodParty.equals("true")){
            FoodPartyFood foodPartyFood = restaurant.getFoodPartyFoodByName(foodName);
            if(foodPartyFood == null){
                System.out.println("Food Party Food Does Not Exist");
                return "no food";
            }
            if (customers.get(0).addToCart(restaurant, foodPartyFood)){
                return "added";
            }
            return "not added";
        }
        else{
            Food food = restaurant.getFoodByName(foodName);
            if(food == null){
                System.out.println("Food Does Not Exist");
                return "no food";
            }
            if (customers.get(0).addToCart(restaurant, food)){
                return "added";
            }
            return "not added";
        }

    }

    public Order getCart(int i) {
        return customers.get(i).getCart();
    }

    public String finalizeOrder() {
        Order order = customers.get(0).getCart();
        if(order != null){
            String result = "done";
            if(areFoodPartyFoodsAvailable(order) && isNewFoodParty(order)){
                customers.get(0).finalizeOrder();
//                result = "done";
            }
            else if(!areFoodPartyFoodsAvailable(order) && isNewFoodParty(order)){
                customers.get(0).removeCart();
                result = "count problem";
            }
            else if(areFoodPartyFoodsAvailable(order) && !isNewFoodParty(order)){
                customers.get(0).removeCart();
                result = "time problem";
            }
            else{
                customers.get(0).removeCart();
                result = "both problem";
            }
            return result;
        }
        return "no order";
    }

    public boolean isRestaurantExists(Restaurant restaurant) {
        for(Restaurant rest: this.restaurants) {
            if(restaurant.equals(rest)){
                return true;
            }
        }
        return false;
    }

    public boolean isNewFoodParty(Order order) {
        for (OrderItem orderItem : order.getOrders()) {
            if (orderItem.getFood() instanceof FoodPartyFood) {
                if (!isRestaurantExists(orderItem.getFood().getMenu().getRestaurant())) {
                    return false;
                }

                if (orderItem.getFood().getMenu().getFoodPartyFoods() == null) {
                    return false;
                }
                for (FoodPartyFood foodPartyFood : orderItem.getFood().getMenu().getFoodPartyFoods()) {
                    if (((FoodPartyFood) orderItem.getFood()).equals(foodPartyFood)){
                       return true;
                    }
                }
                return false;
            }
        }
        return true;
    }

    public boolean areFoodPartyFoodsAvailable(Order order) {
        for(OrderItem orderItem: order.getOrders()){
            if(orderItem.getFood() instanceof FoodPartyFood){
                if(!((FoodPartyFood) orderItem.getFood()).decreaseCount(orderItem.getOrderCount())){
                    return false;
                }
            }
        }
        return true;
    }

    public String getRecommendedRestaurants() {
        ArrayList<RecommendationItem> recommendations = new ArrayList<RecommendationItem>();
        for(Restaurant restaurant: restaurants){
            recommendations.add(new RecommendationItem(restaurant, restaurant.getMenuPopulationAverage() / (restaurant.getLocation().euclideanDistance(new Location(.0f, .0f))) ));
        }
        Collections.sort(recommendations, new Comparator<RecommendationItem>(){
            public int compare(RecommendationItem recommendationItem1, RecommendationItem recommendationItem2){
                if(recommendationItem1.getRatingForUser() == recommendationItem2.getRatingForUser())
                    return 0;
                return recommendationItem1.getRatingForUser() > recommendationItem2.getRatingForUser() ? -1 : 1;
            }
        });
        String result = "";
        for(int i = 0; i < 3; i++){
            if(i < recommendations.size()){
                result += (recommendations.get(i).getRestaurant().getName() + "\n");
            }
        }
        if(result.contains("\n")){
            result = result.substring(0, result.length() - 1);
        }
        return result;

    }

    public void assignDelivery(Order order, Customer customer, ArrayList<Delivery> deliveries) {
        double deliveryTime = Double.POSITIVE_INFINITY;
        double time, distance;
        Delivery selectedDelivery = null;
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
        order.setDelivery(selectedDelivery);
        order.setStatus(Order.orderStatus.OnTheWay);
        order.setEstimatedDeliveryTime(deliveryTime);
        order.setDeliveryDate(new Date());
        setStatusToDeliveredTimer(order, deliveryTime);
        System.out.println("estimation: " + deliveryTime);
        System.out.println(order.getDeliveryDate());
    }

    public void findDelivery(final Order order) {
        TimerTask getDeliveries = new TimerTask() {
            public void run() {
                String deliveriesJson = loghmeh_server.external_services.ExternalServices.getFromExtenalAPI("http://138.197.181.131:8080/deliveries");
                ArrayList<Delivery> deliveries = loghmeh_server.deserializer.deliveryDeserializer.deserialize(deliveriesJson);
                if(deliveries.size() != 0){
                    assignDelivery(order, customers.get(0), deliveries);
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

    public String convertMillisToDateFormat(long durationInMillis) {
        long millis = durationInMillis % 1000;
        long second = (durationInMillis / 1000) % 60;
        long minute = (durationInMillis / (1000 * 60)) % 60;
        long hour = (durationInMillis / (1000 * 60 * 60)) % 24;

        return String.format("%02d h %02d min %02d.%d sec", hour, minute, second, millis);
    }

    public String getFoodFromRestaurant(String jsonInput) {
        String restaurantName = foodDeserializer.getRestaurantNameFromJson(jsonInput);
        Restaurant restaurant = getRestaurantByName(restaurantName);
        if(restaurant == null)
            return ("There Is No Restaurant Named " + restaurantName);
        return restaurant.getFood(jsonInput);
    }

    public Restaurant getRestaurantByName(String restaurantName) {
        for(Restaurant rest: restaurants){
            if(rest.getName().equals(restaurantName))
                return rest;
        }
        return null;
    }

    public Restaurant getRestaurantById(String restaurantId) {
        if(restaurantId == null)
            return null;
        for(Restaurant rest: restaurants){
            if(rest.getId().equals(indexToId.get(restaurantId)))
                return rest;
        }
        return null;
    }

    public String getIndexFromRestaurantId(String restaurantId) {
        return idToIndex.get(restaurantId);
    }

    public ArrayList<Restaurant> getChainingRestaurantsByName(String restaurantName) {
        ArrayList<Restaurant> chainingRestaurants = new ArrayList<Restaurant>();

        for(Restaurant rest: restaurants){
            if(rest.getName().equals(restaurantName))
                chainingRestaurants.add(rest);
        }
        return chainingRestaurants;
    }

    public ArrayList<Restaurant> getChainingRestaurantsById(String restaurantId) {
        ArrayList<Restaurant> chainingRestaurants = new ArrayList<Restaurant>();

        for(Restaurant rest: restaurants){
            if(rest.getId().equals(restaurantId))
                chainingRestaurants.add(rest);
        }
        return chainingRestaurants;
    }


    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    public Customer getCustomer(int i) {
        if(i >= this.customers.size())
            return null;
        return customers.get(i);
    }

}