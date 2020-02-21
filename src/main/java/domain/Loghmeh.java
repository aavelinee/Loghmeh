package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import deserializer.*;

//Singleton class
public class Loghmeh {
    private static Loghmeh loghmeh = null;

    private ArrayList<Restaurant> restaurants;
    private ArrayList<Customer> customers;
    private ArrayList<Delivery> deliveries;
    private final Lock deliveriesLock = new ReentrantLock();

    private HashMap<String, String> idToIndex; //from user view id to restaurant id
    private HashMap<String, String> indexToId; //from restaurant id to user view id


    private Loghmeh() {
        restaurants = new ArrayList<Restaurant>();
        customers = new ArrayList<Customer>();
        customers.add(new Customer(1, "Aylin", "Baharan", "+989128248768", "baharan.aylin@ut.ac.ir", 0f, 0f));
        idToIndex = new HashMap<String, String>();
        indexToId = new HashMap<String , String>();
    }

    public static Loghmeh getInstance() {
        if(loghmeh == null){
            loghmeh = new Loghmeh();
        }
        return loghmeh;
    }

    public boolean restaurantAlreadyExists(Restaurant restaurant) {
        for (Restaurant rest : restaurants){
            if (restaurant.equals(rest)) {
                return true;
            }
        }
        return false;
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
        ArrayList<Restaurant> restaurants = deserializer.restaurantDeserializer.deserializeRestaurants(jsonInput);
        for (Restaurant restaurant : restaurants){
            if(restaurantAlreadyExists(restaurant)){
                return "Restaurant Already Exists\n";
            }
            Restaurant otherBranch = sameRestaurant(restaurant);
            if(otherBranch != null){
                restaurant.setMenu(otherBranch.getMenu());
            }
            indexToId.put(Integer.toString(this.restaurants.size()+1), restaurant.getId());
            idToIndex.put(restaurant.getId(), Integer.toString(this.restaurants.size()+1));
            this.restaurants.add(restaurant);
        }
        return "Restaurants Added Successfully";
    }

    public String addDeliveries(String jsonInput) {
        ArrayList<Delivery> deliveries = deserializer.deliveryDeserializer.deserialize(jsonInput);
        deliveriesLock.lock();
        try {
            this.deliveries = deliveries;
        } finally {
            deliveriesLock.unlock();
        }
        return "Deliveries Added Successfully";
    }

    public String addFoodToRestaurant(String jsonInput) {
        String restaurantName = foodDeserializer.getRestaurantNameFromJson(jsonInput);
        Restaurant restaurant = getRestaurantByName(restaurantName);
        if(restaurant == null){
            String result = "There Is No Restaurant Named " + restaurantName;
            return result;
        }
        restaurant.addFoodToMenu(jsonInput);
        return "Food Added Successfully";

    }


    public Restaurant getRestaurant(String restaurantId) {
        Restaurant restaurant = getRestaurantById(restaurantId);
        if(restaurant == null)
            return null;
        return restaurant;
    }

    public Boolean addToCart(String restaurantId, String foodName) {
        Restaurant restaurant = getRestaurantById(restaurantId);
        if(restaurant == null) {
            System.out.println("There Is No Restaurant With ID " + restaurantId);
            return false;
        }
        Food food = restaurant.getFoodByName(foodName);
        if(food == null){
            System.out.println("Food Does Not Exist");
            return false;
        }

        return customers.get(0).addToCart(restaurant, food);
    }

    public Order getCart() {
        return customers.get(0).getCart();
    }

    public String finalizeOrder() {
        return customers.get(0).finalizeOrder();
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

    public Order getLastOrder() {
        return customers.get(0).getLastOrder();
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