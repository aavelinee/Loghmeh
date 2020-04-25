package loghmeh_server.repository.order;

import loghmeh_server.repository.ConnectionPool;
import loghmeh_server.repository.Mapper;
import loghmeh_server.repository.customer.Customer;
import loghmeh_server.repository.customer.CustomerMapper;
import loghmeh_server.repository.delivery.Delivery;
import loghmeh_server.repository.delivery.DeliveryMapper;
import loghmeh_server.repository.order_item.OrderItem;
import loghmeh_server.repository.order_item.OrderItemMapper;
import loghmeh_server.repository.restaurant.RestaurantMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderMapper extends Mapper {
    private static OrderMapper orderMapper = null;

    private static final String COLUMNS = "status, customer_id, restaurant_id, delivery_id," +
            " estimated_delivery_time, delivery_date, total_price";
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
                     "select " + "id, " + COLUMNS + " from " + TABLE_NAME + " where id = (?)"
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

    public ArrayList<Order> find_orders(Customer customer) {
        ArrayList<Order> orders = new ArrayList<>();
//        int customer_id = CustomerMapper.getInstance().find_customer_id(customer);
//        if(customer_id == -1)
//            return orders;
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "select id from " + TABLE_NAME + " where customer_id = (?)"
             )
        ) {
            try {
                ps.setInt(1, customer.getCustomerId());
                ResultSet resultSet = ps.executeQuery();
                while(resultSet.next()) {
                    try {
                        Order order = find(resultSet.getInt(1));
                        if(order != null) {
                            orders.add(order);
                        }
                    } catch (SQLException ex) {
                        System.out.println("SQL Exception in OrderMapper.finddeliveries query.");
                        continue;
                    }
                }
            } catch (SQLException ex) {
                System.out.println("SQL Exception in DeliveryMapper.finddeliveries query.");
                throw ex;
            }
        } catch (SQLException ex) {
            return orders;
        }
        return orders;
    }

    public Order find_cart(Customer customer) throws SQLException{
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "select " + "id, " + COLUMNS + " from " + TABLE_NAME +
                             " where status = 'Ordering' and customer_id = (?)"
             )
        ) {
            ps.setInt(1, customer.getCustomerId());
            try {
                ResultSet resultSet = ps.executeQuery();
                if(resultSet.next())
                    return convertResultSetToObject(resultSet);
                else
                    return null;
            } catch (SQLException ex) {
                System.out.println("error in OrderMapper.findLastOrder query.");
                throw ex;
            }
        }
    }

    public void insert(Order obj) throws SQLException {
        if (obj == null) {
            System.out.println("nulll");
        }
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement (
                     "insert into " + TABLE_NAME + "(" + "status, customer_id, restaurant_id, total_price" + ")" + "values (?, ?, ?, ?)"
             )
        ) {
            ps.setString(1, obj.getStatus().toString());
            ps.setInt(2, obj.getCustomer().getCustomerId());
            ps.setString(3, obj.getRestaurant().getId());
            ps.setFloat(4, obj.getTotalPrice());

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

    public void update_total_price(int id, float total_price) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement (
                     "update " + TABLE_NAME + " set total_price = (?) where id = (?)"
             )
        ){
            ps.setInt(2, id);
            ps.setFloat(1, total_price);
            try {
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("error in OrderMapper.update_totalprice query.");
                throw ex;
            }

        } catch(SQLException ex) {
            System.out.println("SQL Exception in updating total price");
        }
    }

    public void update_status(int id, Order.orderStatus status) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement (
                     "update " + TABLE_NAME + " set status = (?) where id = (?)"
             )
        ){

            ps.setInt(2, id);
            ps.setString(1, status.toString());
            try {
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("error in OrderMapper.update order status query.");
                throw ex;
            }

        } catch(SQLException ex) {
            System.out.println("SQL Exception in updating order status");
        }
    }

    public void update_delivery_info(int id, String delivery_id, double estimated_delivery_time) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement (
                     "update " + TABLE_NAME + " set delivery_id = (?), estimated_delivery_time = (?), " +
                             "delivery_date = now() where id = (?)"
             )
        ){

            ps.setInt(3, id);
            ps.setString(1, delivery_id);
            ps.setDouble(2, estimated_delivery_time);

            try {
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("error in OrderMapper.update delivery info query.");
                throw ex;
            }

        } catch(SQLException ex) {
            System.out.println("SQL Exception in updating delivery info");
        }
    }

    private Order convertResultSetToObject(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt(1));
        Order.orderStatus status = Order.orderStatus.valueOf(rs.getString(2));
        order.setStatus(status);
        order.setCustomer(CustomerMapper.getInstance().find(rs.getInt(3)));
        order.setRestaurant(RestaurantMapper.getInstance().find(rs.getString(4)));
        order.setRestaurantId(order.getRestaurant().getId());
        order.setRestaurantName(order.getRestaurant().getName());
        order.setDelivery(DeliveryMapper.getInstance().find(rs.getString(5)));
        order.setEstimatedDeliveryTime(rs.getDouble(6));
        order.setDeliveryDate(rs.getDate(7));
        order.setTotalPrice(rs.getFloat(8));

        order.setOrders(OrderItemMapper.getInstance().find_orderitems(order));

        return order;
    }
}
