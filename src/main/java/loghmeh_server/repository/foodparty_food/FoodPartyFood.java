package loghmeh_server.repository.foodparty_food;

import loghmeh_server.repository.food.Food;

public class FoodPartyFood extends Food {
    private int count;
    private float oldPrice;

    public boolean checkCount(int count) {
        if(this.count >= count){
            return true;
        }
        return false;
    }

    public synchronized boolean decreaseCount(int count) {
        if(checkCount(count)) {
            this.count -= count;
            return true;
        }
        return false;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setOldPrice(float oldPrice) {
        this.oldPrice = oldPrice;
    }

    public int getCount() {
        return count;
    }

    public float getOldPrice() {
        return oldPrice;
    }
}
