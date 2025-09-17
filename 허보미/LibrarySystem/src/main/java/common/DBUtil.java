package common;

import java.sql.*;

public class DBUtil {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/librarydb"; //DBMS들 마다 원하는 url 형식이 조금씩 다르더라.
        String user = "libraryuser";
        String password = "lib1234!";

        return DriverManager.getConnection(url, user, password);
    }

    public Connection getConnection( String user, String password) {
        return null;
    }

    public static void close(Connection conn) {

        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void close( PreparedStatement pstmt) {
        if(pstmt != null){
            try {
                pstmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void close(Connection conn, PreparedStatement pstmt) {
        if(pstmt != null){
            try {
                pstmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        close(conn);
    }

    public static void close(ResultSet rs) {
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        close(conn, pstmt);
    }
}
