// db/DB.java
package db;
import java.sql.*;

public class DB {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/hospital_db", "root", "");
    }
}