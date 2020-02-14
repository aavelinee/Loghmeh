package ServerHandler;

import Domain.Location;
import Domain.Loghmeh;
import Domain.Restaurant;
import io.javalin.http.Context;

import java.util.ArrayList;

public class RootPage implements Page {
    public static void handleRequest(Context ctx) {
        String response = render();
        System.out.println(ctx.body());
        ctx.html(response);
    }

    private static String render() {
        String response =
                "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Home</title>\n" +
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
                "    <form action=\"/getRestaurants\" method=\"GET\">\n" +
                "        <button type=\"submit\">Get Restaurants</button>\n" +
                "        <br><br>" +
                "    </form>" +

                "    <form action=\"/getRestaurant\" method=\"POST\">\n" +
                "        <input type=\"text\" {\"id\": id}\" name=\"id\"><br>" +
                "        <button type=\"submit\">Get Restaurant</button>\n" +
                "    </form>" +
//
//                "    <form action=\"/\" method=\"POST\">\n" +
//                "    <button type=\"submit\">finalize</button>\n" +
//                "    </form>" +

                "</body>\n" +
                "</html>";

        return response;
    }
}
