package serializer;

import domain.Location;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class locationSerializer {
    public static String serialize(Location location) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(location);
    }
}
