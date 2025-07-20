// lab/LabService.java
package lab;

import db.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LabService {
    public static List<String> getRequestedTests(int patientId) throws Exception {
        Connection conn = DB.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT required_lab_tests FROM consultations WHERE patient_id = ?");
        stmt.setInt(1, patientId);
        ResultSet rs = stmt.executeQuery();
        List<String> tests = new ArrayList<>();

        if (rs.next()) {
            String testString = rs.getString("required_lab_tests");
            if (testString != null && !testString.isEmpty()) {
                for (String t : testString.split(",")) {
                    tests.add(t.trim());
                }
            }
        }
        return tests;
    }

    public static void saveLabResult(int patientId, String testName, String result) throws Exception {
        Connection conn = DB.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO lab_results (patient_id, test_name, result) VALUES (?, ?, ?)");
        stmt.setInt(1, patientId);
        stmt.setString(2, testName);
        stmt.setString(3, result);
        stmt.executeUpdate();
    }
}
