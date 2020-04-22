package loghmeh_server.domain;

import java.util.ArrayList;

public class Menu {
    private ArrayList<Food> foods = new ArrayList<Food>();
    private ArrayList<FoodPartyFood> foodPartyFoods = new ArrayList<FoodPartyFood>();
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

    public void addFoodPartyFood(FoodPartyFood foodPartyFood) {
        for(FoodPartyFood f: foodPartyFoods){
            if(f.equals(foodPartyFood)) {
                System.out.println("Food Already Exists");
                return;
            }
        }
        foodPartyFood.setMenu(this);
        foodPartyFoods.add(foodPartyFood);
    }

    public Food getFood(String foodName) {
        for(Food food: foods){
            if(food.getName().equals(foodName)){
                return food;
            }
        }
        return null;
    }

    public FoodPartyFood getFoodPartyFood(String foodName) {
        for(FoodPartyFood foodPartyFood: foodPartyFoods){
            if(foodPartyFood.getName().equals(foodName)){
                return foodPartyFood;
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


    public ArrayList<FoodPartyFood> getFoodPartyFoods() {
        return foodPartyFoods;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public void setFoodPartyFoods(ArrayList<FoodPartyFood> foodPartyFoods) {
        this.foodPartyFoods = foodPartyFoods;
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
