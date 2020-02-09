package Serializer;

import Domain.Order;
import Domain.Restaurant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class restaurantSerializer {

    public static String serialize(Restaurant restaurant) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(restaurant);
    }
}
