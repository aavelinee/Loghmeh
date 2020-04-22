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
import java.util.HashMap;
import java.util.Map;

public class FoodPartyFoodMapper extends Mapper {
    private static FoodPartyFoodMapper foodPartyFoodMapper = null;


    private static final String COLUMNS = "count, old_price";
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
             PreparedStatement preparedStatement = con.prepareStatement(
                     "select " + COLUMNS + " from " + TABLE_NAME + " where id = (?)"
             )
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet;
            try {
                Food food = FoodMapper.getInstance().find(id, menu);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                return convertResultSetToObject(resultSet, menu, food);
            } catch (SQLException ex) {
                System.out.println("error in Mapper.findByID query.");
                throw ex;
            }
        }
    }

    public void insert(FoodPartyFood obj, int menu_id) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "insert into " + TABLE_NAME + "(" + COLUMNS + ")" + " values (?, ?)"
             )
        ) {
            preparedStatement.setInt(1, obj.getCount());
            preparedStatement.setFloat(2, obj.getOldPrice());
            try {
                FoodMapper.getInstance().insert(obj, menu_id);
                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("error in Mapper.insert query.");
                throw ex;
            }
        }
    }

    public void delete(int id) throws SQLException {
        this.delete(TABLE_NAME, id);
    }


    private FoodPartyFood convertResultSetToObject(ResultSet rs, Menu menu, Food food) throws SQLException {
        FoodPartyFood foodPartyFood = (FoodPartyFood)food;
        foodPartyFood.setCount(rs.getInt(1));
        foodPartyFood.setOldPrice(rs.getFloat(2));

        return foodPartyFood;
    }
}
