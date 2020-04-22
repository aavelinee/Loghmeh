package loghmeh_server.repository.food;

import loghmeh_server.repository.menu.Menu;

public class Food {
    private String name;
    private String description;
    private float popularity;
    private float price;
    private String image;
    private String restaurantId;
    private String restaurantName;
    private transient Menu menu;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
        this.restaurantId = menu.getRestaurant().getId();
        this.restaurantName = menu.getRestaurant().getName();
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public float getPopularity() {
        return popularity;
    }

    @Override
    public boolean equals(Object object) {
        if(this == object)
            return true;

        Food food;
        if(object instanceof Food)
            food = (Food)object;
        else
            return false;

        if(this.name.equals(food.name) && this.description.equals(food.description) && this.popularity == food.popularity &&
                this.price == food.price) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 1234;
    }

}
