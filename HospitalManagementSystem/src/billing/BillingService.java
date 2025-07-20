// billing/BillingService.java
package billing;

import db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.FileWriter;
import java.time.LocalDate;

public class BillingService {
    // Just example costs; these could come from a config or DB in full system
    private static final double CONSULT_COST = 5000;
    private static final double LAB_TEST_COST = 3000;
    private static final double MED_COST_PER_ITEM = 2000;
    private static final double TREATMENT_COST = 2500;

    public static double calculateTotal(int patientId) throws Exception {
        Connection conn = DB.getConnection();
        double total = 0;

        // Consultation
        PreparedStatement consult = conn.prepareStatement("SELECT COUNT(*) FROM consultations WHERE patient_id = ?");
        consult.setInt(1, patientId);
        ResultSet r1 = consult.executeQuery();
        if (r1.next()) total += r1.getInt(1) * CONSULT_COST;

        // Lab Tests
        PreparedStatement labs = conn.prepareStatement("SELECT COUNT(*) FROM lab_results WHERE patient_id = ?");
        labs.setInt(1, patientId);
        ResultSet r2 = labs.executeQuery();
        if (r2.next()) total += r2.getInt(1) * LAB_TEST_COST;

     // Medications (using real prices)
        PreparedStatement meds = conn.prepareStatement(
            "SELECT quantity_issued, unit_price FROM dispensed_medicines WHERE patient_id = ?");
        meds.setInt(1, patientId);
        ResultSet r3 = meds.executeQuery();
        while (r3.next()) {
            int qty = r3.getInt("quantity_issued");
            double unitPrice = r3.getDouble("unit_price");
            total += qty * unitPrice;
        }

        // Treatment
        PreparedStatement treat = conn.prepareStatement("SELECT COUNT(*) FROM treatments WHERE patient_id = ?");
        treat.setInt(1, patientId);
        ResultSet r4 = treat.executeQuery();
        if (r4.next()) total += r4.getInt(1) * TREATMENT_COST;

        return total;
    }

    public static void savePayment(int patientId, double amount, String method) throws Exception {
        Connection conn = DB.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO bills (patient_id, total_amount, payment_method) VALUES (?, ?, ?)");
        stmt.setInt(1, patientId);
        stmt.setDouble(2, amount);
        stmt.setString(3, method);
        stmt.executeUpdate();
    }
    public static void generateBillFile(int patientId, String paymentMethod) throws Exception {
        Connection conn = DB.getConnection();

        // Get patient name
        PreparedStatement nameStmt = conn.prepareStatement("SELECT name FROM patients WHERE id = ?");
        nameStmt.setInt(1, patientId);
        ResultSet nameRs = nameStmt.executeQuery();
        if (!nameRs.next()) throw new Exception("Patient not found.");
        String fullName = nameRs.getString("name").replaceAll("\\s+", "_");

        StringBuilder bill = new StringBuilder();
        bill.append("========= HOSPITAL INVOICE =========\n");
        bill.append("Patient ID: ").append(patientId).append("\n");
        bill.append("Name: ").append(nameRs.getString("name")).append("\n");
        bill.append("Date: ").append(LocalDate.now()).append("\n");
        bill.append("------------------------------------\n");

        double total = 0;

        // Consultations
        PreparedStatement consult = conn.prepareStatement("SELECT COUNT(*) FROM consultations WHERE patient_id = ?");
        consult.setInt(1, patientId);
        ResultSet r1 = consult.executeQuery();
        if (r1.next()) {
            int count = r1.getInt(1);
            double cost = count * 5000;
            total += cost;
            bill.append(String.format("Consultations (%d) .......... %.2f\n", count, cost));
        }

        // Lab Tests
        PreparedStatement labs = conn.prepareStatement("SELECT COUNT(*) FROM lab_results WHERE patient_id = ?");
        labs.setInt(1, patientId);
        ResultSet r2 = labs.executeQuery();
        if (r2.next()) {
            int count = r2.getInt(1);
            double cost = count * 3000;
            total += cost;
            bill.append(String.format("Lab Tests (%d) ............... %.2f\n", count, cost));
        }

        // Medicines
        PreparedStatement meds = conn.prepareStatement(
            "SELECT medicine_name, quantity_issued, unit_price FROM dispensed_medicines WHERE patient_id = ?");
        meds.setInt(1, patientId);
        ResultSet r3 = meds.executeQuery();
        double medTotal = 0;
        while (r3.next()) {
            String med = r3.getString("medicine_name");
            int qty = r3.getInt("quantity_issued");
            double price = r3.getDouble("unit_price");
            double subtotal = qty * price;
            medTotal += subtotal;
            bill.append(String.format("Medicine: %s x%d @ %.2f .... %.2f\n", med, qty, price, subtotal));
        }
        total += medTotal;

        // Treatments
        PreparedStatement treat = conn.prepareStatement("SELECT COUNT(*) FROM treatments WHERE patient_id = ?");
        treat.setInt(1, patientId);
        ResultSet r4 = treat.executeQuery();
        if (r4.next()) {
            int count = r4.getInt(1);
            double cost = count * 2500;
            total += cost;
            bill.append(String.format("Treatments (%d) .............. %.2f\n", count, cost));
        }

        bill.append("------------------------------------\n");
        bill.append(String.format("TOTAL AMOUNT: .............. %.2f\n", total));
        bill.append("Payment Method: ").append(paymentMethod).append("\n");
        bill.append("====================================\n");

        // Save to file
        String filename = "bills/" + fullName + "_Bill.txt";
        java.io.File dir = new java.io.File("bills");
        if (!dir.exists()) dir.mkdirs();

        try (FileWriter fw = new FileWriter(filename)) {
            fw.write(bill.toString());
        }
    }
    
}
