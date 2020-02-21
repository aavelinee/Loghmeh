package serializer;

import domain.Restaurant;
import com.google.gson.*;
import java.util.ArrayList;

public class restaurantSerializer {

    public static String serialize(ArrayList<Restaurant> chainingRestaurants) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(chainingRestaurants);
    }
}