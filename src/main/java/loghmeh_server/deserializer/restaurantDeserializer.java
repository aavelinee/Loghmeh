package loghmeh_server.deserializer;

import loghmeh_server.domain.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class restaurantDeserializer {

    public static ArrayList<Restaurant> deserializeRestaurants(String jsonInput)
    {
        if(jsonInput.equals("[]"))
            return new ArrayList<Restaurant>();

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

                Restaurant restaurant =  new Restaurant(
                        jsonObject.get("id").getAsString(),
                        jsonObject.get("name").getAsString(),
                        jsonObject.get("logo").getAsString(),
                        location,
                        menu
                );
                menu.setRestaurant(restaurant);

                for(JsonElement foodJsonElement: menuJsonArray){
                    Gson gson = new Gson();
                    Food food = gson.fromJson(foodJsonElement, Food.class);
                    menu.addFood(food);
                }
                menu.setFoodPartyFoods(null);


                return restaurant;
            }
        };
        gsonBuilder.registerTypeAdapter(Restaurant.class, deserializer);
        Type restaurantList = new TypeToken<ArrayList<Restaurant>>(){}.getType();

        Gson customGson = gsonBuilder.create();
        return customGson.fromJson(jsonInput, restaurantList);
    }


    public static ArrayList<Restaurant> deserializeFoodPartyRestaurants(String jsonInput)
    {
        if(jsonInput.equals("[]"))
            return new ArrayList<Restaurant>();

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

                Restaurant restaurant =  new Restaurant(
                        jsonObject.get("id").getAsString(),
                        jsonObject.get("name").getAsString(),
                        jsonObject.get("logo").getAsString(),
                        location,
                        menu
                );
                menu.setRestaurant(restaurant);

                for(JsonElement foodJsonElement: menuJsonArray){
                    Gson gson = new Gson();
                    FoodPartyFood foodPartyFood = gson.fromJson(foodJsonElement, FoodPartyFood.class);
                    menu.addFoodPartyFood(foodPartyFood);
                }
                menu.setFoods(null);

                return restaurant;
            }
        };
        gsonBuilder.registerTypeAdapter(Restaurant.class, deserializer);
        Type restaurantList = new TypeToken<ArrayList<Restaurant>>(){}.getType();

        Gson customGson = gsonBuilder.create();
        return customGson.fromJson(jsonInput, restaurantList);
    }


}
