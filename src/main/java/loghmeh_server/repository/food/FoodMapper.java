package loghmeh_server.repository.food;

import loghmeh_server.repository.menu.Menu;
import loghmeh_server.repository.ConnectionPool;
import loghmeh_server.repository.Mapper;
import loghmeh_server.repository.menu.MenuMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class FoodMapper extends Mapper {
    private static FoodMapper foodMapper = null;


    private static final String COLUMNS = "name, description, price, popularity, image_url, menu_id";
    private static final String TABLE_NAME = "foods";

    public static FoodMapper getInstance() {
        if(foodMapper == null){
            foodMapper = new FoodMapper();
        }
        return foodMapper;
    }


    public Food find(int id, Menu menu) throws SQLException {

        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "select " + COLUMNS + " from " + TABLE_NAME + " where id = (?)"
             )
        ) {
            ps.setInt(1, id);
            try {
                ResultSet resultSet = ps.executeQuery();
                if(resultSet.next())
                    return convertResultSetToObject(resultSet, menu);
                return null;
            } catch (SQLException ex) {
                System.out.println("error in FoodMapper.findByID query.");
                throw ex;
            }
        }
    }

    public ArrayList<Food> find_foods(Menu menu) throws SQLException{
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "select " + COLUMNS + " from " + TABLE_NAME + " where menu_id = (?) and id not in " +
                             "(select food_id from foodpartyfoods)"
             )
        ) {
            ArrayList<Food> foods = new ArrayList<>();
            int menu_id = MenuMapper.getInstance().find_menu_id(menu.getRestaurant());
            if(menu_id != -1){
                ps.setInt(1, menu_id);
                try {
                    ResultSet resultSet = ps.executeQuery();
                    while(resultSet.next()) {
                        foods.add(convertResultSetToObject(resultSet, menu));
                    }
                } catch (SQLException ex) {
                    System.out.println("error in FoodMapper.findFoodsByMenuID query.");
                    throw ex;
                }
            }
            return foods;
        }
    }

    public int find(int menu_id, String food_name) throws SQLException{
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "select id from " + TABLE_NAME + " where menu_id = (?) and name = (?) and " +
                             "id = (select max(id) from " + TABLE_NAME + "  where menu_id = (?) and name = (?))"
             )
        ) {
            ps.setInt(1, menu_id);
            ps.setString(2, food_name);
            ps.setInt(3, menu_id);
            ps.setString(4, food_name);
            try {
                ResultSet resultSet = ps.executeQuery();
                if(resultSet.next())
                    return resultSet.getInt(1);
                else
                    return -1;
            } catch (SQLException ex) {
                System.out.println("error in FoodMapper.findFoodsByMenuID query.");
                throw ex;
            }
        }
    }

    public void insert(Food obj, int menu_id) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "insert into " + TABLE_NAME + "(" + COLUMNS + ")" + " values (?, ?, ?, ?, ?, ?)"
             )
        ) {
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getDescription());
            ps.setFloat(3, obj.getPrice());
            ps.setFloat(4, obj.getPopularity());
            ps.setString(5, obj.getImage());
            ps.setInt(6, menu_id);
            try {
                ps.executeUpdate();
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
        food.setRestaurantId(menu.getRestaurant().getId());
        food.setRestaurantName(menu.getRestaurant().getName());

        return food;
    }
}
