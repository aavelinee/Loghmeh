package loghmeh_server.repository.restaurant;

import loghmeh_server.repository.ConnectionPool;
import loghmeh_server.repository.Mapper;
import loghmeh_server.repository.foodparty_food.FoodPartyFoodMapper;
import loghmeh_server.repository.location.LocationMapper;
import loghmeh_server.repository.menu.MenuMapper;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RestaurantMapper extends Mapper {
    private static RestaurantMapper restaurantMapper = null;


    private static final String COLUMNS = "id, name, logo, description, location_id";
    private static final String TABLE_NAME = "restaurants";
    private Map<Integer, Restaurant> loadedMap = new HashMap<Integer, Restaurant>();


    public static RestaurantMapper getInstance() {
        if(restaurantMapper == null){
            restaurantMapper = new RestaurantMapper();
        }
        return restaurantMapper;
    }


    public Restaurant find(String id) {
        Restaurant result = loadedMap.get(id);
        if (result != null)
            return result;

        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "select " + COLUMNS + " from " + TABLE_NAME + " where id = (?)"
             )
        ) {
            ps.setString(1, id);
            try {
                ResultSet resultSet = ps.executeQuery();
                if(resultSet.next())
                    return convertResultSetToObject(resultSet);
                else
                    return null;
            } catch (SQLException ex) {
                throw ex;
            }
        }catch (SQLException ex) {
            System.out.println("SQL Exception in RestaurantMapper.findByID query.");
            return null;
        }
    }

    public ArrayList<Restaurant> find_restaurants(String type) {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "select id from " + TABLE_NAME
             )
        ) {
            try {
                ResultSet resultSet = ps.executeQuery();
                while(resultSet.next()) {
                    try {
                        Restaurant restaurant = find(resultSet.getString(1));
                        if(restaurant != null) {
                            if(type.equals("ordinary") && restaurant.getMenu().getFoods().size() != 0) {
                                restaurants.add(restaurant);
                            }

                            else if(type.equals("foodparty") && restaurant.getMenu().getFoodPartyFoods().size() != 0){
                                restaurants.add(restaurant);
                            }
                        }
                    } catch (SQLException ex) {
                        System.out.println("SQL Exception in RestaurantMapper.findrests query.");
                        continue;
                    }
                }
            } catch (SQLException ex) {
                throw ex;
            }
        }catch (SQLException ex) {
            System.out.println("SQL Exception in RestaurantMapper.findrests query.");
            return null;
        }
        return restaurants;
    }

    public void insert(Restaurant obj) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "insert into " + TABLE_NAME + "(" + COLUMNS + ")" + " values (?, ?, ?, ?, ?)"
             )
        ) {
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getName());
            ps.setString(3, obj.getLogoURL());
            ps.setString(4, obj.getDescription());
            LocationMapper.getInstance().insert(obj.getLocation());
            ps.setInt(5, LocationMapper.getInstance().find(obj.getLocation().getX(), obj.getLocation().getY()));
            try {
                ps.executeUpdate();
                MenuMapper.getInstance().insert(obj.getMenu(), obj.getId());

            } catch (SQLException ex) {
                System.out.println("error in RestaurantMapper.insert query.");
                throw ex;
            }
        }
    }

    public void insert_restaurants(ArrayList<Restaurant>restaurants) {
        for (Restaurant restaurant : restaurants){
            try {
                if (find(restaurant.getId()) != null) {
                    System.out.println("Restaurant Already Exists\n");
                    continue;
                }
                insert(restaurant);
            } catch (SQLException se) {
                System.out.println("SQL Exception in inserting restaurant");
                continue;
            }
        }
    }

    public void insert_foodparty_restaurants(ArrayList<Restaurant>restaurants) {
        for (Restaurant restaurant: restaurants){
            try {
                Restaurant existedRestaurant = find(restaurant.getId());
                if(existedRestaurant != null) {
                    FoodPartyFoodMapper.getInstance().insert_foodparty_foods(restaurant.getMenu().getFoodPartyFoods(), MenuMapper.getInstance().find_menu_id(restaurant));
                    continue;
                }
                insert(restaurant);
            } catch(SQLException se) {
                System.out.println("SQL Exception in inserting foodparty restaurant");
                continue;
            }
        }
    }

    public void delete(String id) throws SQLException {
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "delete from " + TABLE_NAME + " where id = (?)"
             )
        ) {
            ps.setString(1, id);
            try {
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("error in RestaurantMapper.delete query.");
                throw ex;
            }
        }
    }

    private Restaurant convertResultSetToObject(ResultSet rs) throws SQLException {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(rs.getString(1));
        restaurant.setName(rs.getString(2));
        restaurant.setLogo(rs.getString(3));
        restaurant.setDescription(rs.getString(4));
        restaurant.setLocation(LocationMapper.getInstance().find(rs.getInt(5)));

        restaurant.setMenu(MenuMapper.getInstance().find(restaurant));

        return restaurant;
    }
}

