package ServerHandler;

import Domain.Location;
import Domain.Loghmeh;
import Domain.Restaurant;
import io.javalin.http.Context;

import java.util.ArrayList;

public class GetRestaurantsPage implements Page {
    public static void handleRequest(Context ctx) {
        Loghmeh loghmeh = Loghmeh.getInstance();
        String response = render(loghmeh.getRestaurants());
        ctx.html(response);
    }

    private static String render(ArrayList<Restaurant>restaurants) {
        String response =
                "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>Restaurants</title>\n" +
                        "    <style>\n" +
                        "        table {\n" +
                        "            text-align: center;\n" +
                        "            margin: auto;\n" +
                        "        }\n" +
                        "        th, td {\n" +
                        "            padding: 5px;\n" +
                        "            text-align: center;\n" +
                        "        }\n" +
                        "        .logo{\n" +
                        "            width: 100px;\n" +
                        "            height: 100px;\n" +
                        "        }\n" +
                        "    </style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <table>\n" +
                        "<tr>\n" +
                        "            <th>id</th>\n" +
                        "            <th>logo</th>\n" +
                        "            <th>name</th>\n" +
                        "        </tr>\n" ;

        int numOfRestaurantsInZone = 0;
        for(Restaurant restaurant: restaurants) {
            if(restaurant.getLocation().euclideanDistance(new Location(0f, 0f)) <= 170){
                numOfRestaurantsInZone ++;
                response +=
                        String.format(
                                        "        <tr>\n" +
                                        "            <td>%d</td>\n" +
                                        "            <td><img class=\"logo\" src=%s alt=\"logo\"></td>\n" +
                                        "            <td>%s</td>\n" +
                                        "        </tr>\n"
                                        , numOfRestaurantsInZone, restaurant.getLogoURL(), restaurant.getName());


            }
        }
        response +=
                    "    </table>\n" +
                    "</body>\n" +
                    "</html>";

        return response;
    }

}
