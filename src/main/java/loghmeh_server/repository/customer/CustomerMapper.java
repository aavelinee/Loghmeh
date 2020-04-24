package loghmeh_server.repository.customer;

import loghmeh_server.repository.ConnectionPool;
import loghmeh_server.repository.Mapper;
import loghmeh_server.repository.location.LocationMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CustomerMapper extends Mapper {
    private static CustomerMapper customerMapper = null;

    private static final String COLUMNS = "first_name, last_name, phone_number, email, credit, location_id";
    private static final String TABLE_NAME = "customers";
    private Map<Integer, Customer> loadedMap = new HashMap<Integer, Customer>();


    public static CustomerMapper getInstance() {
        if(customerMapper == null){
            customerMapper = new CustomerMapper();
        }
        return customerMapper;
    }

    public Customer findByCellphone(String phone_number) throws SQLException {
        Customer result = loadedMap.get(phone_number);
        if (result != null)
            return result;
        try (Connection con = ConnectionPool.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "select " + "id, " + COLUMNS + " from " + TABLE_NAME + " where phone_number = (?)"
            )
        ) {
            ps.setString(1, phone_number);
            try {
                ResultSet resultSet = ps.executeQuery();
                if(resultSet.next())
                    return convertResultSetToObject(resultSet);
                else
                    return null;
            } catch (SQLException ex) {
                System.out.println("error in CustomerMapper.findByCellphone query.");
                throw ex;
            }
        }
    }

    public Customer find(int id) throws SQLException {
        Customer result = loadedMap.get(id);
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
                System.out.println("error in CustomerMapper.findByID query.");
                throw ex;
            }
        }
    }

    public int find_customer_id(Customer obj) {
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "select " + "id from " + TABLE_NAME + " where phone_number = (?)"
             )
        ) {
            ps.setString(1, obj.getPhoneNumber());
            try {
                ResultSet resultSet = ps.executeQuery();
                if(resultSet.next())
                    return resultSet.getInt(1);
                else
                    return -1;
            } catch (SQLException ex) {
                System.out.println("error in CustomerMapper.find customer id query.");
                throw ex;
            }
        } catch(SQLException ex) {
            System.out.println("SQLExcepion in finding customer id");
            return -1;
        }
    }

    public void insert(Customer obj) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement (
                    "insert into " + TABLE_NAME + "(" + COLUMNS + ")" + "values (?, ?, ?, ?, ?, ?)"
            )
        ) {
            ps.setString(1, obj.getFirstName());
            ps.setString(2, obj.getLastName());
            if(findByCellphone(obj.getPhoneNumber()) != null) {
                System.out.println("Cellphone exist");
                return;
            }
            ps.setString(3, obj.getPhoneNumber());
            ps.setString(4, obj.getEmail());
            ps.setFloat(5, obj.getCredit());
            int locationId = LocationMapper.getInstance().find(obj.getLocation().getX(), obj.getLocation().getY());
            if(locationId == -1) {
                LocationMapper.getInstance().insert(obj.getLocation());
                locationId = LocationMapper.getInstance().find(obj.getLocation().getX(), obj.getLocation().getY());
            }
            ps.setInt(6, locationId);

            try {
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("error in CustomerMapper.insert query.");
                throw ex;
            }
        }
    }

    public void delete(int id) throws SQLException {
        this.delete(TABLE_NAME, id);
    }

    public void update_credit(int id, float credit) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement (
                     "update " + TABLE_NAME + " set credit = (?) where id = (?)"
             )
        ){
            ps.setInt(2, id);
            ps.setFloat(1, credit);
            try {
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("error in CustomerMapper.update_credit query.");
                throw ex;
            }

        } catch(SQLException ex) {
            System.out.println("SQL Exception in updating credit");
        }
    }

    private Customer convertResultSetToObject(ResultSet rs) throws SQLException {
        Customer customer = new Customer();

        customer.setCustomerId(rs.getInt(1));
        customer.setFirstName(rs.getString(2));
        customer.setLastName(rs.getString(3));
        customer.setPhoneNumber(rs.getString(4));
        customer.setEmail(rs.getString(5));
        customer.setCredit(rs.getFloat(6));
        customer.setLocation(LocationMapper.getInstance().find(rs.getInt(7)));

        return customer;
    }

}




