// assignments/AssignForm.java
package assignments;

import db.DB;

import javax.swing.*;
import java.sql.*;
import java.util.HashMap;

public class AssignForm extends JFrame {
    public AssignForm() {
        setTitle("Assign Staff to Patient");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel patientL = new JLabel("Patient:");
        JLabel doctorL = new JLabel("Doctor:");
        JLabel nurseL = new JLabel("Nurse:");

        JComboBox<String> patientCombo = new JComboBox<>();
        JComboBox<String> doctorCombo = new JComboBox<>();
        JComboBox<String> nurseCombo = new JComboBox<>();

        JButton assignBtn = new JButton("Assign");

        patientL.setBounds(50, 30, 100, 25); patientCombo.setBounds(150, 30, 180, 25);
        doctorL.setBounds(50, 70, 100, 25); doctorCombo.setBounds(150, 70, 180, 25);
        nurseL.setBounds(50, 110, 100, 25); nurseCombo.setBounds(150, 110, 180, 25);
        assignBtn.setBounds(120, 170, 150, 30);

        add(patientL); add(patientCombo);
        add(doctorL); add(doctorCombo);
        add(nurseL); add(nurseCombo);
        add(assignBtn);

        HashMap<String, Integer> patientMap = new HashMap<>();
        HashMap<String, Integer> doctorMap = new HashMap<>();
        HashMap<String, Integer> nurseMap = new HashMap<>();

        try (Connection conn = DB.getConnection()) {
            // Load Patients
            ResultSet ps = conn.createStatement().executeQuery("SELECT id, name FROM patients");
            while (ps.next()) {
                String label = ps.getString("name") + " (ID: " + ps.getInt("id") + ")";
                patientCombo.addItem(label);
                patientMap.put(label, ps.getInt("id"));
            }

            // Load Doctors
            ResultSet ds = conn.createStatement().executeQuery("SELECT id, name FROM staff WHERE role = 'Doctor'");
            while (ds.next()) {
                String label = ds.getString("name") + " (ID: " + ds.getInt("id") + ")";
                doctorCombo.addItem(label);
                doctorMap.put(label, ds.getInt("id"));
            }

            // Load Nurses
            ResultSet ns = conn.createStatement().executeQuery("SELECT id, name FROM staff WHERE role = 'Nurse'");
            while (ns.next()) {
                String label = ns.getString("name") + " (ID: " + ns.getInt("id") + ")";
                nurseCombo.addItem(label);
                nurseMap.put(label, ns.getInt("id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        assignBtn.addActionListener(e -> {
            try {
                Integer patientId = patientMap.get((String) patientCombo.getSelectedItem());
                Integer doctorId = doctorMap.getOrDefault((String) doctorCombo.getSelectedItem(), null);
                Integer nurseId = nurseMap.getOrDefault((String) nurseCombo.getSelectedItem(), null);

                AssignmentService.assignStaffToPatient(patientId, doctorId, nurseId);
                JOptionPane.showMessageDialog(this, "Assigned successfully!");
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Assignment failed: " + ex.getMessage());
            }
        });
    }
}
