package loghmeh_server.deserializer;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import loghmeh_server.repository.delivery.Delivery;
import loghmeh_server.repository.location.Location;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class deliveryDeserializer {
    public static ArrayList<Delivery> deserialize(String jsonInput) {
        if(jsonInput.equals("[]")){
            return new ArrayList<Delivery>();
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        JsonDeserializer<Delivery> deserializer = new JsonDeserializer<Delivery>() {
            @Override
            public Delivery deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                JsonObject jsonObject = json.getAsJsonObject();
                JsonObject locationJsonObject = jsonObject.get("location").getAsJsonObject();

                Location location = new Location(
                        locationJsonObject.get("x").getAsFloat(),
                        locationJsonObject.get("y").getAsFloat()
                );

                Delivery delivery = new Delivery(
                        jsonObject.get("id").getAsString(),
                        jsonObject.get("velocity").getAsFloat(),
                        location
                );
                return delivery;
            }
        };

        gsonBuilder.registerTypeAdapter(Delivery.class, deserializer);
        Type deliveryList = new TypeToken<ArrayList<Delivery>>(){}.getType();
        Gson customGson = gsonBuilder.create();
        return customGson.fromJson(jsonInput, deliveryList);
    }
}
