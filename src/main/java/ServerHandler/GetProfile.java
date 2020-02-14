package ServerHandler;

import Domain.Customer;
import Domain.Location;
import Domain.Loghmeh;
import Domain.Restaurant;
import io.javalin.http.Context;

import java.util.ArrayList;

public class GetProfile implements Page {
    public static void handleRequest(Context ctx) {
        Loghmeh loghmeh = Loghmeh.getInstance();
        String response = render(loghmeh.getCustomer(0));
        ctx.status(200);
        ctx.html(response);
    }

    private static String render(Customer customer) {
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
                        String.format("  <li>id: %d</li>\n" +
                                "        <li>full name: %s %s</li>\n" +
                                "        <li>phone number: %s</li>\n" +
                                "        <li>email: %s</li>\n" +
                                "        <li>credit: %d Toman</li>\n", customer.getCustomerId(), customer.getFirstName(),
                                customer.getLastName(), customer.getPhoneNumber(), customer.getEmail(), customer.getCredit()) +
                        "        <form action=\"/getProfile/increaseCredit\" method=\"POST\">\n" +
                        "            <button type=\"submit\">increase</button>\n" +
                        "            <input type=\"text\" name=\"credit\" value=\"\" />\n" +
                        "        </form>\n" +
                        "    </ul>\n" +
                        "</body>\n" +
                        "</html>";
        return response;
    }

}
