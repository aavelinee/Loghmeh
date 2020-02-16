package Serializer;

import Domain.*;
import com.google.gson.*;

import java.util.ArrayList;

public class menuSerializer {

    public static String serialize(ArrayList<Food> foods) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Food.class, new foodSerializer()).setPrettyPrinting().create();
        return gson.toJson(foods);
    }
}
