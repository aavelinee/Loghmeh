package loghmeh_server.repository.customer;

import loghmeh_server.repository.ConnectionPool;
import loghmeh_server.repository.Mapper;
import loghmeh_server.repository.location.LocationMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CustomerMapper extends Mapper {
    private static CustomerMapper customerMapper = null;

    private static final String COLUMNS = "first_name, last_name, phone_number, email, credit, location_id, password";
    private static final String TABLE_NAME = "customers";


    public static CustomerMapper getInstance() {
        if(customerMapper == null){
            customerMapper = new CustomerMapper();
        }
        return customerMapper;
    }

    public Customer findByEmail(String email) throws SQLException {
        try (Connection con = ConnectionPool.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "select " + "id, " + COLUMNS + " from " + TABLE_NAME + " where email = (?)"
            )
        ) {
            ps.setString(1, email);
            try {
                ResultSet resultSet = ps.executeQuery();
                System.out.println("after exec.");
                if(resultSet.next())
                    return convertResultSetToObject(resultSet);
                else
                    return null;
            } catch (SQLException ex) {
                System.out.println("error in CustomerMapper.findByEmail query.");
                throw ex;
            }
        }
    }

    public Customer find(int id) throws SQLException {
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

    public int find(String email) throws SQLException{
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "select id from " + TABLE_NAME + " where email = (?)"
             )
        ) {
            ps.setString(1, email);

            try {
                ResultSet resultSet = ps.executeQuery();
                if(resultSet.next())
                    return resultSet.getInt(1);
                else
                    return -1;
            } catch (SQLException ex) {
                System.out.println("error in CustomerMapper find id query.");
                throw ex;
            }
        }
    }

    public Customer find(String email, String hashedPassword) {
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "select " + "id, " + COLUMNS + " from " + TABLE_NAME + " where email = (?) and password = (?)"
             )
        ) {
            ps.setString(1, email);
            ps.setString(2, hashedPassword);
            try {
                ResultSet resultSet = ps.executeQuery();
                if(resultSet.next())
                    return convertResultSetToObject(resultSet);
                else
                    return null;
            } catch (SQLException ex) {
                System.out.println("error in CustomerMapper.find by email and password query.");
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("error in finding customer with such email and password");
            return null;
        }
    }


    public void insert(Customer obj) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement (
                    "insert into " + TABLE_NAME + "(" + COLUMNS + ")" + "values (?, ?, ?, ?, ?, ?, ?)"
            )
        ) {
            ps.setString(1, obj.getFirstName());
            ps.setString(2, obj.getLastName());
            ps.setString(3, obj.getPhoneNumber());
            if(findByEmail(obj.getEmail()) != null) {
                System.out.println("Email exist");
                throw new SQLException();
            }
            ps.setString(4, obj.getEmail());
            ps.setFloat(5, obj.getCredit());
            int locationId = LocationMapper.getInstance().find(obj.getLocation().getX(), obj.getLocation().getY());
            System.out.println("loc IDDDD: " + locationId);
            if(locationId == -1) {
                LocationMapper.getInstance().insert(obj.getLocation());
                locationId = LocationMapper.getInstance().find(obj.getLocation().getX(), obj.getLocation().getY());
            }
            System.out.println("loc after!!" + locationId);
            ps.setInt(6, locationId);
            ps.setString(7, obj.getPassword());

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
        customer.setPassword(rs.getString(8));

        return customer;
    }

}




