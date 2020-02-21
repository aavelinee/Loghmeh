package serializer;

import domain.Restaurant;
import com.google.gson.*;

import java.util.ArrayList;

public class chainingRestaurantSerializer {
    public static String serialize(ArrayList<Restaurant> chainingRestaurants) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Restaurant.class, new restaurantSerializer()).setPrettyPrinting().create();
        return gson.toJson(chainingRestaurants);
    }

}

