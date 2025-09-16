package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3307/likedb";
        String username = "root";
        String password = "root1234";
        return DriverManager.getConnection(url, username, password);
    }

}
