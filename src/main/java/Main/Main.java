package Main;

import Domain.Loghmeh;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args){
//        String input = "{\"name\": \"gheime\", \"description\": \"it's yummy!\", \"popularity\": 0.8, \"restaurantName\": \"Hesturan\", \"price\": 20000}";
        Loghmeh loghmeh = new Loghmeh();
        Scanner input = new Scanner(System.in);
        String inputCommand = input.nextLine();
        String command;
        while(!inputCommand.equals("quit")){
            if(inputCommand.contains(" "))
                command = inputCommand.substring(0, inputCommand.indexOf(' '));
            else
                command = inputCommand;

            if(command.equals("addRestaurant")){
                String jsonInput = inputCommand.substring(inputCommand.indexOf(' ') + 1);
                loghmeh.addRestaurant(jsonInput);
            }
            else if(command.equals("addFood")){
                String jsonInput = inputCommand.substring(inputCommand.indexOf(' ') + 1);
                loghmeh.addFoodToRestaurant(jsonInput);
            }
            else if(command.equals("getRestaurants"))
                loghmeh.getRestaurants();
            else if(command.equals("getRestaurant"))
            {
                String jsonInput = inputCommand.substring(inputCommand.indexOf(' ') + 1);
                loghmeh.getRestaurant(jsonInput);
            }
            else if(command.equals("getFood")){
                String jsonInput = inputCommand.substring(inputCommand.indexOf(' ') + 1);
                loghmeh.getFoodFromRestaurant(jsonInput);
            }
            else if(command.equals("quit")){
                System.out.println("Goodbye");
                break;
            }
            else
                System.out.println("command not found");

            inputCommand = input.nextLine();
        }
    }
}

//addFood {"name": "gheime", "description": "it's yummy!", "popularity": 0.8, "restaurantName": "Hesturan", "price": 20000}

//addRestaurant {"name": "Hesturan", "description": "luxury", "location": {"x": 1, "y": 3}, "menu": [{"name": "Gheime", "description": "it’s yummy!", "popularity": 0.8, "price": 20000}, {"name": "Kabab", "description": "it’s delicious!", "popularity": 0.6, "price": 30000}]}

//getRestaurant {"restaurantName": "Hesturan"}

//getRestaurants

//getFood {"foodName": "Kabab", "restaurantName": "Hesturan"}
