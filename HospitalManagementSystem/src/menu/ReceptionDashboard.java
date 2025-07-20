// menu/ReceptionDashboard.java
package menu;

import patient.RegisterPatientForm;
import billing.BillingForm;

import javax.swing.*;

public class ReceptionDashboard extends JFrame {
    public ReceptionDashboard() {
        setTitle("Receptionist Dashboard");
        setSize(400, 250);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("Welcome, Receptionist");
        title.setBounds(100, 20, 250, 30);
        title.setFont(title.getFont().deriveFont(18f));
        add(title);

        JButton regBtn = new JButton("Register Patient");
        regBtn.setBounds(100, 70, 180, 30);
        JButton billBtn = new JButton("Billing & Payment");
        billBtn.setBounds(100, 120, 180, 30);

        regBtn.addActionListener(e -> new RegisterPatientForm().setVisible(true));
        billBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter Patient ID:");
            try {
                int id = Integer.parseInt(input);
                new BillingForm(id).setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID");
            }
        });

        add(regBtn);
        add(billBtn);
    }
}
