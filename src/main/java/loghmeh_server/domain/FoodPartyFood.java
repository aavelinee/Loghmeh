package loghmeh_server.domain;

public class FoodPartyFood extends Food{
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

    public int getCount() {
        return count;
    }

    public float getOldPrice() {
        return oldPrice;
    }
}
