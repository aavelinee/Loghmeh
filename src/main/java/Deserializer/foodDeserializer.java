package Deserializer;

import Domain.Food;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class foodDeserializer {

    public static Food deserialize(String jsonInput) {
        Gson gson = new Gson();
        return gson.fromJson(jsonInput, Food.class);
    }

    public static String getFoodNameFromJson(String jsonInput) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(jsonInput).getAsJsonObject();
        return jsonObject.get("foodName").getAsString();
    }

    public static String getRestaurantNameFromJson(String jsonInput) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(jsonInput).getAsJsonObject();
        return jsonObject.get("restaurantName").getAsString();
    }

}

