// consultation/ConsultationForm.java
package consultations;

import javax.swing.*;
import java.awt.*;

public class ConsultationsForm extends JFrame {
    private final int patientId;

    public ConsultationsForm(int patientId) {
        this.patientId = patientId;

        setTitle("Doctor Consultation - Patient ID: " + patientId);
        setSize(900, 650);  // 🔍 Bigger window
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("Initial Doctor Consultation");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(300, 20, 400, 30);

        JLabel symptomsLabel = new JLabel("Patient Symptoms:");
        JTextArea symptomsField = new JTextArea();
        symptomsField.setLineWrap(true);
        JScrollPane symptomsPane = new JScrollPane(symptomsField);

        JLabel notesLabel = new JLabel("Doctor's Notes:");
        JTextArea notesField = new JTextArea();
        notesField.setLineWrap(true);
        JScrollPane notesPane = new JScrollPane(notesField);

        JLabel testsLabel = new JLabel("Required Lab Tests:");
        JCheckBox blood = new JCheckBox("Blood Test");
        JCheckBox urine = new JCheckBox("Urine Test");
        JCheckBox xray = new JCheckBox("X-Ray");
        JCheckBox covid = new JCheckBox("COVID Test");

        JButton saveBtn = new JButton("Send to Lab");

        // Positioning
        symptomsLabel.setBounds(100, 70, 200, 25); symptomsPane.setBounds(100, 100, 700, 80);
        notesLabel.setBounds(100, 200, 200, 25); notesPane.setBounds(100, 230, 700, 80);
        testsLabel.setBounds(100, 330, 200, 25);

        blood.setBounds(120, 360, 120, 30);
        urine.setBounds(250, 360, 120, 30);
        xray.setBounds(380, 360, 120, 30);
        covid.setBounds(510, 360, 120, 30);

        saveBtn.setBounds(330, 450, 200, 40);

        add(title);
        add(symptomsLabel); add(symptomsPane);
        add(notesLabel); add(notesPane);
        add(testsLabel);
        add(blood); add(urine); add(xray); add(covid);
        add(saveBtn);

        saveBtn.addActionListener(e -> {
            try {
                String symptoms = symptomsField.getText();
                String notes = notesField.getText();

                StringBuilder tests = new StringBuilder();
                if (blood.isSelected()) tests.append("Blood Test, ");
                if (urine.isSelected()) tests.append("Urine Test, ");
                if (xray.isSelected()) tests.append("X-Ray, ");
                if (covid.isSelected()) tests.append("COVID Test, ");
                String labTests = tests.toString().replaceAll(", $", ""); // Trim last comma

                ConsultationServices.saveConsultation(patientId, symptoms, notes, labTests);

                JOptionPane.showMessageDialog(this, "Consultation saved. Patient sent to lab.");
                dispose();
                // TODO: Launch LabForm with patientId
                // new LabForm(patientId).setVisible(true);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }
}
