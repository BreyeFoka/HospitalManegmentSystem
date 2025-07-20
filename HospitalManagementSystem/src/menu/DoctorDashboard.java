// menu/DoctorDashboard.java
package menu;

import consultations.ConsultationsForm;
import prescriptions.FinalConsultationForm;

import javax.swing.*;

public class DoctorDashboard extends JFrame {
    private int patientId;

    public DoctorDashboard(int patientId) {
        this.patientId = patientId;

        setTitle("Doctor Dashboard");
        setSize(400, 250);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("Welcome, Doctor");
        title.setBounds(130, 20, 200, 30);
        title.setFont(title.getFont().deriveFont(18f));
        add(title);

        JButton consultBtn = new JButton("Consult Patient");
        consultBtn.setBounds(100, 70, 180, 30);
        JButton finalDiagBtn = new JButton("Final Diagnosis & Prescription");
        finalDiagBtn.setBounds(100, 120, 180, 30);

        consultBtn.addActionListener(e -> new ConsultationsForm(patientId).setVisible(true));
        finalDiagBtn.addActionListener(e -> new FinalConsultationForm(patientId).setVisible(true));

        add(consultBtn);
        add(finalDiagBtn);
    }
}
