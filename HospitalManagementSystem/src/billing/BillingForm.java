// billing/BillingForm.java
package billing;

import javax.swing.*;
import java.awt.*;

public class BillingForm extends JFrame {
    private final int patientId;

    public BillingForm(int patientId) {
        this.patientId = patientId;

        setTitle("Billing & Payment - Patient ID: " + patientId);
        setSize(800, 500);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("Patient Billing Summary");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(270, 20, 400, 30);
        add(title);

        JLabel amountLabel = new JLabel("Total Amount (RWF):");
        amountLabel.setBounds(150, 100, 200, 30);
        JTextField amountField = new JTextField();
        amountField.setBounds(350, 100, 200, 30);
        amountField.setEditable(false);
        add(amountLabel); add(amountField);

        JLabel methodLabel = new JLabel("Payment Method:");
        methodLabel.setBounds(150, 150, 200, 30);
        JComboBox<String> methodBox = new JComboBox<>(new String[]{"Cash", "Credit Card", "Insurance"});
        methodBox.setBounds(350, 150, 200, 30);
        add(methodLabel); add(methodBox);

        JButton payBtn = new JButton("Process Payment");
        payBtn.setBounds(300, 220, 200, 40);
        add(payBtn);

        try {
            double total = BillingService.calculateTotal(patientId);
            amountField.setText(String.format("%.2f", total));
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error calculating total: " + ex.getMessage());
        }

        payBtn.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                String method = (String) methodBox.getSelectedItem();

                BillingService.savePayment(patientId, amount, method);
                BillingService.generateBillFile(patientId, method);
                JOptionPane.showMessageDialog(this, "Payment recorded. Bill saved as file.");
                dispose();
                // Done!
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Payment error: " + ex.getMessage());
            }
        });
    }
}
