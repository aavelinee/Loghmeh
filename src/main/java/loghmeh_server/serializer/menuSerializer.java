package loghmeh_server.serializer;

import com.google.gson.*;
import loghmeh_server.repository.food.Food;

import java.util.ArrayList;

public class menuSerializer {

    public static String serialize(ArrayList<Food> foods) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Food.class, new foodSerializer()).setPrettyPrinting().create();
        return gson.toJson(foods);
    }
}
