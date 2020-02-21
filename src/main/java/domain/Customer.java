package domain;

import serializer.orderSerializer;

import java.util.ArrayList;

public class Customer {
    private ArrayList<Order> orders = new ArrayList<Order>();
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
            Order order = new Order(restaurant);
            orders.add(order);
        }
        boolean orderedSuccessfully = orders.get(orders.size() - 1).addToCart(restaurant, food);
        return orderedSuccessfully;
    }

    public Order getCart() {
        if(orders.size() == 0 || orders.get(orders.size()-1).getStatus() != Order.orderStatus.Ordering){
            return null;
        }
        return orders.get(orders.size()-1);
    }

    public String finalizeOrder() {
        if(orders.size() == 0 || orders.get(orders.size()-1).getStatus() != Order.orderStatus.Ordering){
            return "There is no order in progress";
        }
        String result = orderSerializer.orderSerialize(getCart());
        orders.get(orders.size()-1).setStatus(Order.orderStatus.Submitted);
        credit -= orders.get(orders.size()-1).getPrice();
        return (result + "\nOrder was submitted successfully");
    }

    public Order getLastOrder() {
        if(orders.size() == 0){
            return null;
        }
        return orders.get(orders.size() - 1);
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

    public void increaseCredit(int credit) {
        this.credit += credit;
    }

    public ArrayList<Restaurant> getCloseRestaurants() {
        ArrayList<Restaurant> restaurants = Loghmeh.getInstance().getRestaurants();
        ArrayList<Restaurant> closeRestaurants = new ArrayList<Restaurant>();
        for(Restaurant restaurant: restaurants) {
            if (restaurant.getLocation().euclideanDistance(location) <= 170){
                closeRestaurants.add(restaurant);
            }
        }
        return closeRestaurants;
    }

    public boolean isRestaurantClose(Location location){
        if(this.location.euclideanDistance(location) <= 170)
            return true;
        return false;
    }
}
