package loghmeh_server.domain;

import java.util.ArrayList;

public class Customer {
    private ArrayList<Order> orders = new ArrayList<>();
    private int customerId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private int credit;
    private Location location;


    public Customer(int id, String firstName, String lastName, String phoneNumber, String email, Float x, Float y) {
        this.customerId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.credit = 0;
        this.location = new Location(x, y);
    }

    public Boolean addToCart(Restaurant restaurant, Food food) {
        if(orders.size() == 0 || orders.get(orders.size() - 1).getStatus() != Order.orderStatus.Ordering){
            Order order = new Order(orders.size() + 1, restaurant);
            orders.add(order);
        }
        return orders.get(orders.size() - 1).addToCart(restaurant, food);
    }

    public Order getCart() {
        if(orders.size() == 0 || orders.get(orders.size()-1).getStatus() != Order.orderStatus.Ordering){
            return null;
        }
        return orders.get(orders.size()-1);
    }

    public void removeCart() {
        if(orders.size() == 0 || orders.get(orders.size()-1).getStatus() != Order.orderStatus.Ordering){
            return;
        }
        orders.remove(orders.size()-1);
    }

    public String removeFromCart(String restaurantId, String foodName) {
        Order cart = getCart();
        if(cart == null)
            return "not found";
        if(!cart.getRestaurant().getId().equals(restaurantId))
            return "not found";
        if(!cart.isFoodInOrder(foodName))
            return "not found";
        cart.removeItemFromOrder(foodName);

        if(cart.getOrders().size() == 0)
            removeCart();

        return "removed";
    }

    public void removeFoodPartyFoodsFromCart() {
        Order cart = getCart();
        cart.removeFoodPartyFoodsFromCart();
        if(cart.getOrders().size() == 0)
            removeCart();
    }

    public boolean finalizeOrder() {
        Order cart = this.getCart();
        if(cart == null){
            return false;
        }
        float orderPrice = cart.getPrice();
        if(credit >= orderPrice) {
            cart.decreaseFoodCounts();
            cart.setStatus(Order.orderStatus.DeliverySearch);
            credit -= orderPrice;
            return true;
        }
        return false;
    }

    public void increaseCredit(int credit) {
        this.credit += credit;
    }

    public boolean isRestaurantClose(Location location){
        if(this.location.euclideanDistance(location) <= 170)
            return true;
        return false;
    }

    public Order getLastOrder() {
        if(orders.size() == 0){
            return null;
        }
        return orders.get(orders.size() - 1);
    }


    public ArrayList<Restaurant> getCloseRestaurants() {
        ArrayList<Restaurant> restaurants = Loghmeh.getInstance().getRestaurants();
        ArrayList<Restaurant> closeRestaurants = new ArrayList<Restaurant>();
        for(Restaurant restaurant: restaurants) {
            if (restaurant.getLocation().euclideanDistance(location) <= 170 && restaurant.getMenu().getFoods() != null){
                closeRestaurants.add(restaurant);
            }
        }
        return closeRestaurants;
    }

    public ArrayList<Restaurant> getCloseFoodPartyRestaurants() {
        ArrayList<Restaurant> restaurants = Loghmeh.getInstance().getRestaurants();
        ArrayList<Restaurant> closeFoodPartyRestaurants = new ArrayList<Restaurant>();
        for(Restaurant restaurant: restaurants) {
            if (restaurant.getLocation().euclideanDistance(location) <= 170 && restaurant.getMenu().getFoodPartyFoods() != null){
                closeFoodPartyRestaurants.add(restaurant);
            }
        }
        return closeFoodPartyRestaurants;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public int getCredit() {
        return credit;
    }

    public Location getLocation() {
        return location;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
}
