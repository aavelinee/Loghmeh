import loghmeh_server.repository.ConnectionPool;
import loghmeh_server.repository.location.ILocationMapper;
import loghmeh_server.repository.location.Location;
import loghmeh_server.repository.location.LocationMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.List;

public class test {

    private Location test;
    private ILocationMapper mapper;


    @Before
    public void setUp() throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement pStatement = connection.prepareStatement(
//                "insert into locations (x, y) values (?, ?)");
//        pStatement.setFloat(1, 0.0f);
//        pStatement.setFloat(2, 0.0f);
                "insert into customers (first_name, last_name, email, phone_number, credit, location_id) values (?, ?, ?, ?, ?, ?)");
        pStatement.setString(1, "احسان");
        pStatement.setString(2, "خامس‌پناه");
        pStatement.setString(3, "ekhamespanah@yahoo.com");
        pStatement.setString(4, "۰۹۱۲۳۴۵۶۷۸۹");
        pStatement.setFloat(5, 0.0f);
        pStatement.setInt(6, 1);
        pStatement.executeUpdate();
        pStatement.close();
        connection.close();
    }

    @Test
    public void whenLoaded_find() throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(
                "select first_name from customers where email = \"" + "ekhamespanah@yahoo.com" + "\"");
        result.next();
        String first_name = result.getString("first_name");
        result.close();
        statement.close();
        connection.close();
        System.out.println(first_name);
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