package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Mysql {
    private  static DataSource dataSource;
     static {
         try {
             Properties properties = new Properties();
             properties.load(Mysql.class.getClassLoader().getResourceAsStream("druid.properties"));
             dataSource = DruidDataSourceFactory.createDataSource(properties);
             System.out.println(dataSource);
//             Class.forName("com.mysql.cj.jdbc.Driver");
//
//             DriverManager.getConnection("jdbc:mysql://localhost:3306/qgjx?serverTimezone=UTC"
//                ,"root","123456");
         }catch (Exception e) {
             e.printStackTrace();
         }
     }
     public static Connection gecon() throws SQLException {
        return dataSource.getConnection();
    }

    public static void close(Connection connection, ResultSet resultSet, PreparedStatement statement) throws SQLException {
         connection.close();
         resultSet.close();
         statement.close();
    }

    public static void close(Connection connection, PreparedStatement statement) throws SQLException {
        connection.close();
        statement.close();
    }
}
