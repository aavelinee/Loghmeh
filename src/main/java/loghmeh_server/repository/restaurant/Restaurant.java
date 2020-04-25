package loghmeh_server.repository.restaurant;

import loghmeh_server.repository.food.Food;
import loghmeh_server.repository.foodparty_food.FoodPartyFood;
import loghmeh_server.repository.location.Location;
import loghmeh_server.repository.menu.Menu;

public class Restaurant {
    private String id;
    private String name;
    private String logo;
    private String description;
    private Location location;
    private Menu menu;

    public Restaurant(String id, String name, String logoURL, Location location, Menu menu) {
        this.id = id;
        this.name = name;
        this.logo = logoURL;
        this.location = location;
        this.menu = menu;
    }

    public Restaurant(){}


    public Food getFoodByName(String foodName) {
        return menu.getFood(foodName);
    }

    public FoodPartyFood getFoodPartyFoodByName(String foodName) {
        return menu.getFoodPartyFood(foodName);
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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(Location location) {
        this.location = location;
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
