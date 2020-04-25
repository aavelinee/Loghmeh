package loghmeh_server.repository.customer;

import loghmeh_server.repository.order.Order;
import loghmeh_server.repository.food.Food;
import loghmeh_server.repository.location.Location;
import loghmeh_server.repository.order.OrderMapper;
import loghmeh_server.repository.restaurant.Restaurant;

import java.sql.SQLException;
import java.util.ArrayList;

public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private float credit;
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

    public Customer(){}

    public Boolean addToCart(Restaurant restaurant, Food food, int foodCount) {
        Order order;
        try {
            order = OrderMapper.getInstance().find_cart(this);
        } catch (SQLException ex) {
            System.out.print("Sql exception in find last order in add to cart");
            return false;
        }
        if(order == null){
            try {
                OrderMapper.getInstance().insert(new Order(restaurant, this));
                order = OrderMapper.getInstance().find_cart(this);
            } catch (SQLException ex) {
                System.out.print("SQL exception in inserting order");
                return false;
            }
        }
        return order.addToCart(restaurant, food, foodCount);
    }

    public Order getCart() {
        Order order;
        try {
            order = OrderMapper.getInstance().find_cart(this);
        } catch (SQLException ex) {
            System.out.print("Sql exception in find last order in get cart");
            return null;
        }
        return order;
    }

    public void removeCart() {
        Order order;
        try {
            order = OrderMapper.getInstance().find_cart(this);
        } catch (SQLException ex) {
            System.out.print("Sql exception in find last order in add to cart");
            return;
        }
        if(order == null){
            return;
        }
        try {
            OrderMapper.getInstance().delete(order.getId());
        } catch (SQLException ex) {
            System.out.print("Sql exception in delete order in remove cart");
            return;
        }
        System.out.print("order deleted successfully");
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
        if(cart == null)
            return;
        cart.removeFoodPartyFoodsFromCart();
        if(cart.getOrders().size() == 0)
            removeCart();
    }

    public boolean finalizeOrder() {
        Order cart = this.getCart();
        if(cart == null){
            return false;
        }
//        float orderPrice = cart.getPrice();
        if(credit >= cart.getTotalPrice()) {
            cart.decreaseFoodCounts();
            cart.setStatus(Order.orderStatus.DeliverySearch);
            credit -= cart.getTotalPrice();
            CustomerMapper.getInstance().update_credit(customerId, credit);
            return true;
        }
        return false;
    }

    public void increaseCredit(float credit) {
        CustomerMapper.getInstance().update_credit(customerId, this.credit + credit);
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

    public float getCredit() {
        return credit;
    }

    public Location getLocation() {
        return location;
    }

    public ArrayList<Order> getOrders() {
        return OrderMapper.getInstance().find_orders(this);
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
