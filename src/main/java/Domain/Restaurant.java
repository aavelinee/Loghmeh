package Domain;

import Deserializer.*;
import Serializer.*;

public class Restaurant {
    private String id;
    private String name;
    private String logo;
    private String description;
    private Location location;
    private Menu menu;
//    private ArrayList<Domain.Food> menu = new ArrayList<Domain.Food>();

    public Restaurant(String id, String name, String logoURL, Location location, Menu menu) {
        this.id = id;
        this.name = name;
        this.logo = logoURL;
//        this.description = description;
        this.location = location;
        this.menu = menu;
    }

    public void addFoodToMenu(String jsonInput) {
        Food food = foodDeserializer.deserialize(jsonInput);
        menu.addFood(food);
    }

    public String getFood(String jsonInput) {
        String foodName = foodDeserializer.getFoodNameFromJson(jsonInput);
        Food food = menu.getFood(foodName);
        if(food == null)
            return "Food Does Not Exist";
        return foodSerializer.serialize(food);

    }

    public Food getFoodByName(String foodName) {
        return menu.getFood(foodName);
    }

    public double getMenuPopulationAverage(){
        return menu.getFoodsPopulationAverage();
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public Menu getMenu() {
        return menu;
    }

    public String getDescription() {
        return description;
    }

    public String getLogoURL() {
        return logo;
    }

    public String getId() { return id; }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public boolean equals(Object object) {
        if(this == object)
            return true;

        Restaurant restaurant;
        if(object instanceof Restaurant)
            restaurant  = (Restaurant)object;
        else
            return false;

        if(this.name.equals(restaurant.name) && this.location.equals(restaurant.location))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return 1234;
    }

}
