package loghmeh_server.repository;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;


public class ConnectionPool {
//    private static BasicDataSource dataSource = new BasicDataSource();
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();



    static {
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        // remote db
//        dataSource.setUrl("jdbc:mysql://localhost:3306/Loghmeh?useUnicode=true&characterEncoding=UTF-8");
//        dataSource.setUsername("root");
//        dataSource.setPassword("password");
//        dataSource.setMinIdle(1);
//        dataSource.setMaxIdle(5);
//        dataSource.setMaxOpenPreparedStatements(200);

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/Loghmeh?useUnicode=true&characterEncoding=UTF-8");
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
