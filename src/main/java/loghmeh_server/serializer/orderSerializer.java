package loghmeh_server.serializer;

import loghmeh_server.domain.Order;
import loghmeh_server.domain.OrderItem;
import com.google.gson.*;
import java.lang.reflect.Type;

public class orderSerializer implements JsonSerializer<Order> {

    @Override
    public JsonElement serialize(Order order, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonArray jsonOrders = new JsonArray();

        for(OrderItem orderItem: order.getOrders()){
            JsonObject object = new JsonObject();
            object.addProperty("foodName", orderItem.getFood().getName());
            object.addProperty("orderCount", orderItem.getOrderCount());

            jsonOrders.add(object);
        }
        return jsonOrders;
    }

    public static String orderSerialize(Order order) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Order.class, new orderSerializer()).setPrettyPrinting().create();

        return gson.toJson(order, Order.class);
    }

}
