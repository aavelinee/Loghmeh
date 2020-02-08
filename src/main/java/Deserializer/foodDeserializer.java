package Deserializer;

import Domain.Food;
import Domain.Restaurant;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;

public class foodDeserializer {

    public static Food deserialize(String jsonInput) {
        Gson gson = new Gson();
        return gson.fromJson(jsonInput, Food.class);
    }

//    JsonParser parser = new JsonParser();
//    JsonObject jsonObject;
//        try {
//        jsonObject = parser.parse(jsonInput).getAsJsonObject();
//    } catch(
//    JsonSyntaxException e) {
//        e.printStackTrace();
//        JsonReader reader = new JsonReader(new StringReader(jsonInput));
//        reader.setLenient(true);
//        jsonObject = parser.parse(reader).getAsJsonObject();
//    }
//        return jsonObject.get("restaurantName").getAsString();
    public static String getFoodNameFromJson(String jsonInput) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(jsonInput).getAsJsonObject();
        return jsonObject.get("foodName").getAsString();
    }

}

