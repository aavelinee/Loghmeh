package ServerHandler;

import Domain.Food;
import Domain.Location;
import Domain.Loghmeh;
import Domain.Restaurant;
import io.javalin.http.Context;


public class GetRestaurantPage implements Page {
    public static void handleRequest(Context ctx) {
        Loghmeh loghmeh = Loghmeh.getInstance();
        String restaurantId = ctx.formParam("restaurantId");
        String foodName = ctx.formParam("foodName");
        String response;
        Restaurant restaurant = loghmeh.getRestaurant(restaurantId);
        if(restaurant == null){
            response = renderRestaurantNotFound(restaurantId, ctx);
        }
        else {
            if (foodName != null) {
                if(loghmeh.addToCart(restaurantId, foodName)){
                    response = render(restaurant, restaurantId, ctx);
                }
                else{
                    response = renderDifferentRestaurants(ctx);
                }
            }
            else{
                response = render(restaurant, restaurantId, ctx);
            }
        }
        ctx.html(response);
    }

    private static String render(Restaurant restaurant, String restaurantId, Context ctx) {
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
                "<body>\n";

                if(restaurant.getLocation().euclideanDistance(new Location(0f, 0f)) <= 170) {
                    response += String.format("    <ul>\n" +
                                    "        <li>id: %s</li>\n" +
                                    "        <li>name: %s</li>\n" +
                                    "        <li>location: (%f, %f)</li>\n" +
                                    "        <li>logo: <img src=%s alt=\"logo\"></li>\n" +
                                    "        <li>menu: \n" +
                                    "        \t<ul>\n" , restaurantId, restaurant.getName(), restaurant.getLocation().getX(),
                            restaurant.getLocation().getY(), restaurant.getLogoURL());
                    for (Food food : restaurant.getMenu().getFoods()) {
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
                                    "</form>\n";
                ctx.status(200);
                }
                else{
                    response += "<h2>This restaurant is farther than the limit</h2>";
                    ctx.status(403);
                }
        response +=
                        "</body>\n" +
                        "</html>";
                return response;
    }

    private static String renderRestaurantNotFound(String restaurantId, Context ctx) {
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
                        String.format("<h2>There is no restaurant found with ID %s</h2>", restaurantId) +
                        "</body>" +
                        "</html>";
        ctx.status(404);

        return response;
    }

    private static String renderDifferentRestaurants(Context ctx){
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
                        "<h2>You can't order from different restaurants</h2>" +
                        "</body>" +
                        "</html>";
        ctx.status(403);
        return response;
    }
}
