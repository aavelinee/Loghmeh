package ServerHandler;

import Domain.Loghmeh;
import Domain.OrderItem;
import io.javalin.http.Context;

public class FinalizeOrderPage implements Page{
    public static void handleRequest(Context ctx) {
        Loghmeh loghmeh = Loghmeh.getInstance();
        String response;
        if(loghmeh.getCart() == null){
            response = renderEmptyCart(ctx);
        }
        else{
            if(getPrice() > loghmeh.getCustomer(0).getCredit()){
                response = renderNotEnoughMoney(ctx);
            }
            else{

                String result = loghmeh.finalizeOrder();
                response = render(result, ctx);
            }
        }
        ctx.html(response);
    }

    private static String render(String result, Context ctx) {
        String response =
                "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>User</title>\n" +
                "    <style>\n" +
                "        li {\n" +
                "        \tpadding: 5px\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <ul>\n" +
                result +
                "    </ul>\n" +
                "</body>\n" +
                "</html>";
        ctx.status(200);
        return response;
    }
    private static String renderEmptyCart(Context ctx) {
        String response =
                "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>User</title>\n" +
                        "    <style>\n" +
                        "        li {\n" +
                        "        \tpadding: 5px\n" +
                        "        }\n" +
                        "    </style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<h2>Your cart is empty</h2>" +
                        "</body>\n" +
                        "</html>";
        ctx.status(400);
        return response;
    }

    private static String renderNotEnoughMoney(Context ctx) {
        String response =
                "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>User</title>\n" +
                        "    <style>\n" +
                        "        li {\n" +
                        "        \tpadding: 5px\n" +
                        "        }\n" +
                        "    </style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<h2>There is not enough money in your credit</h2>" +
                        "</body>\n" +
                        "</html>";
        ctx.status(400);
        return response;
    }

    private static float getPrice() {
        float price = 0;
        for(OrderItem orderItem: Loghmeh.getInstance().getCart().getOrders()){
            price += orderItem.getFood().getPrice() * orderItem.getOrderCount();
        }
        return price;
    }
}
