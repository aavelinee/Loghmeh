package loghmeh_server.repository.order;

import loghmeh_server.repository.customer.Customer;
import loghmeh_server.repository.delivery.Delivery;
import loghmeh_server.repository.order_item.OrderItem;
import loghmeh_server.repository.food.Food;
import loghmeh_server.repository.foodparty_food.FoodPartyFood;
import loghmeh_server.repository.restaurant.Restaurant;

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
    private float totalPrice;
    private Customer customer;

    public Order(Restaurant restaurant, Customer customer) {
        status = orderStatus.Ordering;
//        this.id = id;
        this.restaurant = restaurant;
        this.totalPrice = 0;
        this.customer = customer;
    }

    public Order(){}

    public boolean addToCart(Restaurant restaurant, Food food, int foodCount) {
        if(!this.restaurant.equals(restaurant)){
            return false;
        }
        this.totalPrice +=  (foodCount*food.getPrice());

        for(OrderItem orderItem: orders){
            if(orderItem.getFood().equals(food)) {
                orderItem.orderMore(foodCount);
                return true;
            }
        }
        OrderItem orderItem = new OrderItem(food, foodCount, this);
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

    public boolean isFoodInOrder(String foodName) {
        for(OrderItem orderItem: orders) {
            if(orderItem.getFood().getName().equals(foodName))
                return true;
        }
        return false;
    }

    public void removeItemFromOrder(String foodName) {
        for(OrderItem orderItem: orders) {
            if(orderItem.getFood().getName().equals(foodName)) {
                orderItem.orderLess();
                this.totalPrice -= orderItem.getFood().getPrice();
                if(orderItem.getOrderCount() == 0) {
                    orders.remove(orderItem);
                }
                break;
            }
        }
    }

    public void removeFoodPartyFoodsFromCart() {
        for(int i = orders.size() - 1; i >= 0; i--) {
            if(orders.get(i).getFood() instanceof FoodPartyFood)
                orders.remove(orders.get(i));
        }
    }

    public boolean decreaseFoodCounts() {
        for(OrderItem orderItem: orders) {
            if(orderItem.getFood() instanceof FoodPartyFood) {
                if(!((FoodPartyFood) orderItem.getFood()).decreaseCount(orderItem.getOrderCount()))
                    return false;
            }
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public ArrayList<OrderItem> getOrders() {
        return orders;
    }

    public orderStatus getStatus() {
        return status;
    }

    public Restaurant getRestaurant() { return restaurant; }

    public double getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    public Date getDeliveryDate() { return deliveryDate; }

    public Delivery getDelivery() { return delivery; }

    public float getTotalPrice() { return totalPrice; }

    public Customer getCustomer() { return customer; }


    public void setStatus(orderStatus status) {
        this.status = status;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public void setEstimatedDeliveryTime(double estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setId(int id) { this.id = id; }

    public void setRestaurant(Restaurant restaurant) { this.restaurant = restaurant; }

    public void setOrders(ArrayList<OrderItem> orders) { this.orders = orders; }

    public void setTotalPrice(float totalPrice) { this.totalPrice = totalPrice; }

    public void setCustomer(Customer customer) {this.customer = customer; }
}
