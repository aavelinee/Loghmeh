package ServerHandler;

import Domain.Food;
import Domain.Loghmeh;
import Domain.Restaurant;
import io.javalin.http.Context;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;
import java.util.List;

public class GetRestaurantPage implements Page {
    public static void handleRequest(Context ctx) {
        Loghmeh loghmeh = Loghmeh.getInstance();
        String restaurantId = ctx.formParam("restaurantId");
        String foodName = ctx.formParam("foodName");
        Restaurant restaurant = loghmeh.getRestaurant(restaurantId);
        if(foodName != null){
            loghmeh.addToCart(restaurantId, foodName);
        }
        String response = render(restaurant, restaurantId);
        ctx.html(response);
    }

    private static String render(Restaurant restaurant, String restaurantId) {
        String response =
                "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Restaurant</title>\n" +
                "    <style>\n" +
                "        img {\n" +
                "        \twidth: 50px;\n" +
                "        \theight: 50px;\n" +
                "        }\n" +
                "        li {\n" +
                "            display: flex;\n" +
                "            flex-direction: row;\n" +
                "        \tpadding: 0 0 5px;\n" +
                "        }\n" +
                "        div, form {\n" +
                "            padding: 0 5px\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                String.format("    <ul>\n" +
                "        <li>id: %s</li>\n" +
                "        <li>name: %s</li>\n" +
                "        <li>location: (%f, %f)</li>\n" +
                "        <li>logo: <img src=%s alt=\"logo\"></li>\n" +
                "        <li>menu: \n" +
                "        \t<ul>\n" , restaurantId, restaurant.getName(), restaurant.getLocation().getX(),
                                        restaurant.getLocation().getY(), restaurant.getLogoURL());
                for(Food food: restaurant.getMenu().getFoods()){
                    response +=
                            String.format(
                            "        \t\t<li>\n" +
                            "                    <img src=%s alt=\"logo\">\n" +
                            "                    <div>%s</div>\n" +
                            "                    <div>%f Toman</div>\n" +
                            "                    <form action=\"/getRestaurant\" method=\"POST\">\n" +
                            "                        <input type=\"hidden\" name=\"restaurantId\" value=%s>" +
                            "                        <button type=\"submit\" name=\"foodName\" value=\"%s\" >addToCart</button>\n" +
                            "                    </form>\n" +
                            "                </li>\n", food.getImage(), food.getName(), food.getPrice(), restaurantId, food.getName());

                }
                response +=
                        "        \t</ul>\n" +
                        "        </li>\n" +
                        "    </ul>\n" +
                        "<form action=\"/getCart\" method=\"POST\">\n" +
                        "    <input type=\"hidden\" name=\"restaurantId\" value=%s>" +
                        "    <button type=\"submit\" name=\"foodName\" value=\"%s\" >get Cart</button>\n" +
                        "</form>\n" +
                        "</body>\n" +
                        "</html>";
                return response;
    }
}
