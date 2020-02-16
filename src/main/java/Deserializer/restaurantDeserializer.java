package Deserializer;

import Domain.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

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
                        jsonObject.get("id").getAsString(),
                        jsonObject.get("name").getAsString(),
                        jsonObject.get("logo").getAsString(),
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

    public static ArrayList<Restaurant> deserializeRestaurants(String jsonInput)
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
                        jsonObject.get("id").getAsString(),
                        jsonObject.get("name").getAsString(),
                        jsonObject.get("logo").getAsString(),
                        location,
                        menu
                );
                menu.setRestaurant(restaurant);
                return restaurant;
            }
        };
        gsonBuilder.registerTypeAdapter(Restaurant.class, deserializer);
        Type restaurantList = new TypeToken<ArrayList<Restaurant>>(){}.getType();

        Gson customGson = gsonBuilder.create();
        return customGson.fromJson(jsonInput, restaurantList);
    }

    public static String getRestaurantNameFromJson(String jsonInput) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(jsonInput).getAsJsonObject();
        return jsonObject.get("name").getAsString();
    }


}
