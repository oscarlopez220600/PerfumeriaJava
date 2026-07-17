package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/perfumeria?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; 
    private static final String PASS = "";     

    private static Connection conn;

    private Conexion() {}

    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(URL, USER, PASS);
        }
        return conn;
    }
}
