// treatment/TreatmentForm.java
package treatment;

import javax.swing.*;
import java.awt.*;

public class TreatmentForm extends JFrame {
    private final int patientId;

    public TreatmentForm(int patientId) {
        this.patientId = patientId;

        setTitle("Nurse Station - Treatment Entry - Patient ID: " + patientId);
        setSize(900, 650);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("Patient Treatment / Nursing Log");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(300, 20, 400, 30);
        add(title);

        JLabel typeLabel = new JLabel("Type of Treatment:");
        typeLabel.setBounds(100, 80, 200, 25);
        JComboBox<String> typeBox = new JComboBox<>(new String[]{
            "Injection", "IV Drip", "Perfusion", "Vitals Monitoring", "Oxygen", "Other"
        });
        typeBox.setBounds(300, 80, 300, 25);

        JLabel notesLabel = new JLabel("Nurse Notes / Observations:");
        notesLabel.setBounds(100, 130, 300, 25);
        JTextArea notesArea = new JTextArea();
        notesArea.setLineWrap(true);
        JScrollPane notesScroll = new JScrollPane(notesArea);
        notesScroll.setBounds(100, 160, 700, 200);

        JButton logBtn = new JButton("Log Treatment");
        logBtn.setBounds(350, 400, 200, 40);

        add(typeLabel);
        add(typeBox);
        add(notesLabel);
        add(notesScroll);
        add(logBtn);

        logBtn.addActionListener(e -> {
            try {
                String treatment = (String) typeBox.getSelectedItem();
                String notes = notesArea.getText();
                if (notes.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter nurse notes.");
                    return;
                }
                TreatmentService.logTreatment(patientId, treatment, notes);
                JOptionPane.showMessageDialog(this, "Treatment logged successfully. Patient ready for billing.");
                dispose();
                // Optionally: new BillingForm(patientId).setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }
}
