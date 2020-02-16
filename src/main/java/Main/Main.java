package Main;

public class Main {

    public static void main(String[] args){
        String restaurantsJson = ExternalServices.GetResaurants.getRestaurants("http://138.197.181.131:8080/restaurants");
        JavalineApp javalineApp = new JavalineApp(restaurantsJson);
    }
}


