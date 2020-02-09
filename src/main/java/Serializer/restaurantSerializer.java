package Serializer;

import Domain.Order;
import Domain.Restaurant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class restaurantSerializer {

//    public static String serialize(Restaurant restaurant) {
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        return gson.toJson(restaurant);
//    }

    public static String serialize(ArrayList<Restaurant> chainingRestaurants) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(chainingRestaurants);
    }
}
