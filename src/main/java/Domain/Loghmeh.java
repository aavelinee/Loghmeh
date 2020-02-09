package Domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Deserializer.*;
import Serializer.*;


public class Loghmeh {
    private ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
    private ArrayList<Customer> customers = new ArrayList<Customer>();

    public Loghmeh() {
        customers.add(new Customer());
    }

    public String addRestaurant(String jsonInput) {
        Restaurant restaurant = restaurantDeserializer.deserialize(jsonInput);
        for (Restaurant rest : restaurants)
            if (restaurant.equals(rest)) {
                return "Restaurant Already Exists";
            }
        //TODO:check wether menu already exists
        restaurants.add(restaurant);
        return "Restaurant Added Successfully";
    }

    public String addFoodToRestaurant(String jsonInput) {
        String restaurantName = restaurantDeserializer.getRestaurantNameFromJson(jsonInput);
        Restaurant restaurant = getRestaurantByName(restaurantName);
        if(restaurant == null){
            String result = "There Is No Restaurant Named " + restaurantName;
            return result;
        }
        restaurant.addFoodToMenu(jsonInput);
        return "Food Added Successfully";

    }

    public String getRestaurants() {
        String result = "";
        for(Restaurant rest: restaurants){
            result += (rest.getName() + "\n");
        }
        if(result.contains("\n")){
            result = result.substring(0, result.length() - 1);
        }
        else{
            result = "There is no restaurant in the system";
        }
        return result;
    }

    public String getRestaurant(String jsonInput) {
        String restaurantName = restaurantDeserializer.getRestaurantNameFromJson(jsonInput);
        Restaurant restaurant = getRestaurantByName(restaurantName);
        if(restaurant == null)
            return ("There Is No Restaurant Named " + restaurantName);
        return restaurantSerializer.serialize(restaurant);
    }

    public String addToCart(String jsonInput) {
        String restaurantName = restaurantDeserializer.getRestaurantNameFromJson(jsonInput);
        Restaurant restaurant = getRestaurantByName(restaurantName);
        if(restaurant == null)
            return ("There Is No Restaurant Named " + restaurantName );
        String foodName = foodDeserializer.getFoodNameFromJson(jsonInput);
        Food food = restaurant.getFoodByName(foodName);
        if(food == null){
            return "Food Does Not Exist";
        }

        return customers.get(0).addToCart(restaurant, food);

    }

    public String getCart() {
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
        String restaurantName = restaurantDeserializer.getRestaurantNameFromJson(jsonInput);
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

    public Order getLastOrder() {
        return customers.get(0).getLastOrder();
    }
}