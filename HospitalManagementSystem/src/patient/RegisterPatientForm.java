package patient;

import model.Patient;
//import consultation.ConsultationForm;

import javax.swing.*;

public class RegisterPatientForm extends JFrame {
    public RegisterPatientForm() {
        setTitle("Hospital Registration Desk - New Patient");
        setSize(800, 600); // 🧱 Bigger UI window
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 🏷️ Labels
        JLabel title = new JLabel("Register New Patient");
        title.setBounds(300, 20, 300, 30);
        title.setFont(title.getFont().deriveFont(18.0f));

        JLabel nameL = new JLabel("Full Name:");
        JLabel ageL = new JLabel("Age:");
        JLabel genderL = new JLabel("Gender:");
        JLabel addressL = new JLabel("Full Address:");
        JLabel phoneL = new JLabel("Phone Number:");
        JCheckBox emergencyCheck = new JCheckBox("Emergency Case");

        // 🖊️ Fields
        JTextField nameF = new JTextField();
        JTextField ageF = new JTextField();
        JComboBox<String> genderF = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        JTextField addressF = new JTextField();
        JTextField phoneF = new JTextField();
        JButton registerBtn = new JButton("Register and Send to Doctor");

        // 📐 Positioning
        int labelX = 200, fieldX = 350, width = 250, height = 30;
        int y = 80, gap = 50;

        nameL.setBounds(labelX, y, 120, height); nameF.setBounds(fieldX, y, width, height); y += gap;
        ageL.setBounds(labelX, y, 120, height); ageF.setBounds(fieldX, y, width, height); y += gap;
        genderL.setBounds(labelX, y, 120, height); genderF.setBounds(fieldX, y, width, height); y += gap;
        addressL.setBounds(labelX, y, 120, height); addressF.setBounds(fieldX, y, width, height); y += gap;
        phoneL.setBounds(labelX, y, 120, height); phoneF.setBounds(fieldX, y, width, height); y += gap;
        emergencyCheck.setBounds(fieldX, y, width, height); y += gap;
        registerBtn.setBounds(300, y, 250, 40);

        // 🧩 Add all
        add(title);
        add(nameL); add(nameF);
        add(ageL); add(ageF);
        add(genderL); add(genderF);
        add(addressL); add(addressF);
        add(phoneL); add(phoneF);
        add(emergencyCheck);
        add(registerBtn);

        // 🖱️ Button action
        registerBtn.addActionListener(e -> {
            try {
                Patient p = new Patient(
                    nameF.getText(),
                    Integer.parseInt(ageF.getText()),
                    (String) genderF.getSelectedItem(),
                    addressF.getText(),
                    phoneF.getText(),
                    emergencyCheck.isSelected()
                );
                int patientId = PatientService.registerPatient(p);
                JOptionPane.showMessageDialog(this, "Patient registered! ID: " + patientId);

                // Forward to consultation
                dispose();
//                new ConsultationForm(patientId).setVisible(true);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Registration Error: " + ex.getMessage());
            }
        });
    }
}
