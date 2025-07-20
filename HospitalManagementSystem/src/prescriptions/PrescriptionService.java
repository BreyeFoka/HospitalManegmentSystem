// prescription/PrescriptionService.java
package prescriptions;

import db.DB;

import java.sql.*;

public class PrescriptionService {
    public static String getLabResults(int patientId) throws Exception {
        Connection conn = DB.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT test_name, result FROM lab_results WHERE patient_id = ?");
        stmt.setInt(1, patientId);
        ResultSet rs = stmt.executeQuery();

        StringBuilder results = new StringBuilder();
        while (rs.next()) {
            results.append(rs.getString("test_name")).append(": ").append(rs.getString("result")).append("\n");
        }
        return results.toString();
    }

    public static void savePrescription(int patientId, String diagnosis, String medicines) throws Exception {
        Connection conn = DB.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO prescriptions (patient_id, diagnosis, prescribed_medicines) VALUES (?, ?, ?)");
        stmt.setInt(1, patientId);
        stmt.setString(2, diagnosis);
        stmt.setString(3, medicines);
        stmt.executeUpdate();
    }
}
