// menu/PharmacistDashboard.java
package menu;

import pharmacy.PharmacyForm;

import javax.swing.*;

public class PharmacistDashboard extends JFrame {
    public PharmacistDashboard() {
        setTitle("Pharmacist Dashboard");
        setSize(400, 220);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("Welcome, Pharmacist");
        title.setBounds(100, 20, 250, 30);
        title.setFont(title.getFont().deriveFont(18f));
        add(title);

        JButton dispenseBtn = new JButton("Dispense Medicines");
        dispenseBtn.setBounds(100, 80, 180, 30);

        dispenseBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter Patient ID:");
            try {
                int id = Integer.parseInt(input);
                new pharmacy.PharmacyForm(id).setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Patient ID");
            }
        });

        add(dispenseBtn);
        JButton inventoryBtn = new JButton("🗃️ Manage Inventory");
        inventoryBtn.setBounds(100, 130, 180, 30);
        inventoryBtn.addActionListener(e -> new pharmacy.InventoryManagementForm().setVisible(true));
        add(inventoryBtn);
    }
}
