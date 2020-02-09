package Domain;

public class OrderItem {
    private Food food;
    private int orderCount;

    public OrderItem(Food food) {
        orderCount = 1;
        this.food = food;
    }

    public void orderMore() {
        this.orderCount += 1;
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
