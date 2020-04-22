package loghmeh_server.repository.food;

import loghmeh_server.repository.menu.Menu;
import loghmeh_server.repository.ConnectionPool;
import loghmeh_server.repository.Mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class FoodMapper extends Mapper {
    private static FoodMapper foodMapper = null;


    private static final String COLUMNS = "name, description, price, popularity, image_url, menu_id";
    private static final String TABLE_NAME = "foods";
    private Map<Integer, Food> loadedMap = new HashMap<Integer, Food>();


    public static FoodMapper getInstance() {
        if(foodMapper == null){
            foodMapper = new FoodMapper();
        }
        return foodMapper;
    }


    public Food find(int id, Menu menu) throws SQLException {
        Food result = loadedMap.get(id);
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
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                return convertResultSetToObject(resultSet, menu);
            } catch (SQLException ex) {
                System.out.println("error in Mapper.findByID query.");
                throw ex;
            }
        }
    }

    public void insert(Food obj, int menu_id) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "insert into " + TABLE_NAME + "(" + COLUMNS + ")" + " values (?, ?, ?, ?, ?, ?)"
             )
        ) {
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setString(2, obj.getDescription());
            preparedStatement.setFloat(3, obj.getPrice());
            preparedStatement.setFloat(4, obj.getPopularity());
            preparedStatement.setString(5, obj.getImage());
            preparedStatement.setInt(6, menu_id);
            try {
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


    private Food convertResultSetToObject(ResultSet rs, Menu menu) throws SQLException {
        Food food = new Food();
        food.setName(rs.getString(1));
        food.setDescription(rs.getString(2));
        food.setPrice(rs.getFloat(3));
        food.setPopularity(rs.getFloat(4));
        food.setImage(rs.getString(5));
        food.setMenu(menu);

        return food;
    }
}
