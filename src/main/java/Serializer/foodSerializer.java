package Serializer;

import Domain.Food;
import Domain.Order;
import Domain.Restaurant;
import com.google.gson.*;

import java.lang.reflect.Type;

public class foodSerializer {
    public static String serialize(Food food) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(food);
    }
//    public static String serialize(Food food) {
//        GsonBuilder gsonBuilder = new GsonBuilder();
//
//        JsonSerializer<Food> serializer = JsonSerializer<Food>serializer = new JsonSerializer<Food>() {
//            @Override
//            public JsonElement serialize(Food src, Type typeOfSrc, JsonSerializationContext context) {
//                JsonObject jsonMerchant = new JsonObject();
//
//                jsonMerchant.addProperty("Id", src.getId());
//
//                return jsonMerchant;
//            }
//        };
//        gsonBuilder.registerTypeAdapter(Food.class, serializer);
//
//        Gson customGson = gsonBuilder.create();
//        String customJSON = customGson.toJson(subscription);
//    }
}
