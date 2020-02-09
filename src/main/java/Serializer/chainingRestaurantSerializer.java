package Serializer;

import Domain.Food;
import Domain.Order;
import Domain.OrderItem;
import Domain.Restaurant;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class chainingRestaurantSerializer {
    public static String serialize(ArrayList<Restaurant> chainingRestaurants) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Restaurant.class, new restaurantSerializer()).setPrettyPrinting().create();
        return gson.toJson(chainingRestaurants);
    }
//@Override
//public JsonElement serialize(Restaurant restaurant, Type type, JsonSerializationContext jsonSerializationContext) {
//    JsonArray jsonOrders = new JsonArray();
//
//    for(Restaurant rest: order.getOrders()){
//        JsonObject object = new JsonObject();
//        object.addProperty("foodName", orderItem.getFood().getName());
//        object.addProperty("orderCount", orderItem.getOrderCount());
//
//        jsonOrders.add(object);
//    }
//    return jsonOrders;
//}
//
//    public static String orderSerialize(Order order) {
//        Gson gson = new GsonBuilder().registerTypeAdapter(Order.class, new orderSerializer()).setPrettyPrinting().create();
//
//        return gson.toJson(order, Order.class);
//    }
}

