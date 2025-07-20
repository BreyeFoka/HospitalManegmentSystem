// patient/PatientService.java
package patient;

import db.DB;
import model.Patient;
import java.sql.*;

public class PatientService {
    public static int registerPatient(Patient p) throws Exception {
        Connection conn = DB.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO patients (name, age, gender, address, phone, emergency) VALUES (?, ?, ?, ?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS
        );
        stmt.setString(1, p.getName());
        stmt.setInt(2, p.getAge());
        stmt.setString(3, p.getGender());
        stmt.setString(4, p.getAddress());
        stmt.setString(5, p.getPhone());
        stmt.setBoolean(6, p.isEmergency());
        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1); // return patient ID
        } else {
            throw new Exception("Patient registration failed.");
        }
    }
}
