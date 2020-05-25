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

public class RestaurantMapper extends Mapper {
    private static RestaurantMapper restaurantMapper = null;


    private static final String COLUMNS = "id, name, logo, description, location_id";
    private static final String TABLE_NAME = "restaurants";


    public static RestaurantMapper getInstance() {
        if(restaurantMapper == null){
            restaurantMapper = new RestaurantMapper();
        }
        return restaurantMapper;
    }


    public Restaurant find(String id) {
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "select " + COLUMNS + " from " + TABLE_NAME + " where id = (?)"
             )
        ) {

            ps.setString(1, id);
            try {
                ResultSet resultSet = ps.executeQuery();
                if(resultSet.next()) {
                    return convertResultSetToObject(resultSet);
                }
                else{
                    return null;
                }
            } catch (SQLException ex) {
                System.out.println("SQL Exception in RestaurantMapper.findByID query.");
                throw ex;
            }
        }catch (SQLException ex) {
            System.out.println("SQL Exception in RestaurantMapper.findByID query.");
            return null;
        }
    }

    public ArrayList<Restaurant> find_restaurants(String type, int page) {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        String stmt;
        int limit = 8;
        int offset = limit * (page - 1);
        if(type.equals("ordinary")){
            stmt = "select id from " + TABLE_NAME + " limit 8 offset " + offset;
        } else if(type.equals("foodparty")) {
            stmt = "select id from " + TABLE_NAME;
        } else {
            return null;
        }
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                stmt
             )
        ){
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
            return restaurants;
        }
        return restaurants;
    }



    public ArrayList<Restaurant> find_searched_restaurants(String restaurant_name, String food_name, int page) {
        ArrayList<Restaurant> found_restaurants = new ArrayList<>();
        int limit = 8;
        int offset = limit * (page - 1);
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "select distinct selected_restaurants.id" +
                     " from (select * from restaurants where locate((?), name) > 0)as selected_restaurants inner join (menus) on selected_restaurants.id = menus.restaurant_id" +
                     " inner join (select * from foods where locate((?), name) > 0)as selected_foods on  menus.id = selected_foods.menu_id limit " + limit + " offset " + offset
             )
        ) {
            ps.setString(1, restaurant_name);
            ps.setString(2, food_name);
            try {
                ResultSet resultSet = ps.executeQuery();
                while(resultSet.next()) {
                    Restaurant restaurant = find(resultSet.getString(1));
                    if(restaurant != null)
                        found_restaurants.add(restaurant);
                }
            } catch (SQLException ex) {
                System.out.println("error in RestaurantMapper.search query.");
                throw ex;
            }

        }catch (SQLException ex) {
            System.out.println("SQL Exception in restaurant search");
            return found_restaurants;
        }
        return found_restaurants;
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
            int locationId = LocationMapper.getInstance().find(obj.getLocation().getX(), obj.getLocation().getY());
            if(locationId == -1) {
                LocationMapper.getInstance().insert(obj.getLocation());
                locationId = LocationMapper.getInstance().find(obj.getLocation().getX(), obj.getLocation().getY());
            }
            ps.setInt(5, locationId);

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
                    FoodPartyFoodMapper.getInstance().insert_foodparty_foods(restaurant.getMenu().getFoodPartyFoods(), MenuMapper.getInstance().find_menu_id(existedRestaurant));
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

