package Domain;

import java.lang.Math;

public class Location {
    private Float x;
    private Float y;

    public Location(Float x, Float y) {
        this.x = x;
        this.y = y;
    }

    public double euclideanDistance(Location location) {
        return Math.sqrt(Math.pow((location.x - x), 2) + Math.pow((location.y - y), 2));
    }

    @Override
    public boolean equals(Object object) {
        if(this == object)
            return true;

        Location location;
        if(object instanceof Location)
            location = (Location) object;
        else
            return false;

        if(this.x == location.x && this.y == location.y)
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return 1234;
    }

}
