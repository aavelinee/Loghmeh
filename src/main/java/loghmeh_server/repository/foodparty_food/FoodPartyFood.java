package loghmeh_server.repository.foodparty_food;

import loghmeh_server.repository.food.Food;
import loghmeh_server.repository.food.FoodMapper;
import loghmeh_server.repository.menu.MenuMapper;

import java.sql.SQLException;

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
            try{
                int menu_id = MenuMapper.getInstance().find_menu_id(getMenu().getRestaurant());
                int food_id = FoodMapper.getInstance().find(menu_id, getName());
                this.count -= count;
                FoodPartyFoodMapper.getInstance().update_count(food_id, count);
                return true;
            } catch (SQLException ex) {
                System.out.println("SQLException in decrease foodparty food count");
                return false;
            }
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
