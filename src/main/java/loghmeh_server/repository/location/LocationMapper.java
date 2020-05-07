package loghmeh_server.repository.location;

import loghmeh_server.repository.ConnectionPool;
import loghmeh_server.repository.Mapper;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;


public class LocationMapper extends Mapper {
    private static LocationMapper locationMapper = null;


    private static final String COLUMNS = "x, y";
    private static final String TABLE_NAME = "locations";

    private Map<Integer, Location> loadedMap = new HashMap<Integer, Location>();

    public static LocationMapper getInstance() {
        if(locationMapper == null){
            locationMapper = new LocationMapper();
        }
        return locationMapper;
    }

    public Location find(int id) throws SQLException {
        Location result = loadedMap.get(id);
        if (result != null)
            return result;

        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "select " + COLUMNS + " from " + TABLE_NAME + " where id = (?)"
             )
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet;
            try {
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next())
                    return convertResultSetToObject(resultSet);
                else
                    return null;
            } catch (SQLException ex) {
                System.out.println("error in Mapper.findByID query.");
                throw ex;
            }
        }
    }

    public int find(float x, float y) throws SQLException {
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "select id from " + TABLE_NAME + " where x = (?) and y = (?)"
             )
        ) {
            preparedStatement.setFloat(1, x);
            preparedStatement.setFloat(2, y);
            ResultSet resultSet;
            try {
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next())
                    return resultSet.getInt(1);
                else
                    return -1;
            } catch (SQLException ex) {
                System.out.println("error in Mapper.findByID query.");
                throw ex;
            }
        }
    }

    public void insert(Location obj) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into " + TABLE_NAME + "(" + COLUMNS + ")" + " values (?, ?)"
             )
        ) {
            preparedStatement.setFloat(1, obj.getX());
            preparedStatement.setFloat(2, obj.getY());
            try {
                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("error in Location Mapper.insert query.");
                throw ex;
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void delete(int id) throws SQLException {
        this.delete(TABLE_NAME, id);
    }

    private Location convertResultSetToObject(ResultSet rs) throws SQLException {
        return  new Location(
                rs.getFloat(1),
                rs.getFloat(2)
        );
    }
}

