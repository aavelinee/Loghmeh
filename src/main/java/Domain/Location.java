package Domain;

public class Location {
    private Float x;
    private Float y;

    public Location(Float x, Float y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Location location) {
        if(this.x == location.x && this.y == location.y)
            return true;
        return false;
    }

    public void print() {
        System.out.println(x);
        System.out.println(y);
    }

}
