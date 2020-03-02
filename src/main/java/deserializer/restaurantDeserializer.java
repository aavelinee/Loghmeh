package deserializer;

import domain.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class restaurantDeserializer {
    public static ArrayList<Restaurant> deserializeRestaurants(String jsonInput, final String restaurantType)
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
                for(JsonElement foodJsonElement: menuJsonArray){
                    Gson gson = new Gson();
                    if(restaurantType.equals("normal")){
                        Food food = gson.fromJson(foodJsonElement, Food.class);
                        menu.addFood(food);
                    }
                    else if(restaurantType.equals("foodparty")){
                        FoodPartyFood foodPartyFood = gson.fromJson(foodJsonElement, FoodPartyFood.class);
                        menu.addFoodPartyFood(foodPartyFood);
                    }
                }
                if(restaurantType.equals("normal")){
                    menu.setFoodPartyFoods(null);
                }
                else if(restaurantType.equals("foodparty")){
                    menu.setFoods(null);
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

}
