package Domain;

import Deserializer.foodDeserializer;
import Deserializer.restaurantDeserializer;

import java.util.ArrayList;

public class Order {
    public enum orderStatus {
        Delivered, Payed, Submitted, Ordering;
    }
    private transient orderStatus status;

    private transient Restaurant restaurant;
    private ArrayList<OrderItem> orders = new ArrayList<OrderItem>();


    public Order(Restaurant restaurant) {
        status = orderStatus.Ordering;
        this.restaurant = restaurant;
    }

    public boolean addToCart(Restaurant restaurant, Food food) {
        if(!this.restaurant.equals(restaurant)){
            return false;
        }
        for(OrderItem orderItem: orders){
            if(orderItem.getFood().equals(food)) {
                orderItem.orderMore();
                return true;
            }
        }
        OrderItem orderItem = new OrderItem(food);
        orders.add(orderItem);
        return true;
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
}
