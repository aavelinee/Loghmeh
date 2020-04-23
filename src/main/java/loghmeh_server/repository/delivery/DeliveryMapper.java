package loghmeh_server.repository.delivery;

import loghmeh_server.repository.ConnectionPool;
import loghmeh_server.repository.location.LocationMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DeliveryMapper {
    private static DeliveryMapper deliveryMapper = null;

    private static final String COLUMNS = "id, velocity, location_id";
    private static final String TABLE_NAME = "deliveries";
    private Map<Integer, Delivery> loadedMap = new HashMap<Integer, Delivery>();


    public static DeliveryMapper getInstance() {
        if(deliveryMapper == null){
            deliveryMapper = new DeliveryMapper();
        }
        return deliveryMapper;
    }


    public Delivery find(String id) throws SQLException {
        Delivery result = loadedMap.get(id);
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
                System.out.println("error in DeliveryMapper.findByID query.");
                throw ex;
            }
        }
    }

    public void insert(Delivery obj) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "insert into " + TABLE_NAME + "(" + COLUMNS + ")" + " values (?, ?, ?)"
             )
        ) {
            ps.setString(1, obj.getId());
            ps.setFloat(2, obj.getVelocity());
            LocationMapper.getInstance().insert(obj.getLocation());
            ps.setInt(3, LocationMapper.getInstance().find(obj.getLocation().getX(), obj.getLocation().getY()));
            try {
                ps.executeUpdate();

            } catch (SQLException ex) {
                System.out.println("error in DeliveryMapper.insert query.");
                throw ex;
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
                System.out.println("error in DeliveryMapper.delete query.");
                throw ex;
            }
        }
    }


    private Delivery convertResultSetToObject(ResultSet rs) throws SQLException {
        Delivery delivery = new Delivery();
        delivery.setId(rs.getString(1));
        delivery.setVelocity(rs.getInt(2));
        delivery.setLocation(LocationMapper.getInstance().find(rs.getInt(5)));

        return delivery;
    }
}
