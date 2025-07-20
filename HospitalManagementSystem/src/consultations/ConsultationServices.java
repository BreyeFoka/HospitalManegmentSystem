// consultation/ConsultationService.java
package consultations;

import db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ConsultationServices {
    public static void saveConsultation(int patientId, String symptoms, String notes, String labTests) throws Exception {
        Connection conn = DB.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO consultations (patient_id, symptoms, doctor_notes, required_lab_tests) VALUES (?, ?, ?, ?)"
        );
        stmt.setInt(1, patientId);
        stmt.setString(2, symptoms);
        stmt.setString(3, notes);
        stmt.setString(4, labTests);
        stmt.executeUpdate();
    }
}
