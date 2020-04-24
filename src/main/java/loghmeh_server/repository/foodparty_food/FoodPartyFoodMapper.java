package loghmeh_server.repository.foodparty_food;

import loghmeh_server.repository.menu.Menu;
import loghmeh_server.repository.ConnectionPool;
import loghmeh_server.repository.Mapper;
import loghmeh_server.repository.food.Food;
import loghmeh_server.repository.food.FoodMapper;
import loghmeh_server.repository.menu.MenuMapper;
import loghmeh_server.repository.order_item.OrderItemMapper;
import loghmeh_server.repository.restaurant.Restaurant;
import loghmeh_server.repository.restaurant.RestaurantMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FoodPartyFoodMapper extends Mapper {
    private static FoodPartyFoodMapper foodPartyFoodMapper = null;


    private static final String COLUMNS = "food_id, count, old_price, expiration_time";
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

    public FoodPartyFood find(String restaurant_id, String food_name) {
        try {
            Restaurant restaurant = RestaurantMapper.getInstance().find(restaurant_id);
            if(restaurant == null)
                return null;
            Menu menu = MenuMapper.getInstance().find(restaurant);
            if(menu == null)
                return null;
            int menu_id = MenuMapper.getInstance().find_menu_id(restaurant);
            int food_id = FoodMapper.getInstance().find(menu_id, food_name);
            return find(food_id, menu);
        } catch (SQLException se) {
            System.out.println("SQL Exception in find foodpartyfood");
            return null;
        }
    }

    public ArrayList<FoodPartyFood> find_foodparty_foods(Menu menu) throws SQLException{
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "select food_id from foodpartyfoods where food_id in (select id from foods where menu_id=(?)) and expiration_time > now()"
             )
        ) {
            ArrayList<FoodPartyFood> foodPartyFoods = new ArrayList<>();
            int menu_id = MenuMapper.getInstance().find_menu_id(menu.getRestaurant());
            if(menu_id != -1){
                ps.setInt(1, menu_id);
                ResultSet resultSet;
                try {
                    resultSet = ps.executeQuery();
                    while(resultSet.next()) {
                        foodPartyFoods.add(find(resultSet.getInt(1), menu));
                    }
                } catch (SQLException ex) {
                    System.out.println("error in FoodPartyFoodMapper.findFoodPartyFoodsByMenuID query.");
                    throw ex;
                }
            }
            return foodPartyFoods;
        }
    }

    public ArrayList<FoodPartyFood> find_all_foodparty_foods() {
        ArrayList<Restaurant> foodPartyRestaurants = RestaurantMapper.getInstance().find_restaurants("foodparty", 0);
        ArrayList<FoodPartyFood>foodPartyFoods = new ArrayList<>();
        for(Restaurant restaurant: foodPartyRestaurants) {
            try{
                foodPartyFoods.addAll(find_foodparty_foods(restaurant.getMenu()));
            } catch (SQLException ex) {
                System.out.println("SQL Exception in finding all foodparty foods");
                continue;
            }
        }
        System.out.println("found all foodparty foods");
        return foodPartyFoods;
    }

    public boolean is_expired(FoodPartyFood foodPartyFood) {
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "select count(*) from foodpartyfoods where food_id in (select id from foods where menu_id = (?)  and name = (?)) and expiration_time > now()"
             )
        ) {
            int menu_id = MenuMapper.getInstance().find_menu_id(foodPartyFood.getMenu().getRestaurant());
            if(menu_id != -1){
                ps.setInt(1, menu_id);
                ps.setString(2, foodPartyFood.getName());
                ResultSet resultSet = ps.executeQuery();
                resultSet.next();
                if(resultSet.getInt(1) == 0)
                    return true;
                else
                    return false;
            }

        } catch (SQLException ex) {
            System.out.println("SQL Exception in checking is expired");
            return true;
        }
        return true;
    }

    public boolean is_foodparty(int food_id) {
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "select count(*) from foodpartyfoods where food_id = (?)"
             )
        ) {
            try{
                ps.setInt(1, food_id);
                ResultSet resultSet = ps.executeQuery();
                resultSet.next();
                if(resultSet.getInt(1) == 0)
                    return false;
                else
                    return true;
            } catch (SQLException ex) {
                throw ex;
            }

        } catch (SQLException ex) {
            System.out.println("SQL Exception in checking is foodparty");
            return false;
        }
    }

    public void insert(FoodPartyFood obj, int menu_id) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "insert into " + TABLE_NAME + "(" + COLUMNS + ")" + " values (?, ?, ?, now() + interval 2 minute)"
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

    public void insert_foodparty_foods(ArrayList<FoodPartyFood>foodPartyFoods, int menu_id) {
        for(FoodPartyFood foodPartyFood: foodPartyFoods) {
            try {
                insert(foodPartyFood, menu_id);
            } catch(SQLException se) {
                System.out.println("SQL Execption in inserting foodparty");
                continue;
            }
        }
    }

    public void delete(int id) throws SQLException {
        this.delete(TABLE_NAME, id);
    }

    public void update_count(int food_id, int count) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement (
                     "update " + TABLE_NAME + " set count = (?) where food_id = (?)"
             )
        ){

            ps.setInt(2, food_id);
            ps.setInt(1, count);
            try {
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("error in OrderMapper.update foodpartyfood count query.");
                throw ex;
            }

        } catch(SQLException ex) {
            System.out.println("SQL Exception in updating foodpartyfood count");
        }
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
