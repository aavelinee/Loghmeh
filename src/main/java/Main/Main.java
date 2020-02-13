package Main;

import Domain.Loghmeh;
import io.javalin.Javalin;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args){
        Javalin app = Javalin.create().start(7676);
        app.get("/addCart", ctx -> ctx.result("Hello World"));
    }
}

//addFood {"name": "gheime", "description": "it's yummy!", "popularity": 0.8, "restaurantName": "Hesturan", "price": 20000}

//addRestaurant {"name": "Hesturan", "description": "luxury", "location": {"x": 1, "y": 3}, "menu": [{"name": "Gheime", "description": "it’s yummy!", "popularity": 0.8, "price": 20000}, {"name": "Kabab", "description": "it’s delicious!", "popularity": 0.6, "price": 30000}]}

//getRestaurant {"name": "Hesturan"}

//getRestaurants

//getFood {"foodName": "Kabab", "restaurantName": "Hesturan"}

//addToCart {"foodName": "Kabab", "restaurantName": "Hesturan"}