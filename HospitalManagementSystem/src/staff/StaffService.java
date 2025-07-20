package staff;

import db.DB;
import java.sql.*;

public class StaffService {
    public static void addStaff(Staff s) throws Exception {
        Connection conn = DB.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO staff (name, role, specialization, phone, address) VALUES (?, ?, ?, ?, ?)");
        stmt.setString(1, s.getName());
        stmt.setString(2, s.getRole());
        stmt.setString(3, s.getSpecialization());
        stmt.setString(4, s.getPhone());
        stmt.setString(5, s.getAddress());
        stmt.executeUpdate();
    }
}