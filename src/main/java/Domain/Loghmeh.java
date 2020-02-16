package Domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import Deserializer.*;
import Serializer.*;

import javax.swing.*;

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
        customers.add(new Customer(1, "Aylin", "Baharan", "+989128248768", "baharan.aylin@ut.ac.ir"));
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
//                restaurant.setMenu(rest.getMenu());
                return restaurant;
            }
        }
        return null;
    }

    public String addRestaurants(String jsonInput) {
        ArrayList<Restaurant> restaurants = Deserializer.restaurantDeserializer.deserializeRestaurants(jsonInput);
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
        return "Restaurant Added Successfully";
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

//    public String getRestaurants() {
//        String result = "";
//        for(Restaurant rest: restaurants){
//            result += (rest.getName() + "\n");
//        }
//        if(result.contains("\n")){
//            result = result.substring(0, result.length() - 1);
//        }
//        else{
//            result = "There is no restaurant in the system";
//        }
//        return result;
//    }

//    public String getRestaurant(String jsonInput) {
//        String restaurantName = restaurantDeserializer.getRestaurantNameFromJson(jsonInput);
////        Restaurant restaurant = getRestaurantByName(restaurantName);
//        ArrayList<Restaurant> chainingRestaurants = getChainingRestaurantsByName(restaurantName);
////        if(restaurant == null)
//        if(chainingRestaurants.size() == 0)
//            return ("There Is No Restaurant Named " + restaurantName);
//        return restaurantSerializer.serialize(chainingRestaurants);
//    }


    public Restaurant getRestaurant(String restaurantId) {
//        String restaurantName = restaurantDeserializer.getRestaurantNameFromJson(jsonInput);
        Restaurant restaurant = getRestaurantById(restaurantId);
//        ArrayList<Restaurant> chainingRestaurants = getChainingRestaurantsById(restaurantId);
        if(restaurant == null)
//        if(chainingRestaurants.size() == 0)
            return null;
        return restaurant;
//        return restaurantSerializer.serialize(chainingRestaurants);


    }

//    public String addToCart(String jsonInput) {
//        String restaurantName = foodDeserializer.getRestaurantNameFromJson(jsonInput);
//        Restaurant restaurant = getRestaurantByName(restaurantName);
//        if(restaurant == null)
//            return ("There Is No Restaurant Named " + restaurantName );
//        String foodName = foodDeserializer.getFoodNameFromJson(jsonInput);
//        Food food = restaurant.getFoodByName(foodName);
//        if(food == null){
//            return "Food Does Not Exist";
//        }
//
//        return customers.get(0).addToCart(restaurant, food);
//
//    }

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