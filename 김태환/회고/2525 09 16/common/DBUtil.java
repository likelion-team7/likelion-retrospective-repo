package net.likelion.common;

import java.sql.*;

public class DBUtil {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/likedb";
        String user = "root";
        String password = "root1234";

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

    public static void close(ResultSet rs) {
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
