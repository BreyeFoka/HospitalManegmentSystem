// admin/AdminDashboard.java
package admin;

import staff.ManageStaffForm;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {
    public AdminDashboard() {
        setTitle("Admin Dashboard - Hospital Management");
        setSize(400, 300);
        setLayout(new GridLayout(3, 1, 10, 10));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel title = new JLabel("Welcome, Admin", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        JButton addStaffBtn = new JButton("➕ Add Doctor/Nurse");
        JButton viewLogsBtn = new JButton("📜 View System Logs (optional)");

        addStaffBtn.addActionListener(e -> new ManageStaffForm().setVisible(true));
        viewLogsBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Logs coming soon."));

        add(title);
        add(addStaffBtn);
        add(viewLogsBtn);
    }
}
