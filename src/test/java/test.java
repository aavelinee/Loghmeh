import loghmeh_server.repository.ConnectionPool;
import loghmeh_server.repository.location.ILocationMapper;
import loghmeh_server.repository.location.Location;
import loghmeh_server.repository.location.LocationMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class test {

    private Location test;
    private ILocationMapper mapper;


    @Before
    public void setUp() throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement pStatement = connection.prepareStatement(
                "insert into locations (x, y) values (?, ?)");
        pStatement.setFloat(1, 0.0f);
        pStatement.setFloat(2, 0.0f);
        pStatement.executeUpdate();
        pStatement.close();
        connection.close();
    }

    @Test
    public void whenLoaded_find() throws SQLException {

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