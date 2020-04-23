package loghmeh_server.repository.order;

import loghmeh_server.repository.ConnectionPool;
import loghmeh_server.repository.Mapper;
import loghmeh_server.repository.customer.CustomerMapper;
import loghmeh_server.repository.delivery.DeliveryMapper;
import loghmeh_server.repository.restaurant.RestaurantMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class OrderMapper extends Mapper {
    private static OrderMapper orderMapper = null;

    private static final String COLUMNS = "id, status, customer_id, restaurant_id, delivery_id, " +
            "estimated_delivery_time, delivery_date, total_price";
    private static final String TABLE_NAME = "orders";
    private Map<Integer, Order> loadedMap = new HashMap<Integer, Order>();

    public static OrderMapper getInstance() {
        if(orderMapper == null){
            orderMapper = new OrderMapper();
        }
        return orderMapper;
    }

    public Order find(int id) throws SQLException {
        Order result = loadedMap.get(id);
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
                System.out.println("error in OrderMapper.findByID query.");
                throw ex;
            }
        }
    }

    public void insert(Order obj) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement (
                     "insert into" + TABLE_NAME + "(" + COLUMNS + ")" + "values (?, ?, ?, ?, ?, ?, ?)"
             )
        ) {
//            ps.setString(2, FoodMapper.getInstance().find());
            ps.setInt(3, obj.getCustomer().getCustomerId());
            ps.setString(4, obj.getRestaurant().getId());
            ps.setString(5, obj.getDelivery().getId());
            ps.setDouble(6, obj.getEstimatedDeliveryTime());
//            ps.setDate(7, obj.getDeliveryDate());
            ps.setFloat(8, obj.getTotalPrice());

            try {
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("error in OrderMapper.insert query.");
                throw ex;
            }
        }
    }

    public void delete(int id) throws SQLException {
        this.delete(TABLE_NAME, id);
    }

    private Order convertResultSetToObject(ResultSet rs) throws SQLException {
        Order order = new Order();

        order.setId(rs.getInt(1));
//        order.setStatus(rs.);
        order.setCustomer(CustomerMapper.getInstance().find(rs.getInt(3)));
        order.setRestaurant(RestaurantMapper.getInstance().find(rs.getString(4)));
        order.setDelivery(DeliveryMapper.getInstance().find(rs.getString(5)));
        order.setEstimatedDeliveryTime(rs.getDouble(6));
        order.setDeliveryDate(rs.getDate(7));
        order.setTotalPrice(rs.getFloat(8));

        return order;
    }
}
