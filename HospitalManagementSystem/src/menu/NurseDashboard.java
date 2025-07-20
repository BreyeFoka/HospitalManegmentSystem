// menu/NurseDashboard.java
package menu;

import treatment.TreatmentForm;

import javax.swing.*;

public class NurseDashboard extends JFrame {
    public NurseDashboard() {
        setTitle("Nurse Dashboard");
        setSize(400, 220);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("Welcome, Nurse");
        title.setBounds(130, 20, 250, 30);
        title.setFont(title.getFont().deriveFont(18f));
        add(title);

        JButton treatmentBtn = new JButton("Log Patient Treatment");
        treatmentBtn.setBounds(100, 80, 180, 30);

        treatmentBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter Patient ID:");
            try {
                int id = Integer.parseInt(input);
                new treatment.TreatmentForm(id).setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Patient ID");
            }
        });

        add(treatmentBtn);
    }
}
