package Domain;

public class Food {
    private String name;
    private String description;
    private float popularity;
    private float price;
    private transient Menu menu;

    public String getName() {
        return name;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
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


    public void print() {
        System.out.println(name);
        System.out.println(description);
        System.out.println(popularity);
        System.out.println(price);
        System.out.println(menu.getRestaurant().getName());
    }

}
