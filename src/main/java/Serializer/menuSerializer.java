package Serializer;

import Domain.*;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class menuSerializer {
//    @Override
//    public JsonElement serialize(Menu menu, Type type, JsonSerializationContext jsonSerializationContext) {
//        JsonArray jsonMenu = new JsonArray();
//
//        for(Food food: menu.getFoods()){
//            JsonObject object = new JsonObject();
//            object.add(foodSerializer.serialize(food));
//
//            jsonOrders.add(object);
//        }
//        return jsonOrders;
//    }
//
//    public static String orderSerialize(Order order) {
//        Gson gson = new GsonBuilder().registerTypeAdapter(Order.class, new orderSerializer()).setPrettyPrinting().create();
//
//        return gson.toJson(order, Order.class);
//    }
    public static String serialize(ArrayList<Food> foods) {
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Gson gson = new GsonBuilder().registerTypeAdapter(Food.class, new foodSerializer()).setPrettyPrinting().create();
        return gson.toJson(foods);
    }
}
