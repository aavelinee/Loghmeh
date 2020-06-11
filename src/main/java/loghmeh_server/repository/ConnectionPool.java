package loghmeh_server.repository;

import java.sql.Connection;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;


public class ConnectionPool {
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        dataSource.setJdbcUrl("jdbc:mysql://loghmeh-mysql:3306/loghmeh?useUnicode=true&characterEncoding=UTF-8");
        dataSource.setUser("root");
        dataSource.setPassword("password");

        dataSource.setInitialPoolSize(5);
        dataSource.setMinPoolSize(5);
        dataSource.setAcquireIncrement(5);
        dataSource.setMaxPoolSize(100);
        dataSource.setMaxStatements(200);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private ConnectionPool() {
    }

}
