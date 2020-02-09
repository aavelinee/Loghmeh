package Domain;

public class RecommendationItem {
    private Restaurant restaurant;
    private double ratingForUser;

    public RecommendationItem(Restaurant restaurant, double ratingForUser) {
        this.restaurant = restaurant;
        this.ratingForUser = ratingForUser;
    }

    @Override
    public double compare(RecommendationItem recommendationItem)
    {
        return(ratingForUser - recommendationItem.ratingForUser);
    }
}
