
package pharmacy;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PharmacyForm extends JFrame {
 private final int patientId;

 public PharmacyForm(int patientId) {
     this.patientId = patientId;

     setTitle("Pharmacy - Dispense Medicines - Patient ID: " + patientId);
     setSize(900, 700);
     setLayout(null);
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     setLocationRelativeTo(null);

     JLabel title = new JLabel("Dispense Prescribed Medicines");
     title.setFont(new Font("Arial", Font.BOLD, 20));
     title.setBounds(270, 20, 500, 30);
     add(title);

     try {
         List<String> meds = PharmacyService.getPrescribedMedicines(patientId);
         if (meds.isEmpty()) {
             JOptionPane.showMessageDialog(this, "No prescriptions found for this patient.");
             dispose();
             return;
         }

         int y = 80;
         Map<String, JTextField> qtyMap = new HashMap<>();

         for (String med : meds) {
             JLabel label = new JLabel("Medicine: " + med);
             label.setBounds(100, y, 300, 25);
             JTextField qtyField = new JTextField("1");
             qtyField.setBounds(400, y, 100, 25);
             qtyMap.put(med, qtyField);

             add(label);
             add(qtyField);
             y += 40;
         }

         JButton dispenseBtn = new JButton("Dispense All");
         dispenseBtn.setBounds(350, y + 20, 200, 40);
         add(dispenseBtn);

         dispenseBtn.addActionListener(e -> {
             try {
                 for (Map.Entry<String, JTextField> entry : qtyMap.entrySet()) {
                     String med = entry.getKey();
                     int qty = Integer.parseInt(entry.getValue().getText());
                     PharmacyService.dispenseMedicine(patientId, med, qty);
                 }
                 JOptionPane.showMessageDialog(this, "Medicines dispensed. Send patient to treatment.");
                 dispose();
                 // Optionally: new TreatmentForm(patientId).setVisible(true);
             } catch (Exception ex) {
                 ex.printStackTrace();
                 JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
             }
         });

     } catch (Exception ex) {
         ex.printStackTrace();
         JOptionPane.showMessageDialog(this, "Error loading prescriptions: " + ex.getMessage());
     }
 }
}
