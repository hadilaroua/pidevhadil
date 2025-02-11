package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/hacksphere";  // Replace with your DB URL
    private static final String USER = "root";  // Replace with your DB username
    private static final String PASSWORD = "";  // Replace with your DB password

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new SQLException("Failed to connect to the database");
            }
        }
        return connection;
    }
}
