// lab/LabForm.java
package lab;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class LabForm extends JFrame {
    private final int patientId;

    public LabForm(int patientId) {
        this.patientId = patientId;

        setTitle("Laboratory - Patient ID: " + patientId);
        setSize(900, 650);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("Lab Results Entry");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(320, 20, 300, 30);
        add(title);

        try {
            java.util.List<String> tests = LabService.getRequestedTests(patientId);

            if (tests.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No lab tests requested for this patient.");
                dispose();
                return;
            }

            int y = 80;
            Map<String, JTextArea> testResultMap = new HashMap<>();

            for (String test : tests) {
                JLabel label = new JLabel(test + " Result:");
                label.setBounds(100, y, 200, 25);
                JTextArea field = new JTextArea();
                JScrollPane scroll = new JScrollPane(field);
                scroll.setBounds(300, y, 450, 60);
                field.setLineWrap(true);
                testResultMap.put(test, field);

                add(label);
                add(scroll);
                y += 80;
            }

            JButton saveBtn = new JButton("Submit Results");
            saveBtn.setBounds(350, y, 200, 40);
            add(saveBtn);

            saveBtn.addActionListener(e -> {
                try {
                    for (Map.Entry<String, JTextArea> entry : testResultMap.entrySet()) {
                        String testName = entry.getKey();
                        String result = entry.getValue().getText();
                        LabService.saveLabResult(patientId, testName, result);
                    }
                    JOptionPane.showMessageDialog(this, "Lab results saved successfully!");
                    dispose();
                    // Optionally: return to second doctor consultation
                    // new ReviewResultsForm(patientId).setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load tests: " + e.getMessage());
        }
    }
}
