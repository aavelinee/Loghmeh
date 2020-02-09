package Domain;

import java.util.ArrayList;
import java.util.Collections;
import Deserializer.*;
import Serializer.*;


public class Loghmeh {
    private ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
    private ArrayList<Order> orders = new ArrayList<Order>();

    public void addRestaurant(String jsonInput) {
        Restaurant restaurant = restaurantDeserializer.deserialize(jsonInput);
        for (Restaurant rest : restaurants)
            if (restaurant.equals(rest)) {
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
        if(restaurant == null)
            return;
        System.out.println(restaurantSerializer.serialize(restaurant));
    }

    public void addToCart(String jsonInput) {
        String restaurantName = restaurantDeserializer.getRestaurantNameFromJson(jsonInput);
        Restaurant restaurant = getRestaurantByName(restaurantName);
        if(restaurant == null)
            return;
        String foodName = foodDeserializer.getFoodNameFromJson(jsonInput);
        Food food = restaurant.getFoodByName(foodName);
        if(food == null){
            return;
        }

        if(orders.size() == 0 || orders.get(orders.size() - 1).getStatus() != Order.orderStatus.Ordering){
            Order order = new Order(restaurant);
            orders.add(order);
        }
        orders.get(orders.size() - 1).addToCart(restaurant, food);
    }

    public Order getCart() {
        if(orders.size() == 0 || orders.get(orders.size()-1).getStatus() != Order.orderStatus.Ordering){
            System.out.println("There is no order in progress");
            return null;
        }
        String orderJson = orderSerializer.orderSerialize(orders.get(orders.size()-1));
        System.out.println(orderJson);
        return orders.get(orders.size()-1);
    }

    public void finalizeOrder() {
        Order order = getCart();
        if(order != null){
            order.setStatus(Order.orderStatus.Submitted);
            System.out.println("Order was submitted successfully");
        }
    }

    public void getRecommendedRestaurants() {
        ArrayList<RecommendationItem> recommendations = new ArrayList<RecommendationItem>();
        for(Restaurant restaurant: restaurants){
            recommendations.add(new RecommendationItem(restaurant, restaurant.getMenuPopulationAverage()/restaurant.getLocation().euclideanDistance(new Location(0, 0))));
        }
        Collections.sort(recommendations);

    }

    public void getFoodFromRestaurant(String jsonInput) {
        String restaurantName = restaurantDeserializer.getRestaurantNameFromJson(jsonInput);
        Restaurant restaurant = getRestaurantByName(restaurantName);
        if(restaurant == null)
            return;
        restaurant.getFood(jsonInput);
    }

    public Restaurant getRestaurantByName(String restaurantName) {
        for(Restaurant rest: restaurants){
            if(rest.getName().equals(restaurantName))
                return rest;
        }
        System.out.println("There Is No Restaurant Named " + restaurantName );
        return null;
    }

}