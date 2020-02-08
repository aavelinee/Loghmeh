package Serializer;

import Domain.Restaurant;
import com.google.gson.Gson;

public class restaurantSerializer {

    public static String serialize(Restaurant restaurant) {
        Gson gson = new Gson();
        return gson.toJson(restaurant);
    }
}
