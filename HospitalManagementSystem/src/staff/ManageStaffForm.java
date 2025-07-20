//package staff;
//
//import javax.swing.*;
//import db.DB;
//import java.sql.*;
//
//public class ManageStaffForm extends JFrame {
//    // Helper method to get role_id by role name from roles table
//    private int getRoleIdByName(Connection conn, String roleName) throws SQLException {
//        String sql = "SELECT id FROM roles WHERE name = ?";
//        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, roleName);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    return rs.getInt("id");
//                } else {
//                    throw new SQLException("Role not found: " + roleName);
//                }
//            }
//        }
//    }
//
//    public ManageStaffForm() {
//        setTitle("Add Staff Member");
//        setSize(400, 450);
//        setLayout(null);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        JLabel nameL = new JLabel("Name:");
//        JLabel roleL = new JLabel("Role:");
//        JLabel specL = new JLabel("Specialization:");
//        JLabel phoneL = new JLabel("Phone:");
//        JLabel addressL = new JLabel("Address:");
//        JLabel usernameL = new JLabel("Username:");
//        JLabel passwordL = new JLabel("Password:");
//
//        JTextField nameF = new JTextField();
//        JComboBox<String> roleF = new JComboBox<>(new String[]{
//            "Doctor", "Nurse", "Pharmacist", "Lab Technician", "Receptionist"
//        });
//        JTextField specF = new JTextField();
//        JTextField phoneF = new JTextField();
//        JTextField addressF = new JTextField();
//        JTextField usernameF = new JTextField();
//        JPasswordField passwordF = new JPasswordField();
//
//        JButton saveBtn = new JButton("Add Staff");
//
//        nameL.setBounds(50, 30, 100, 25); nameF.setBounds(160, 30, 160, 25);
//        roleL.setBounds(50, 70, 100, 25); roleF.setBounds(160, 70, 160, 25);
//        specL.setBounds(50, 110, 100, 25); specF.setBounds(160, 110, 160, 25);
//        phoneL.setBounds(50, 150, 100, 25); phoneF.setBounds(160, 150, 160, 25);
//        addressL.setBounds(50, 190, 100, 25); addressF.setBounds(160, 190, 160, 25);
//        usernameL.setBounds(50, 230, 100, 25); usernameF.setBounds(160, 230, 160, 25);
//        passwordL.setBounds(50, 270, 100, 25); passwordF.setBounds(160, 270, 160, 25);
//        saveBtn.setBounds(120, 320, 150, 30);
//
//        add(nameL); add(nameF);
//        add(roleL); add(roleF);
//        add(specL); add(specF);
//        add(phoneL); add(phoneF);
//        add(addressL); add(addressF);
//        add(usernameL); add(usernameF);
//        add(passwordL); add(passwordF);
//        add(saveBtn);
//
//        saveBtn.addActionListener(e -> {
//            String name = nameF.getText().trim();
//            String roleName = (String) roleF.getSelectedItem();
//            String specialization = specF.getText().trim();
//            String phone = phoneF.getText().trim();
//            String address = addressF.getText().trim();
//            String username = usernameF.getText().trim();
//            String password = new String(passwordF.getPassword());
//
//            if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Name, Username, and Password are required.");
//                return;
//            }
//
//            try (Connection conn = DB.getConnection()) {
//                conn.setAutoCommit(false);
//
//                // Get role_id for selected role name
//                int roleId = getRoleIdByName(conn, roleName);
//
//                // Insert into staff table with role_id, availability=true, created_at=NOW()
//                PreparedStatement staffStmt = conn.prepareStatement(
//                    "INSERT INTO staff (name, role_id, specialization, phone, address, availability, created_at) VALUES (?, ?, ?, ?, ?, ?, NOW())",
//                    Statement.RETURN_GENERATED_KEYS
//                );
//                staffStmt.setString(1, name);
//                staffStmt.setInt(2, roleId);
//                staffStmt.setString(3, specialization);
//                staffStmt.setString(4, phone);
//                staffStmt.setString(5, address);
//                staffStmt.setBoolean(6, true);  // Assuming available by default
//                staffStmt.executeUpdate();
//
//                ResultSet keys = staffStmt.getGeneratedKeys();
//                int staffId = -1;
//                if (keys.next()) {
//                    staffId = keys.getInt(1);
//                }
//
//                // Insert into users table with role_id and staff_id FK
//                PreparedStatement userStmt = conn.prepareStatement(
//                    "INSERT INTO users (username, password, role_id, staff_id) VALUES (?, ?, ?, ?)"
//                );
//                userStmt.setString(1, username);
//                // TODO: Hash password before storing in production!
//                userStmt.setString(2, password);
//                userStmt.setInt(3, roleId);
//                userStmt.setInt(4, staffId);
//                userStmt.executeUpdate();
//
//                conn.commit();
//
//                JOptionPane.showMessageDialog(this, "Staff and User account created successfully.");
//                dispose();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
//            }
//        });
//    }
//}
package staff;

import javax.swing.*;
import db.DB;
import java.sql.*;

public class ManageStaffForm extends JFrame {
    // Helper method to get role_id by role name from roles table
    private int getRoleIdByName(Connection conn, String roleName) throws SQLException {
        String sql = "SELECT id FROM roles WHERE name = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, roleName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    throw new SQLException("Role not found: " + roleName);
                }
            }
        }
    }

    public ManageStaffForm() {
        setTitle("Add Staff Member");
        setSize(400, 450);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel nameL = new JLabel("Name:");
        JLabel roleL = new JLabel("Role:");
        JLabel specL = new JLabel("Specialization:");
        JLabel phoneL = new JLabel("Phone:");
        JLabel addressL = new JLabel("Address:");
        JLabel usernameL = new JLabel("Username:");
        JLabel passwordL = new JLabel("Password:");

        JTextField nameF = new JTextField();
        JComboBox<String> roleF = new JComboBox<>(new String[]{
            "Doctor", "Nurse", "Pharmacist", "Lab Technician", "Receptionist"
        });
        JTextField specF = new JTextField();
        JTextField phoneF = new JTextField();
        JTextField addressF = new JTextField();
        JTextField usernameF = new JTextField();
        JPasswordField passwordF = new JPasswordField();

        JButton saveBtn = new JButton("Add Staff");

        nameL.setBounds(50, 30, 100, 25); nameF.setBounds(160, 30, 160, 25);
        roleL.setBounds(50, 70, 100, 25); roleF.setBounds(160, 70, 160, 25);
        specL.setBounds(50, 110, 100, 25); specF.setBounds(160, 110, 160, 25);
        phoneL.setBounds(50, 150, 100, 25); phoneF.setBounds(160, 150, 160, 25);
        addressL.setBounds(50, 190, 100, 25); addressF.setBounds(160, 190, 160, 25);
        usernameL.setBounds(50, 230, 100, 25); usernameF.setBounds(160, 230, 160, 25);
        passwordL.setBounds(50, 270, 100, 25); passwordF.setBounds(160, 270, 160, 25);
        saveBtn.setBounds(120, 320, 150, 30);

        add(nameL); add(nameF);
        add(roleL); add(roleF);
        add(specL); add(specF);
        add(phoneL); add(phoneF);
        add(addressL); add(addressF);
        add(usernameL); add(usernameF);
        add(passwordL); add(passwordF);
        add(saveBtn);

        saveBtn.addActionListener(e -> {
            String name = nameF.getText().trim();
            String roleName = (String) roleF.getSelectedItem();
            String specialization = specF.getText().trim();
            String phone = phoneF.getText().trim();
            String address = addressF.getText().trim();
            String username = usernameF.getText().trim();
            String password = new String(passwordF.getPassword());

            if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name, Username, and Password are required.");
                return;
            }

            try (Connection conn = DB.getConnection()) {
                conn.setAutoCommit(false);

                // Insert staff info WITHOUT role_id (just name, spec, phone, address)
                PreparedStatement staffStmt = conn.prepareStatement(
                    "INSERT INTO staff (name, specialization, phone, address) VALUES (?, ?, ?, ?)"
                );
                staffStmt.setString(1, name);
                staffStmt.setString(2, specialization);
                staffStmt.setString(3, phone);
                staffStmt.setString(4, address);
                staffStmt.executeUpdate();

                // Get role_id for the selected role
                int roleId = getRoleIdByName(conn, roleName);

                // Insert user info with username, password, role_id (no staff_id)
                PreparedStatement userStmt = conn.prepareStatement(
                    "INSERT INTO users (username, password, role_id) VALUES (?, ?, ?)"
                );
                userStmt.setString(1, username);
                // TODO: hash passwords in real app!
                userStmt.setString(2, password);
                userStmt.setInt(3, roleId);
                userStmt.executeUpdate();

                conn.commit();

                JOptionPane.showMessageDialog(this, "Staff and User account created successfully.");
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }
}
