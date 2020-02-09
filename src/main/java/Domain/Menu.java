package Domain;

import java.util.ArrayList;

public class Menu {
    private ArrayList<Food> foods = new ArrayList<Food>();
    private transient Restaurant restaurant;

    public void addFood(Food food) {
        for(Food f: foods){
            if(f.equals(food)) {
                System.out.println("Food Already Exists");
                return;
            }
        }
        food.setMenu(this);
        foods.add(food);
    }

    public Food getFood(String foodName) {
        for(Food food: foods){
            if(food.getName().equals(foodName)){
                return food;
            }
        }
        System.out.println("Food Does Not Exist");
        return null;
    }

    public float getFoodsPopulationAverage() {
        float foodsPopulationAverage = 0;
        for(Food food: foods){
            foodsPopulationAverage += food.getPopularity();
        }
        if(foods.size() != 0){
            foodsPopulationAverage /= foods.size();
        }
        return foodsPopulationAverage;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

}
