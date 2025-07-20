
package auth;

import db.DB;
import model.User;

import java.sql.*;

public class AuthService {
    public static User login(String username, String password) {
        try (Connection conn = DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT u.*, r.name AS role FROM users u JOIN roles r ON u.role_id = r.id WHERE u.username=? AND u.password=?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("username"), rs.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
