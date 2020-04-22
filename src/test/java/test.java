import loghmeh_server.repository.ConnectionPool;
import loghmeh_server.repository.location.ILocationMapper;
import loghmeh_server.repository.location.Location;
import loghmeh_server.repository.location.LocationMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class test {

    private Location test;
    private ILocationMapper mapper;
    private Connection connection;

    private static final float x = 1.0f;
    private static final float y = 2.5f;

    @Before
    public void setUp() throws SQLException {
        mapper = new LocationMapper(true);
        test = new Location(x, y);
        connection = ConnectionPool.getConnection();
        mapper.insert(test);
        mapper.insert(new Location(2.0f, 3.1f));
    }

    @Test
    public void whenLoaded_find() throws SQLException {
        Location example = mapper.find(1);
        System.out.println("loaded example -> " + example.toString());
        Assert.assertTrue("loaded example is not correct", test.equals(example));
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