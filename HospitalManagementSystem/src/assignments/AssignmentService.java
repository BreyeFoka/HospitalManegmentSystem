// assignments/AssignmentService.java
package assignments;

import db.DB;
import java.sql.*;

public class AssignmentService {
    public static void assignStaffToPatient(int patientId, Integer doctorId, Integer nurseId) throws Exception {
        Connection conn = DB.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO assignments (patient_id, doctor_id, nurse_id) VALUES (?, ?, ?)");
        stmt.setInt(1, patientId);
        if (doctorId != null) stmt.setInt(2, doctorId); else stmt.setNull(2, java.sql.Types.INTEGER);
        if (nurseId != null) stmt.setInt(3, nurseId); else stmt.setNull(3, java.sql.Types.INTEGER);
        stmt.executeUpdate();
    }
}
