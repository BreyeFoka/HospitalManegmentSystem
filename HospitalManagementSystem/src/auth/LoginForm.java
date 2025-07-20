package auth;

import db.DB;
import model.User;

import javax.swing.*;
import java.sql.*;

public class LoginForm extends JFrame {

    public LoginForm() {
        setTitle("Hospital Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(320, 180);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 30, 80, 25);
        add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(110, 30, 160, 25);
        add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 60, 80, 25);
        add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(110, 60, 160, 25);
        add(passField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(100, 100, 100, 30);
        add(loginBtn);

        loginBtn.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            User user = AuthService.login(username, password);
            if (user != null) {
                UserSession.setLoggedInUser(user);
                JOptionPane.showMessageDialog(this, "Login successful. Role: " + user.getRole());
                dispose();
                launchRoleDashboard(user);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
        });
    }

    private void launchRoleDashboard(User user) {
        switch (user.getRole()) {
            case "Admin":
                new admin.AdminDashboard().setVisible(true);
                break;
            case "Doctor":
                int doctorPatientId = promptPatientId();
                new menu.DoctorDashboard(doctorPatientId).setVisible(true);
                break;
            case "Nurse":
                int nursePatientId = promptPatientId();
                new treatment.TreatmentForm(nursePatientId).setVisible(true);
                break;
            case "Pharmacist":
                int pharmPatientId = promptPatientId();
                new pharmacy.PharmacyForm(pharmPatientId).setVisible(true);
                break;
            case "Lab Technician":
                int labPatientId = promptPatientId();
                new lab.LabForm(labPatientId).setVisible(true);
                break;
            case "Receptionist":
                new menu.ReceptionDashboard().setVisible(true);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Unknown role: " + user.getRole());
        }
    }

    private int promptPatientId() {
        String input = JOptionPane.showInputDialog(this, "Enter Patient ID:");
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Using ID = 1");
            return 1;
        }
    }

    public static void main(String[] args) {
        new LoginForm().setVisible(true);
    }
}
