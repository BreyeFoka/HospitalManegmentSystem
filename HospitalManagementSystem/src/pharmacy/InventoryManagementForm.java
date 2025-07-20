// pharmacy/InventoryManagementForm.java
package pharmacy;

import db.DB;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class InventoryManagementForm extends JFrame {
    public InventoryManagementForm() {
        setTitle("Medicine Inventory Manager");
        setSize(500, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel nameL = new JLabel("Medicine Name:");
        JLabel qtyL = new JLabel("Quantity:");
        JLabel expiryL = new JLabel("Expiry Date (YYYY-MM-DD):");
        JLabel priceL = new JLabel("Unit Price:");

        JTextField nameF = new JTextField();
        JTextField qtyF = new JTextField();
        JTextField expiryF = new JTextField();
        JTextField priceF = new JTextField();

        JButton addBtn = new JButton("Add/Update Medicine");

        nameL.setBounds(40, 40, 150, 25); nameF.setBounds(200, 40, 200, 25);
        qtyL.setBounds(40, 80, 150, 25); qtyF.setBounds(200, 80, 200, 25);
        expiryL.setBounds(40, 120, 150, 25); expiryF.setBounds(200, 120, 200, 25);
        priceL.setBounds(40, 160, 150, 25); priceF.setBounds(200, 160, 200, 25);
        addBtn.setBounds(150, 220, 200, 35);

        add(nameL); add(nameF);
        add(qtyL); add(qtyF);
        add(expiryL); add(expiryF);
        add(priceL); add(priceF);
        add(addBtn);

        addBtn.addActionListener(e -> {
            try {
                String name = nameF.getText();
                int qty = Integer.parseInt(qtyF.getText());
                String expiry = expiryF.getText();
                double price = Double.parseDouble(priceF.getText());

                Connection conn = DB.getConnection();
                PreparedStatement check = conn.prepareStatement(
                    "SELECT id FROM inventory WHERE medicine_name = ?");
                check.setString(1, name);
                ResultSet rs = check.executeQuery();

                if (rs.next()) {
                    // Update
                    PreparedStatement update = conn.prepareStatement(
                        "UPDATE inventory SET quantity = ?, expiry_date = ?, price = ? WHERE medicine_name = ?");
                    update.setInt(1, qty);
                    update.setString(2, expiry);
                    update.setDouble(3, price);
                    update.setString(4, name);
                    update.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Medicine updated.");
                } else {
                    // Insert
                    PreparedStatement insert = conn.prepareStatement(
                        "INSERT INTO inventory (medicine_name, quantity, expiry_date, price) VALUES (?, ?, ?, ?)");
                    insert.setString(1, name);
                    insert.setInt(2, qty);
                    insert.setString(3, expiry);
                    insert.setDouble(4, price);
                    insert.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Medicine added.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }
}
