package ServerHandler;

import Domain.*;
import com.sun.org.apache.xpath.internal.operations.Or;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class GetCartPage {
    public static void handleRequest(Context ctx) {
        Loghmeh loghmeh = Loghmeh.getInstance();
        Order cart = loghmeh.getCart();
        String response;

        if(cart == null) {
                response = renderUnsuccessfulScenario();
                ctx.status(200);
                ctx.html(response);
        }
        else {
            response = render(cart);
            ctx.status(200);
            ctx.html(response);
        }

    }

    private static String render(Order cart) {
        String response =
                "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "<title>User</title>\n" +
                "<style>\n" +
                "        li, div, form {\n" +
                "        \tpadding: 5px\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                String.format("<div>%s</div>\n", cart.getRestaurant().getName()) +
                "<ul>\n";

                for(OrderItem orderItem: cart.getOrders()){
                    response +=
                            String.format(
                                    "    <li>%s:%d</li>\n", orderItem.getFood().getName(), orderItem.getOrderCount());

                }

                response +=
                    "</ul>\n" +
                    "<form action=\"/finalize\" method=\"GET\">\n" +
                    "    <button type=\"submit\">finalize</button>\n" +
                    "</form>\n" +
                    "</body>\n" +
                    "</html>";
                return response;
    }
    private static String renderUnsuccessfulScenario() {
        String response =
                "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "<title>User</title>\n" +
                "<style>\n" +
                "        li, div, form {\n" +
                "        \tpadding: 5px\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h2>There is nothing in your cart</h2>" +
                "</body>";
        return response;
    }
}
