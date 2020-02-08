package Domain;

public class Food {
    private String name;
    private String description;
    private float popularity;
    private float price;

    public String getName() {
        return name;
    }

    public void print() {
        System.out.println(name);
        System.out.println(description);
        System.out.println(popularity);
        System.out.println(price);
    }

}
