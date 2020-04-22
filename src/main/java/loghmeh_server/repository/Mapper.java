package loghmeh_server.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Mapper {
    protected void delete(String TABLE_NAME, int id) throws SQLException {
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
}
