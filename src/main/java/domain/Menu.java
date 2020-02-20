package domain;

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

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public boolean equals(Object object) {
        if(this == object)
            return true;

        Menu menu;
        if(object instanceof Menu)
            menu  = (Menu)object;
        else
            return false;

        if(menu.foods.size() != foods.size())
            return false;
        for(int i = 0; i < foods.size(); i++){
            if(!foods.get(i).equals(menu.foods.get(i)))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 1234;
    }

}
