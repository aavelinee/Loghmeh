package loghmeh_server.repository.location;

import loghmeh_server.repository.ConnectionPool;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;


public class LocationMapper {

    private static final String COLUMNS = "x, y";
    private static final String TABLE_NAME = "locations";

    private Map<Integer, Location> loadedMap = new HashMap<Integer, Location>();

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
                resultSet.next();
                return convertResultSetToObject(resultSet);
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
                System.out.println("error in Mapper.insert query.");
                throw ex;
            }
        }
    }

    public void delete(int id) throws SQLException {
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "delete from " + TABLE_NAME + " where id = (?)"
             )
        ) {
            preparedStatement.setInt(1, id);
            try {
                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("error in Mapper.delete query.");
                throw ex;
            }
        }
    }

    private Location convertResultSetToObject(ResultSet rs) throws SQLException {
        return  new Location(
                rs.getFloat(1),
                rs.getFloat(2)
        );
    }
}

