package domain;

public class Delivery {
    private String id;
    private float velocity;
    private Location location;

    public Delivery(String id, float velocity, Location location) {
        this.id = id;
        this.velocity = velocity;
        this.location = location;
    }


}