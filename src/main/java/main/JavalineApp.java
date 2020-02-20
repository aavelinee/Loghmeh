package main;

import domain.Loghmeh;
import ServerHandler.*;
import io.javalin.Javalin;

public class JavalineApp {
    private Javalin loghmehServer;

    public JavalineApp(String restaurantsJson) {
        Loghmeh loghmeh = Loghmeh.getInstance();
        if(restaurantsJson != null) {

            System.out.println(loghmeh.addRestaurants(restaurantsJson));
            loghmehServer = Javalin.create().start(7677);

            routeGets();
            routePosts();

        }
        else{
            System.out.println("Unable to get restaurants from API");
        }
    }

    private void routeGets() {
        loghmehServer.get("/", ctx -> RootPage.handleGetRequest(ctx));
        loghmehServer.get("/getRestaurants", ctx -> GetRestaurantsPage.handleGetRequest(ctx));
        loghmehServer.get("/getProfile", ctx -> GetProfilePage.handleGetRequest(ctx));
        loghmehServer.get("/finalize", ctx -> FinalizeOrderPage.handleRequest(ctx));
    }

    private void routePosts() {
        loghmehServer.post("/getProfile/increaseCredit", ctx -> GetProfilePage.handlePostRequest(ctx));
        loghmehServer.post("/getRestaurant", ctx -> ServerHandler.GetRestaurantPage.handleRequest(ctx));
        loghmehServer.post("/getCart", ctx -> GetCartPage.handleRequest(ctx));
    }
}