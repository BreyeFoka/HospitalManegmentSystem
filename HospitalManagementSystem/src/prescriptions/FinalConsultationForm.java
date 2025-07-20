// prescription/FinalConsultationForm.java
package prescriptions;

import javax.swing.*;
import java.awt.*;

public class FinalConsultationForm extends JFrame {
    private final int patientId;

    public FinalConsultationForm(int patientId) {
        this.patientId = patientId;

        setTitle("Final Consultation & Prescription - Patient ID: " + patientId);
        setSize(900, 700);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("Review Lab Results & Prescribe Medication");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(250, 20, 500, 30);
        add(title);

        JLabel labLabel = new JLabel("Lab Results:");
        labLabel.setBounds(50, 70, 200, 25);
        JTextArea labResults = new JTextArea();
        labResults.setLineWrap(true);
        labResults.setEditable(false);
        JScrollPane labScroll = new JScrollPane(labResults);
        labScroll.setBounds(50, 100, 780, 150);
        add(labLabel); add(labScroll);

        JLabel diagLabel = new JLabel("Final Diagnosis:");
        diagLabel.setBounds(50, 270, 200, 25);
        JTextArea diagArea = new JTextArea();
        diagArea.setLineWrap(true);
        JScrollPane diagScroll = new JScrollPane(diagArea);
        diagScroll.setBounds(50, 300, 780, 80);
        add(diagLabel); add(diagScroll);

        JLabel medLabel = new JLabel("Prescribed Medicines:");
        medLabel.setBounds(50, 400, 200, 25);
        JTextArea medArea = new JTextArea();
        medArea.setLineWrap(true);
        JScrollPane medScroll = new JScrollPane(medArea);
        medScroll.setBounds(50, 430, 780, 100);
        add(medLabel); add(medScroll);

        JButton prescribeBtn = new JButton("Prescribe & Send to Pharmacy");
        prescribeBtn.setBounds(300, 560, 300, 40);
        add(prescribeBtn);

        try {
            labResults.setText(PrescriptionService.getLabResults(patientId));
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading lab results: " + ex.getMessage());
        }

        prescribeBtn.addActionListener(e -> {
            try {
                String diagnosis = diagArea.getText();
                String meds = medArea.getText();
                if (diagnosis.isEmpty() || meds.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill both diagnosis and medicines.");
                    return;
                }
                PrescriptionService.savePrescription(patientId, diagnosis, meds);
                JOptionPane.showMessageDialog(this, "Prescription saved. Proceed to Pharmacy.");
                dispose();
                // TODO: Forward to pharmacy
                // new PharmacyForm(patientId).setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }
}
