package Serializer;

import Domain.Food;
import Domain.Restaurant;
import com.google.gson.Gson;

public class foodSerializer {
    public static String serialize(Food food) {
        Gson gson = new Gson();
        return gson.toJson(food);
    }
}
