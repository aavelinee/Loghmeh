package loghmeh_server.repository;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static BasicDataSource dataSource = new BasicDataSource();

    static {
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        // remote db
        dataSource.setUrl("jdbc:mysql://localhost/Loghmeh");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        dataSource.setMinIdle(1);
        dataSource.setMaxIdle(5);
        dataSource.setMaxOpenPreparedStatements(100);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private ConnectionPool() {
    }

}
