package Domain;

import Serializer.orderSerializer;

import java.util.ArrayList;

public class Customer {
    private ArrayList<Order> orders = new ArrayList<Order>();

    public String addToCart(Restaurant restaurant, Food food) {
        if(orders.size() == 0 || orders.get(orders.size() - 1).getStatus() != Order.orderStatus.Ordering){
            Order order = new Order(restaurant);
            orders.add(order);
        }
        boolean orderedSuccessfully = orders.get(orders.size() - 1).addToCart(restaurant, food);
        if(orderedSuccessfully)
            return "Food Added To Your Cart Successfully";
        else
            return "You can't order from different restaurants";
    }

    public String getCart() {
        if(orders.size() == 0 || orders.get(orders.size()-1).getStatus() != Order.orderStatus.Ordering){
            return "There is no order in progress";
        }
        return orderSerializer.orderSerialize(orders.get(orders.size()-1));
    }

    public String finalizeOrder() {
        if(orders.size() == 0 || orders.get(orders.size()-1).getStatus() != Order.orderStatus.Ordering){
            return "There is no order in progress";
        }
        String result = getCart();
        orders.get(orders.size()-1).setStatus(Order.orderStatus.Submitted);
        return (result + "\nOrder was submitted successfully");
    }

    public Order getLastOrder() {
        if(orders.size() == 0){
            return null;
        }
        return orders.get(orders.size() - 1);
    }
}
