
import java.util.ArrayList;

public class Loghmeh {
    private ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();

    public void addRestaurant(Restaurant restaurant) {
        for (Restaurant rest : restaurants)
            if (restaurant == rest) {
                System.out.println("Restaurant Already Exists");
                return;
            }
        //TODO:check wether menu already exists

        restaurants.add(restaurant);
    }
//    public static void main2(String[] args){
//
//    }
}