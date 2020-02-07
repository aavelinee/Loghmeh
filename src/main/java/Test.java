import com.google.gson.*;

import java.io.IOException;
import java.lang.reflect.Type;

public class Test {
    public static Restaurant parseJson(String input) throws IOException
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

                return new Restaurant(
                        jsonObject.get("name").getAsString(),
                        jsonObject.get("description").getAsString(),
                        location,
                        menu
                );
            }
        };
        gsonBuilder.registerTypeAdapter(Restaurant.class, deserializer);

        Gson customGson = gsonBuilder.create();
        Restaurant customObject = customGson.fromJson(input, Restaurant.class);
        return customObject;
    }
    public static void main(String[] args){
        String input = "{\"name\": \"Hesturan\", \"description\": \"luxury\", \"location\": {\"x\": 1, \"y\": 3}," +
                "\"menu\": [{\"name\": \"Gheime\", \"description\": \"it’s yummy!\", \"popularity\": 0.8, \"price\":" +
            "20000}, {\"name\": \"Kabab\", \"description\": \"it’s delicious!\", \"popularity\": 0.6, \"price\":" +
                "30000}]}";
        try {
            Restaurant r = parseJson(input);
            r.print();
        }
        catch(IOException e) {
            System.out.println("error");
        }

    }
}

