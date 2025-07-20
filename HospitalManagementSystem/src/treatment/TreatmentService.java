// treatment/TreatmentService.java
package treatment;

import db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TreatmentService {
    public static void logTreatment(int patientId, String type, String notes) throws Exception {
        Connection conn = DB.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO treatments (patient_id, treatment_type, notes) VALUES (?, ?, ?)"
        );
        stmt.setInt(1, patientId);
        stmt.setString(2, type);
        stmt.setString(3, notes);
        stmt.executeUpdate();
    }
}
