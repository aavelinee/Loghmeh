package loghmeh_server.repository.order_item;

import loghmeh_server.repository.food.Food;
import loghmeh_server.repository.foodparty_food.FoodPartyFood;
import loghmeh_server.repository.order.Order;

public class OrderItem {

    private Order order;
    private Food food;
    private int orderCount;
    private boolean isFoodParty;

    public OrderItem(Food food, int foodCount, Order order) {
        this.orderCount = foodCount;
        this.food = food;
        if(food instanceof FoodPartyFood)
            this.isFoodParty = true;
        else
            this.isFoodParty = false;
        this.order = order;
    }

    public OrderItem(){}

    public void orderMore(int orderCount) {
        this.orderCount += orderCount;
    }

    public void orderLess() {
        this.orderCount -= 1;
    }

    public Food getFood() {
        return food;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public Order getOrder() { return order; }


    public void setOrder(Order order) { this.order = order; }

    public void setFood(Food food) { this.food = food; }

    public void setOrderCount(int orderCount) { this.orderCount = orderCount; }

    public void setIsFoodParty(boolean foodParty) { isFoodParty = foodParty; }

    @Override
    public boolean equals(Object object) {
        if(this == object)
            return true;

        OrderItem orderItem;
        if(object instanceof OrderItem)
            orderItem = (OrderItem)object;
        else
            return false;

        if(this.food.equals(orderItem.food))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return 1234;
    }
}
