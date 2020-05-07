package loghmeh_server.repository.order_item;

import loghmeh_server.repository.ConnectionPool;
import loghmeh_server.repository.Mapper;
import loghmeh_server.repository.food.Food;
import loghmeh_server.repository.food.FoodMapper;
import loghmeh_server.repository.foodparty_food.FoodPartyFoodMapper;
import loghmeh_server.repository.menu.MenuMapper;
import loghmeh_server.repository.order.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class OrderItemMapper extends Mapper {
    private static OrderItemMapper orderItemMapper = null;

    private static final String COLUMNS = "order_id, food_id, order_count";
    private static final String TABLE_NAME = "orderitems";

    public static OrderItemMapper getInstance() {
        if(orderItemMapper == null){
            orderItemMapper = new OrderItemMapper();
        }
        return orderItemMapper;
    }

    public OrderItem find(Order order, int id) throws SQLException {
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "select " + "id, " + COLUMNS + " from " + TABLE_NAME + " where id = (?)"
             )
        ) {
            ps.setInt(1, id);
            try {
                ResultSet resultSet = ps.executeQuery();
                if(resultSet.next())
                    return convertResultSetToObject(resultSet, order);
                else
                    return null;
            } catch (SQLException ex) {
                System.out.println("error in OrderItemMapper.findByID query.");
                throw ex;
            }
        }
    }

    public int find_orderitem_id(int order_id, Food food) {
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "select id from " + TABLE_NAME + " where order_id = (?) and food_id = (?)"
             )
        ) {

            int menu_id = MenuMapper.getInstance().find_menu_id(food.getMenu().getRestaurant());
            if(menu_id == -1)
                return -1;
            int food_id = FoodMapper.getInstance().find(menu_id, food.getName());
            if(food_id == -1)
                return -1;

            ps.setInt(1, order_id);
            ps.setInt(2, food_id);
            try {
                ResultSet resultSet = ps.executeQuery();
                if(resultSet.next())
                    return resultSet.getInt(1);
                else
                    return -1;
            } catch (SQLException ex) {
                System.out.println("error in OrderItemMapper.findByID from orderid and food query.");
                throw ex;
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception in finding orderitem id");
            return -1;
        }
    }

    public ArrayList<OrderItem> find_orderitems(Order order) {
        ArrayList<OrderItem> orderItems = new ArrayList<>();

        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "select id, " + COLUMNS +" from " + TABLE_NAME + " where order_id = (?)"
             )
        ) {
            ps.setInt(1, order.getId());
            try {
                ResultSet resultSet = ps.executeQuery();
                while(resultSet.next()) {
                    try {
                        OrderItem orderItem = find(order, resultSet.getInt(1));
                        if(orderItem != null) {
                            orderItems.add(orderItem);
                        }
                    } catch (SQLException ex) {
                        System.out.println("SQL Exception in OrderItemMapper.findbyorderId query.");
                        continue;
                    }
                }
            } catch (SQLException ex) {
                System.out.println("SQL Exception in OrderItemMapper.findByOrderId query.");
                throw ex;
            }
        } catch (SQLException ex) {
            return orderItems;
        }
        return orderItems;
    }

    public void insert(OrderItem obj) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement (
                     "insert into " + TABLE_NAME + "(" + COLUMNS + ")" + "values (?, ?, ?)"
             )
        ) {
            ps.setInt(1, obj.getOrder().getId());
            int menu_id = MenuMapper.getInstance().find_menu_id(obj.getOrder().getRestaurant());
            ps.setInt(2, FoodMapper.getInstance().find(menu_id, obj.getFood().getName()));
            ps.setInt(3, obj.getOrderCount());

            try {
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("error in OrderItemMapper.insert query.");
                throw ex;
            }
        }
    }

    public void delete(int id) throws SQLException {
        this.delete(TABLE_NAME, id);
    }

    public void update_orderitem_count(int order_id, Food food, int count) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement (
                     "update " + TABLE_NAME + " set order_count = (?) where id = (?)"
             )
        ){
            int orderitem_id = OrderItemMapper.getInstance().find_orderitem_id(order_id, food);
            if(orderitem_id == -1)
                return;

            ps.setInt(2, orderitem_id);
            ps.setInt(1, count);
            try {
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("error in OrderMapper.update_orderitem count query.");
                throw ex;
            }

        } catch(SQLException ex) {
            System.out.println("SQL Exception in updating orderitem count");
        }
    }

    private OrderItem convertResultSetToObject(ResultSet rs, Order order) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        if(FoodPartyFoodMapper.getInstance().is_foodparty(rs.getInt(3))){
            orderItem.setFood(FoodPartyFoodMapper.getInstance().find(rs.getInt(3), orderItem.getOrder().getRestaurant().getMenu()));
            orderItem.setIsFoodParty(true);
        }
        else{
            orderItem.setFood(FoodMapper.getInstance().find(rs.getInt(3), orderItem.getOrder().getRestaurant().getMenu()));
            orderItem.setIsFoodParty(false);
        }
        orderItem.setOrderCount(rs.getInt(4));

        return orderItem;
    }
}
