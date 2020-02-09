package Deserializer;

import Domain.*;
import com.google.gson.*;

import java.lang.reflect.Type;

public class restaurantDeserializer {
    public static Restaurant deserialize(String jsonInput)
    {
        GsonBuilder gsonBuilder = new GsonBuilder();

        JsonDeserializer<Restaurant> deserializer = new JsonDeserializer<Restaurant>() {
            @Override
            public Restaurant deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                JsonObject jsonObject = json.getAsJsonObject();
                JsonObject locationJsonObject = jsonObject.get("location").getAsJsonObject();
                JsonArray menuJsonArray = jsonObject.get("menu").getAsJsonArray();
                

                Location location = new Location(
                        locationJsonObject.get("x").getAsFloat(),
                        locationJsonObject.get("y").getAsFloat()
                );

                Menu menu = new Menu();
                for(JsonElement foodJsonElement: menuJsonArray){
                    Gson gson = new Gson();
                    Food food = gson.fromJson(foodJsonElement, Food.class);
                    menu.addFood(food);
                }

                Restaurant restaurant =  new Restaurant(
                        jsonObject.get("name").getAsString(),
                        jsonObject.get("description").getAsString(),
                        location,
                        menu
                );
                menu.setRestaurant(restaurant);
                return restaurant;
            }
        };
        gsonBuilder.registerTypeAdapter(Restaurant.class, deserializer);

        Gson customGson = gsonBuilder.create();
        return customGson.fromJson(jsonInput, Restaurant.class);
    }

    public static String getRestaurantNameFromJson(String jsonInput) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(jsonInput).getAsJsonObject();
        return jsonObject.get("name").getAsString();
    }


}
