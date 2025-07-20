package pharmacy;

import db.DB;

import java.sql.*;
import java.util.*;

public class PharmacyService {
    public static List<String> getPrescribedMedicines(int patientId) throws Exception {
        Connection conn = DB.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT prescribed_medicines FROM prescriptions WHERE patient_id = ?");
        stmt.setInt(1, patientId);
        ResultSet rs = stmt.executeQuery();

        List<String> meds = new ArrayList<>();
        if (rs.next()) {
            String data = rs.getString("prescribed_medicines");
            if (data != null) {
                for (String m : data.split(",")) {
                    meds.add(m.trim());
                }
            }
        }
        return meds;
    }

    public static int getStock(String medName) throws Exception {
        Connection conn = DB.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT quantity FROM inventory WHERE medicine_name = ?");
        stmt.setString(1, medName);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("quantity");
        }
        return 0;
    }

    public static void dispenseMedicine(int patientId, String medName, int qty) throws Exception {
        Connection conn = DB.getConnection();
        conn.setAutoCommit(false);
        try {
        	PreparedStatement priceStmt = conn.prepareStatement("SELECT price FROM inventory WHERE medicine_name = ?");
        	priceStmt.setString(1, medName);
        	ResultSet rs = priceStmt.executeQuery();
        	double unitPrice = 0;
        	if (rs.next()) unitPrice = rs.getDouble("price");

        	PreparedStatement insert = conn.prepareStatement(
        	    "INSERT INTO dispensed_medicines (patient_id, medicine_name, quantity_issued, unit_price) VALUES (?, ?, ?, ?)");
        	insert.setInt(1, patientId);
        	insert.setString(2, medName);
        	insert.setInt(3, qty);
        	insert.setDouble(4, unitPrice);
        	insert.executeUpdate();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }
}
