package Serializer;

import Domain.Food;
import com.google.gson.*;

public class foodSerializer {
    public static String serialize(Food food) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(food);
    }

}
