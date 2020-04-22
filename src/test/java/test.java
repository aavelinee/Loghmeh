import loghmeh_server.repository.ConnectionPool;
import loghmeh_server.repository.location.Location;
import loghmeh_server.repository.location.LocationMapper;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.assertEquals;

public class test {

    private Location test;

    private LocationMapper locationMapper;
    @Before
    public void setUp() throws SQLException {
//        Connection connection = ConnectionPool.getConnection();
//        PreparedStatement pStatement = connection.prepareStatement(
//                "insert into locations (x, y) values (?, ?)");
//        pStatement.setFloat(1, 0.0f);
//        pStatement.setFloat(2, 0.0f);
//                "insert into customers (first_name, last_name, email, phone_number, credit, location_id) values (?, ?, ?, ?, ?, ?)");
//        pStatement.setString(1, "احسان");
//        pStatement.setString(2, "خامس‌پناه");
//        pStatement.setString(3, "ekhamespanah@yahoo.com");
//        pStatement.setString(4, "۰۹۱۲۳۴۵۶۷۸۹");
//        pStatement.setFloat(5, 0.0f);
//        pStatement.setInt(6, 1);
//        pStatement.executeUpdate();
//        pStatement.close();
//        connection.close();
        locationMapper = new LocationMapper();
    }

    @Test
    public void whenLoaded_find() throws SQLException {
        Location location = new Location(0.0f, 0.0f);
        assertEquals (locationMapper.find(1), location);

    }
//
//    @After
//    public void terminate() throws SQLException {
//        System.out.println("deleting location");
//        mapper.delete(1);
//        mapper.delete(2);
//        connection.close();
//    }

}