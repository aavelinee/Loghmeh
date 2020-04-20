package loghmeh_server.domain;

public class OrderItem {
    private Food food;
    private int orderCount;
    private boolean isFoodParty;

    public OrderItem(Food food, int foodCount) {
        this.orderCount = foodCount;
        this.food = food;
        if(food instanceof FoodPartyFood)
            this.isFoodParty = true;
        else
            this.isFoodParty = false;
    }

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
