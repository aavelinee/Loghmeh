package domain;

public class RecommendationItem {
    private Restaurant restaurant;
    private double ratingForUser;

    public RecommendationItem(Restaurant restaurant, double ratingForUser) {
        this.restaurant = restaurant;
        this.ratingForUser = ratingForUser;
    }

    public double getRatingForUser() {
        return ratingForUser;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
