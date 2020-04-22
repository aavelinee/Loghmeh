package loghmeh_server.serializer;

import loghmeh_server.repository.restaurant.Restaurant;
import com.google.gson.*;
import java.util.ArrayList;

public class restaurantSerializer {

    public static String serialize(ArrayList<Restaurant> chainingRestaurants) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(chainingRestaurants);
    }
}