package loghmeh_server.repository.location;

import loghmeh_server.repository.ConnectionPool;
import loghmeh_server.repository.Mapper;
import java.sql.*;


public class LocationMapper extends Mapper<Location, Integer> implements ILocationMapper {

    private static final String COLUMNS = " id, x, y ";
    private static final String TABLE_NAME = "location_table";

    private Boolean doManage;

    public LocationMapper(Boolean doManage) throws SQLException {
        if (this.doManage = doManage) {
            Connection con = ConnectionPool.getConnection();
            Statement st = con.createStatement();
            st.executeUpdate(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
            st.executeUpdate(String.format(
                    "CREATE TABLE  %s " +
                            "(" +
                            "id integer PRIMARY KEY, " +
                            "x float" +
                            "y float" +
                            ");",
                    TABLE_NAME));
            st.close();
            con.close();
        }
    }

    @Override
    protected String getFindStatement(Integer id) {
        return "SELECT " + COLUMNS +
                " FROM " + TABLE_NAME +
                " WHERE id = "+ id.toString() + ";";
    }

    @Override
    protected String getInsertStatement(Location location) {
        return "INSERT INTO " + TABLE_NAME +
                "(" + COLUMNS + ")" + " VALUES "+
                "("+
                "id INT PRIMARY KEY AUTO_INCREMENT ," +
                '"' + location.getX().toString() + ',' +
                '"' + location.getY().toString() + '"' +
                ");";
    }

    @Override
    protected String getDeleteStatement(Integer id) {
        return "DELETE FROM " + TABLE_NAME +
                " WHERE id = " + id.toString() + ";";
    }

    @Override
    protected Location convertResultSetToObject(ResultSet rs) throws SQLException {
        return  new Location(
                rs.getFloat(2),
                rs.getFloat(3)
        );
    }

}

