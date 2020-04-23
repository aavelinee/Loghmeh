package loghmeh_server.repository.order_item;

import loghmeh_server.repository.ConnectionPool;
import loghmeh_server.repository.Mapper;
import loghmeh_server.repository.food.FoodMapper;
import loghmeh_server.repository.order.OrderMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class OrderItemMapper extends Mapper {
    private static OrderItemMapper orderItemMapper = null;

    private static final String COLUMNS = "order_id, food_id, order_count";
    private static final String TABLE_NAME = "orderitems;";
    private Map<Integer, OrderItem> loadedMap = new HashMap<Integer, OrderItem>();

    public static OrderItemMapper getInstance() {
        if(orderItemMapper == null){
            orderItemMapper = new OrderItemMapper();
        }
        return orderItemMapper;
    }

    public OrderItem find(int id) throws SQLException {
        OrderItem result = loadedMap.get(id);
        if (result != null)
            return result;

        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "select " + COLUMNS + " from " + TABLE_NAME + " where id = (?)"
             )
        ) {
            ps.setInt(1, id);
            try {
                ResultSet resultSet = ps.executeQuery();
                if(resultSet.next())
                    return convertResultSetToObject(resultSet);
                else
                    return null;
            } catch (SQLException ex) {
                System.out.println("error in OrderItemMapper.findByID query.");
                throw ex;
            }
        }
    }

    public void insert(OrderItem obj) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement (
                     "insert into" + TABLE_NAME + "(" + COLUMNS + ")" + "values (?, ?, ?)"
             )
        ) {
            ps.setInt(1, obj.getOrder().getId());
//            ps.setString(2, FoodMapper.getInstance().);
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

    private OrderItem convertResultSetToObject(ResultSet rs) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(OrderMapper.getInstance().find(rs.getInt(1)));
        orderItem.setFood(FoodMapper.getInstance().find(rs.getInt(2), orderItem.getOrder().getRestaurant().getMenu()));
        orderItem.setOrderCount(rs.getInt(3));

        return orderItem;
    }
}
