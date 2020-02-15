package ServerHandler;

import Domain.Loghmeh;
import io.javalin.http.Context;

public class FinalizeOrderPage implements Page{
    public static void handleRequest(Context ctx) {
        Loghmeh loghmeh = Loghmeh.getInstance();
        String result = loghmeh.finalizeOrder();
        String response = render(result);
        ctx.status(200);
        ctx.html(response);
    }

    private static String render(String result) {
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
        return response;
    }
}
