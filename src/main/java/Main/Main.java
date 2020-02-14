package Main;

import Domain.Loghmeh;
import ServerHandler.*;
import io.javalin.Javalin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args){

        Loghmeh loghmeh = Loghmeh.getInstance();
        String restaurantsJson = ExternalServices.GetResaurants.getRestaurants("http://138.197.181.131:8080/restaurants");
        if(restaurantsJson != null) {
            System.out.println(loghmeh.addRestaurants(restaurantsJson));

            Javalin loghmehServer = Javalin.create().start(7677);
            loghmehServer.get("/", ctx -> RootPage.handleGetRequest(ctx));
            loghmehServer.get("/getRestaurants", ctx -> GetRestaurantsPage.handleGetRequest(ctx));
            loghmehServer.get("/getProfile", ctx -> GetProfilePage.handleGetRequest(ctx));
            loghmehServer.post("/getProfile/increaseCredit", ctx -> GetProfilePage.handlePostRequest(ctx));

//            loghmehServer.routes(() -> {
//                get("/un-secured",   ctx -> ctx.result("Hello"),   roles(ANYONE));
//                get("/secured",      ctx -> ctx.result("Hello"),   roles(ROLE_ONE));
//            });
            loghmehServer.post("/getRestaurant", ctx -> ServerHandler.GetRestaurantPage.handleRequest(ctx));
//            loghmehServer.post("/getCart", ctx -> ServerHandler.GetAddToCartPage)
        }
        else{
            System.out.println("Unable to get restaurants from API");
        }

    }
}

//addFood {"name": "gheime", "description": "it's yummy!", "popularity": 0.8, "restaurantName": "Hesturan", "price": 20000}

//addRestaurant {"name": "Hesturan", "description": "luxury", "location": {"x": 1, "y": 3}, "menu": [{"name": "Gheime", "description": "it’s yummy!", "popularity": 0.8, "price": 20000}, {"name": "Kabab", "description": "it’s delicious!", "popularity": 0.6, "price": 30000}]}

//getRestaurant {"name": "Hesturan"}

//getRestaurants

//getFood {"foodName": "Kabab", "restaurantName": "Hesturan"}

//addToCart {"foodName": "Kabab", "restaurantName": "Hesturan"}


//    Scanner input = new Scanner(System.in);
//        String inputCommand = input.nextLine();
//        String command;
//        String result;
//        while(!inputCommand.equals("quit")){
//            if(inputCommand.contains(" "))
//                command = inputCommand.substring(0, inputCommand.indexOf(' '));
//            else
//                command = inputCommand;
//
//            if(command.equals("addRestaurant")){
//                String jsonInput = inputCommand.substring(inputCommand.indexOf(' ') + 1);
//                result = loghmeh.addRestaurant(jsonInput);
//            }
//            else if(command.equals("addFood")){
//                String jsonInput = inputCommand.substring(inputCommand.indexOf(' ') + 1);
//                result = loghmeh.addFoodToRestaurant(jsonInput);
//            }
//            else if(command.equals("getRestaurants"))
//                result = loghmeh.getRestaurants();
//            else if(command.equals("getRestaurant"))
//            {
//                String jsonInput = inputCommand.substring(inputCommand.indexOf(' ') + 1);
//                result = loghmeh.getRestaurant(jsonInput);
//            }
//            else if(command.equals("getFood")){
//                String jsonInput = inputCommand.substring(inputCommand.indexOf(' ') + 1);
//                result = loghmeh.getFoodFromRestaurant(jsonInput);
//            }
//            else if(command.equals("addToCart")){
//                String jsonInput = inputCommand.substring(inputCommand.indexOf(' ') + 1);
//                result = loghmeh.addToCart(jsonInput);
//            }
//            else if(command.equals("getCart")){
//                result = loghmeh.getCart();
//            }
//            else if(command.equals("finalizeOrder")){
//                result = loghmeh.finalizeOrder();
//            }
//            else if(command.equals("getRecommendedRestaurants")){
//                result = loghmeh.getRecommendedRestaurants();
//            }
//            else if(command.equals("quit")){
//                result = "Goodbye";
//                break;
//            }
//            else
//                result = "command not found";
//
//            System.out.println(result);
//            inputCommand = input.nextLine();
//        }


