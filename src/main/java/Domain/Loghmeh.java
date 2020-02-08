package Domain;

import java.util.ArrayList;
import Deserializer.*;
import Serializer.*;


public class Loghmeh {
    private ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();

    public void addRestaurant(String jsonInput) {
        Restaurant restaurant = restaurantDeserializer.deserialize(jsonInput);
        for (Restaurant rest : restaurants)
            if (restaurant == rest) {
                System.out.println("Restaurant Already Exists");
                return;
            }
        //TODO:check wether menu already exists
        restaurants.add(restaurant);
    }

    public void addFoodToRestaurant(String jsonInput) {
        System.out.println(jsonInput);
        String restaurantName = restaurantDeserializer.getRestaurantNameFromJson(jsonInput);
        Restaurant restaurant = getRestaurantByName(restaurantName);
        if(restaurant == null){
            System.out.println("There Is No Restaurant Named " + restaurantName );
            return;
        }
        restaurant.addFoodToMenu(jsonInput);

    }

    public void getRestaurants() {
        for(Restaurant rest: restaurants){
            System.out.println(rest.getName());
        }
    }

    public void getRestaurant(String jsonInput) {
        String restaurantName = restaurantDeserializer.getRestaurantNameFromJson(jsonInput);
        Restaurant restaurant = getRestaurantByName(restaurantName);
        System.out.println(restaurantSerializer.serialize(restaurant));
    }

    public void getFoodFromRestaurant(String jsonInput) {
        String restaurantName = restaurantDeserializer.getRestaurantNameFromJson(jsonInput);
        Restaurant restaurant = getRestaurantByName(restaurantName);
        restaurant.getFood(jsonInput);
    }

    public Restaurant getRestaurantByName(String restaurantName) {
        for(Restaurant rest: restaurants){
            if(rest.getName().equals(restaurantName))
                return rest;
        }
        return null;
    }

}