package loghmeh_server.repository.menu;

import loghmeh_server.repository.ConnectionPool;
import loghmeh_server.repository.Mapper;
import loghmeh_server.repository.food.Food;
import loghmeh_server.repository.food.FoodMapper;
import loghmeh_server.repository.foodparty_food.FoodPartyFood;
import loghmeh_server.repository.foodparty_food.FoodPartyFoodMapper;
import loghmeh_server.repository.restaurant.Restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MenuMapper extends Mapper {
    private static MenuMapper menuMapper = null;


    private static final String COLUMNS = "restaurant_id";
    private static final String TABLE_NAME = "menus";


    public static MenuMapper getInstance() {
        if(menuMapper == null){
            menuMapper = new MenuMapper();
        }
        return menuMapper;
    }

    public Menu find(Restaurant restaurant) throws SQLException {
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "select id, " + COLUMNS + " from " + TABLE_NAME + " where restaurant_id = (?)"
             )
        ) {
            ps.setString(1, restaurant.getId());
            try {
                ResultSet resultSet = ps.executeQuery();
                if(resultSet.next())
                    return convertResultSetToObject(resultSet, restaurant);
                else
                    return null;
            } catch (SQLException ex) {
                System.out.println("error in MenuMapper.findByID query.");
                throw ex;
            }
        }
    }

    public int find_menu_id(Restaurant restaurant) throws SQLException {
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "select id from " + TABLE_NAME + " where restaurant_id = (?)"
             )
        ) {
            ps.setString(1, restaurant.getId());
            try {
                ResultSet resultSet = ps.executeQuery();
                if(resultSet.next())
                    return resultSet.getInt(1);
                else
                    return -1;
            } catch (SQLException ex) {
                System.out.println("error in MenuMapper.findByID query.");
                throw ex;
            }
        }
    }

    public void insert(Menu obj, String restaurant_id) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "insert into " + TABLE_NAME + "(" + COLUMNS + ")" + " values (?)"
             )
        ) {
            ps.setString(1, restaurant_id);

            try {
                ps.executeUpdate();
                PreparedStatement ps2 = connection.prepareStatement("select id from menus where restaurant_id = (?)");
                ps2.setString(1, restaurant_id);
                ResultSet resultSet = ps2.executeQuery();
                resultSet.next();
                if(obj.getFoods() != null){
                    for(Food food: obj.getFoods()){
                        FoodMapper.getInstance().insert(food, resultSet.getInt(1));
                    }
                }
                if(obj.getFoodPartyFoods() != null){
                    for(FoodPartyFood foodPartyFood: obj.getFoodPartyFoods()){
                        FoodPartyFoodMapper.getInstance().insert(foodPartyFood, resultSet.getInt(1));
                    }
                }
                ps.close();
            } catch (SQLException ex) {
                System.out.println("error in MenuMapper.insert query.");
                throw ex;
            }
        }
    }

    public void delete(int id) throws SQLException {
        this.delete(TABLE_NAME, id);
    }


    private Menu convertResultSetToObject(ResultSet rs, Restaurant restaurant) throws SQLException {
        Menu menu = new Menu();
        menu.setRestaurant(restaurant);
        menu.setFoods(FoodMapper.getInstance().find_foods(menu));

        menu.setFoodPartyFoods(FoodPartyFoodMapper.getInstance().find_foodparty_foods(menu));

        return menu;
    }
}
