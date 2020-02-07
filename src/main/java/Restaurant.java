import java.util.ArrayList;

public class Restaurant {
    private String name;
    private String description;
    private Location location;
    private Menu menu;
//    private ArrayList<Food> menu = new ArrayList<Food>();

    public Restaurant(String name, String description, Location location, Menu menu) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.menu = menu;
    }
    public boolean equals(Restaurant restaurant) {
        if(this.name.equals(restaurant.name) && this.location == restaurant.location)
            return true;
        return false;
    }
    public  void print(){
        System.out.println(name);
        System.out.println(description);
        location.print();
        menu.print();
    }

}
