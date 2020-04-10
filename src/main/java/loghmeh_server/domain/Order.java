package loghmeh_server.domain;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    public enum orderStatus {
        Delivered, OnTheWay, DeliverySearch, Ordering;
    }

    private int id;
    private orderStatus status;

    private Restaurant restaurant;
    private ArrayList<OrderItem> orders = new ArrayList<OrderItem>();
    private Delivery delivery;
    private double estimatedDeliveryTime;
    private Date deliveryDate;

    public Order(int id, Restaurant restaurant) {
        status = orderStatus.Ordering;
        this.id = id;
        this.restaurant = restaurant;
    }

    public boolean addToCart(Restaurant restaurant, Food food) {
        if(!this.restaurant.equals(restaurant)){
            return false;
        }
        for(OrderItem orderItem: orders){
            if(orderItem.getFood().equals(food)) {
                orderItem.orderMore();
                System.out.println("order morrreeee");
                return true;
            }
        }
        OrderItem orderItem = new OrderItem(food);
        orders.add(orderItem);
        return true;
    }

    public float getPrice() {
        float price = 0;
        for(OrderItem orderItem: orders){
            price += orderItem.getFood().getPrice() * orderItem.getOrderCount();
        }
        return price;
    }

    public int getId() {
        return id;
    }

    public orderStatus getStatus() {
        return status;
    }

    public void setStatus(orderStatus status) {
        this.status = status;
    }

    public ArrayList<OrderItem> getOrders() {
        return orders;
    }

    public Restaurant getRestaurant() { return restaurant; }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public void setEstimatedDeliveryTime(double estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    public double getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }
}
