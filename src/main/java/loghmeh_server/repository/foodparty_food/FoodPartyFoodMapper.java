package loghmeh_server.repository.foodparty_food;

import loghmeh_server.repository.menu.Menu;
import loghmeh_server.repository.ConnectionPool;
import loghmeh_server.repository.Mapper;
import loghmeh_server.repository.food.Food;
import loghmeh_server.repository.food.FoodMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FoodPartyFoodMapper extends Mapper {
    private static FoodPartyFoodMapper foodPartyFoodMapper = null;


    private static final String COLUMNS = "food_id, count, old_price";
    private static final String TABLE_NAME = "foodpartyfoods";
    private Map<Integer, FoodPartyFood> loadedMap = new HashMap<Integer, FoodPartyFood>();


    public static FoodPartyFoodMapper getInstance() {
        if(foodPartyFoodMapper == null){
            foodPartyFoodMapper = new FoodPartyFoodMapper();
        }
        return foodPartyFoodMapper;
    }


    public FoodPartyFood find(int id, Menu menu) throws SQLException {
        FoodPartyFood result = loadedMap.get(id);
        if (result != null)
            return result;

        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "select " + COLUMNS + " from " + TABLE_NAME + " where food_id = (?)"
             )
        ) {
            ps.setInt(1, id);
            ResultSet resultSet;
            try {
                Food food = FoodMapper.getInstance().find(id, menu);
                resultSet = ps.executeQuery();
                if(resultSet.next())
                    return convertResultSetToObject(resultSet, food);
                else
                    return null;
            } catch (SQLException ex) {
                System.out.println("error in FoodPartyFoodMapper.findByID query.");
                throw ex;
            }
        }
    }

    public ArrayList<FoodPartyFood> find__foodparty_foods(int menu_id, Menu menu) throws SQLException{
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "select id from foods where menu_id = (?) and " +
                             "id in (select food_id from foodpartyfoods)"
             )
        ) {
            ps.setInt(1, menu_id);
            ResultSet resultSet;
            try {
                resultSet = ps.executeQuery();
                ArrayList<FoodPartyFood> foodPartyFoods = new ArrayList<>();
                while(resultSet.next()) {
                    foodPartyFoods.add(find(resultSet.getInt(1), menu));
                }
                return foodPartyFoods;
            } catch (SQLException ex) {
                System.out.println("error in FoodPartyFoodMapper.findFoodPartyFoodsByMenuID query.");
                throw ex;
            }
        }
    }

    public void insert(FoodPartyFood obj, int menu_id) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "insert into " + TABLE_NAME + "(" + COLUMNS + ")" + " values (?, ?, ?)"
             )
        ) {
            FoodMapper.getInstance().insert(obj, menu_id);
            ps.setInt(1, FoodMapper.getInstance().find(menu_id, obj.getName()));
            ps.setInt(2, obj.getCount());
            ps.setFloat(3, obj.getOldPrice());
            try {
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("error in FoodPartyFoodMapper.insert query.");
                throw ex;
            }
        }
    }

    public void delete(int id) throws SQLException {
        this.delete(TABLE_NAME, id);
    }


    private FoodPartyFood convertResultSetToObject(ResultSet rs, Food food) throws SQLException {
        FoodPartyFood foodPartyFood = new FoodPartyFood();
        foodPartyFood.setName(food.getName());
        foodPartyFood.setDescription(food.getDescription());
        foodPartyFood.setPopularity(food.getPopularity());
        foodPartyFood.setPrice(food.getPrice());
        foodPartyFood.setImage(food.getImage());
        foodPartyFood.setRestaurantId(food.getRestaurantId());
        foodPartyFood.setRestaurantName(food.getRestaurantName());
        foodPartyFood.setMenu(food.getMenu());
        foodPartyFood.setCount(rs.getInt(2));
        foodPartyFood.setOldPrice(rs.getFloat(3));

        return foodPartyFood;
    }
}
