package loghmeh_server.repository.delivery;

import loghmeh_server.repository.location.Location;

public class Delivery {
    private String id;
    private float velocity;
    private Location location;

    public Delivery(String id, float velocity, Location location) {
        this.id = id;
        this.velocity = velocity;
        this.location = location;
    }

    public Delivery(){}

    public String getId() { return id; }

    public float getVelocity() {
        return velocity;
    }

    public Location getLocation() {
        return location;
    }

    public void setId(String id) { this.id = id; }

    public void setVelocity(float velocity) { this.velocity = velocity; }

    public void setLocation(Location location) { this.location = location; }
}