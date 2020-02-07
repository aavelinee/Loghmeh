import java.util.ArrayList;

public class Menu {
    private ArrayList<Food> foods = new ArrayList<Food>();

    public void print() {
        for(Food food: foods){
            food.print();
        }
    }
    public void addFood(Food food) {
        for(Food f: foods){
            if(f == food) {
                System.out.println("Food Already Exists");
                return;
            }
        }
        foods.add(food);
    }
}
