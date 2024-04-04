package persistence;

import java.sql.*;

public class Database {
    protected static Connection connect() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbUrl = System.getenv("DB_URL");
            String localUrl = "localhost:3306";
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + dbUrl + "/user_db?serverTimezone=UTC",
                    "root", "student");
            System.out.println("Connected to the database.");
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }
}