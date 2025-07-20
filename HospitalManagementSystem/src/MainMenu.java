////// MainMenu.java
////import javax.swing.*;
////import java.awt.*;
////import patient.RegisterPatientForm;
////import consultations.ConsultationsForm;
////import lab.LabForm;
////import prescriptions.FinalConsultationForm;
////import pharmacy.PharmacyForm;
////import treatment.TreatmentForm;
////import billing.BillingForm;
////
////public class MainMenu extends JFrame {
////    public MainMenu() {
////        setTitle("🏥 Hospital Management System");
////        setSize(600, 500);
////        setLayout(new GridLayout(7, 1, 10, 10));
////        setDefaultCloseOperation(EXIT_ON_CLOSE);
////        setLocationRelativeTo(null);
////
////        JLabel title = new JLabel("Hospital Workflow Menu", SwingConstants.CENTER);
////        title.setFont(new Font("Arial", Font.BOLD, 22));
////
////        JButton regBtn = new JButton("1. Patient Registration");
////        JButton doc1Btn = new JButton("2. Doctor Consultation");
////        JButton labBtn = new JButton("3. Lab Tests");
////        JButton doc2Btn = new JButton("4. Final Diagnosis & Prescription");
////        JButton pharmBtn = new JButton("5. Pharmacy");
////        JButton treatBtn = new JButton("6. Treatment/Nurse");
////        JButton billBtn = new JButton("7. Billing & Payment");
////
////        regBtn.addActionListener(e -> new RegisterPatientForm().setVisible(true));
////        doc1Btn.addActionListener(e -> openWithId(ConsultationsForm.class));
////        labBtn.addActionListener(e -> openWithId(LabForm.class));
////        doc2Btn.addActionListener(e -> openWithId(FinalConsultationForm.class));
////        pharmBtn.addActionListener(e -> openWithId(PharmacyForm.class));
////        treatBtn.addActionListener(e -> openWithId(TreatmentForm.class));
////        billBtn.addActionListener(e -> openWithId(BillingForm.class));
////
////        add(title); add(regBtn); add(doc1Btn); add(labBtn); add(doc2Btn); add(pharmBtn); add(treatBtn); add(billBtn);
////    }
////
////    private void openWithId(Class<?> formClass) {
////        String input = JOptionPane.showInputDialog("Enter Patient ID:");
////        if (input == null) return;
////        try {
////            int id = Integer.parseInt(input);
////            if (formClass == ConsultationsForm.class) new ConsultationsForm(id).setVisible(true);
////            if (formClass == LabForm.class) new LabForm(id).setVisible(true);
////            if (formClass == FinalConsultationForm.class) new FinalConsultationForm(id).setVisible(true);
////            if (formClass == PharmacyForm.class) new PharmacyForm(id).setVisible(true);
////            if (formClass == TreatmentForm.class) new TreatmentForm(id).setVisible(true);
////            if (formClass == BillingForm.class) new BillingForm(id).setVisible(true);
////        } catch (Exception e) {
////            JOptionPane.showMessageDialog(this, "Invalid ID");
////        }
////    }
////
////    public static void main(String[] args) {
////        new MainMenu().setVisible(true);
////    }
////}
//
//// MainMenu.java
//import auth.LoginForm;
//import auth.UserSession;
//import model.User;
//import admin.AdminDashboard;
//import menu.DoctorDashboard;
//import menu.NurseDashboard;
//import menu.PharmacistDashboard;
//import menu.ReceptionDashboard;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//
//public class MainMenu extends JFrame {
//    private JButton loginBtn;
//    private JButton logoutBtn;
//    private JLabel welcomeLabel;
//
//    public MainMenu() {
//        setTitle("🏥 Hospital Management System");
//        setSize(600, 500);
//        setLayout(new BorderLayout(10,10));
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        welcomeLabel = new JLabel("Please log in", SwingConstants.CENTER);
//        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 22));
//
//        JPanel centerPanel = new JPanel(new GridLayout(7, 1, 10, 10));
//        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
//
//        final JButton regBtn = new JButton("1. Patient Registration");
//        final JButton doc1Btn = new JButton("2. Doctor Consultation");
//        final JButton labBtn = new JButton("3. Lab Tests");
//        final JButton doc2Btn = new JButton("4. Final Diagnosis & Prescription");
//        final JButton pharmBtn = new JButton("5. Pharmacy");
//        final JButton treatBtn = new JButton("6. Treatment/Nurse");
//        final JButton billBtn = new JButton("7. Billing & Payment");
//
//        regBtn.setEnabled(false);
//        doc1Btn.setEnabled(false);
//        labBtn.setEnabled(false);
//        doc2Btn.setEnabled(false);
//        pharmBtn.setEnabled(false);
//        treatBtn.setEnabled(false);
//        billBtn.setEnabled(false);
//
//        centerPanel.add(regBtn);
//        centerPanel.add(doc1Btn);
//        centerPanel.add(labBtn);
//        centerPanel.add(doc2Btn);
//        centerPanel.add(pharmBtn);
//        centerPanel.add(treatBtn);
//        centerPanel.add(billBtn);
//
//        loginBtn = new JButton("Login");
//        logoutBtn = new JButton("Logout");
//        logoutBtn.setEnabled(false);
//
//        JPanel southPanel = new JPanel();
//        southPanel.add(loginBtn);
//        southPanel.add(logoutBtn);
//
//        add(welcomeLabel, BorderLayout.NORTH);
//        add(centerPanel, BorderLayout.CENTER);
//        add(southPanel, BorderLayout.SOUTH);
//
//        loginBtn.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                final LoginForm login = new LoginForm();
//                login.setVisible(true);
//
//                login.addWindowListener(new WindowAdapter() {
//                    public void windowClosed(WindowEvent we) {
//                        User user = UserSession.getLoggedInUser();
//                        if (user != null) {
//                            welcomeLabel.setText("Welcome, " + user.getUsername() + " (" + user.getRole() + ")");
//                            enableButtonsForRole(user.getRole(), regBtn, doc1Btn, labBtn, doc2Btn, pharmBtn, treatBtn, billBtn);
//                            loginBtn.setEnabled(false);
//                            logoutBtn.setEnabled(true);
//                        }
//                    }
//                });
//            }
//        });
//
//        logoutBtn.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                UserSession.setLoggedInUser(null);
//                welcomeLabel.setText("Please log in");
//                loginBtn.setEnabled(true);
//                logoutBtn.setEnabled(false);
//
//                regBtn.setEnabled(false);
//                doc1Btn.setEnabled(false);
//                labBtn.setEnabled(false);
//                doc2Btn.setEnabled(false);
//                pharmBtn.setEnabled(false);
//                treatBtn.setEnabled(false);
//                billBtn.setEnabled(false);
//            }
//        });
//
//        regBtn.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                new patient.RegisterPatientForm().setVisible(true);
//            }
//        });
//
//        doc1Btn.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                openWithId("Doctor Consultation", new PatientIdHandler() {
//                    public void handle(int id) {
//                        new menu.DoctorDashboard(id).setVisible(true);
//                    }
//                });
//            }
//        });
//
//        labBtn.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                openWithId("Lab Tests", new PatientIdHandler() {
//                    public void handle(int id) {
//                        new lab.LabForm(id).setVisible(true);
//                    }
//                });
//            }
//        });
//
//        doc2Btn.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                openWithId("Final Diagnosis", new PatientIdHandler() {
//                    public void handle(int id) {
//                        new prescriptions.FinalConsultationForm(id).setVisible(true);
//                    }
//                });
//            }
//        });
//
//        pharmBtn.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                openWithId("Pharmacy", new PatientIdHandler() {
//                    public void handle(int id) {
//                        new pharmacy.PharmacyForm(id).setVisible(true);
//                    }
//                });
//            }
//        });
//
//        treatBtn.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                openWithId("Treatment", new PatientIdHandler() {
//                    public void handle(int id) {
//                        new treatment.TreatmentForm(id).setVisible(true);
//                    }
//                });
//            }
//        });
//
//        billBtn.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                openWithId("Billing", new PatientIdHandler() {
//                    public void handle(int id) {
//                        new billing.BillingForm(id).setVisible(true);
//                    }
//                });
//            }
//        });
//    }
//
//    private void enableButtonsForRole(String role, JButton regBtn, JButton doc1Btn, JButton labBtn, JButton doc2Btn, JButton pharmBtn, JButton treatBtn, JButton billBtn) {
//        if (role == null) return;
//
//        String r = role.toLowerCase();
//        if (r.equals("admin")) {
//            regBtn.setEnabled(true);
//            doc1Btn.setEnabled(true);
//            labBtn.setEnabled(true);
//            doc2Btn.setEnabled(true);
//            pharmBtn.setEnabled(true);
//            treatBtn.setEnabled(true);
//            billBtn.setEnabled(true);
//        } else if (r.equals("doctor")) {
//            doc1Btn.setEnabled(true);
//            doc2Btn.setEnabled(true);
//        } else if (r.equals("nurse")) {
//            treatBtn.setEnabled(true);
//        } else if (r.equals("receptionist")) {
//            regBtn.setEnabled(true);
//            billBtn.setEnabled(true);
//        } else if (r.equals("pharmacist")) {
//            pharmBtn.setEnabled(true);
//        } else if (r.equals("lab technician")) {
//            labBtn.setEnabled(true);
//        }
//    }
//
//    private void openWithId(String title, PatientIdHandler handler) {
//        String input = JOptionPane.showInputDialog(this, "Enter Patient ID for " + title + ":");
//        if (input == null) return;
//        try {
//            int id = Integer.parseInt(input);
//            handler.handle(id);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Invalid Patient ID");
//        }
//    }
//
//    // Interface to simulate functional interface for Java 7
//    private interface PatientIdHandler {
//        void handle(int id);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new MainMenu().setVisible(true);
//            }
//        });
//    }
//}
import auth.LoginForm;
import auth.UserSession;
import model.User;
import admin.AdminDashboard;
import menu.DoctorDashboard;
import menu.NurseDashboard;
import menu.PharmacistDashboard;
import menu.ReceptionDashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame {
    private JButton loginBtn;
    private JButton logoutBtn;
    private JLabel welcomeLabel;

    // Workflow buttons
    private JButton regBtn;
    private JButton doc1Btn;
    private JButton labBtn;
    private JButton doc2Btn;
    private JButton pharmBtn;
    private JButton treatBtn;
    private JButton billBtn;

    // Admin panel button
    private JButton adminPanelBtn;

    public MainMenu() {
        setTitle("🏥 Hospital Management System");
        setSize(600, 500);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        welcomeLabel = new JLabel("Please log in", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 22));

        JPanel centerPanel = new JPanel(new GridLayout(8, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        // Initialize buttons
        regBtn = new JButton("1. Patient Registration");
        doc1Btn = new JButton("2. Doctor Consultation");
        labBtn = new JButton("3. Lab Tests");
        doc2Btn = new JButton("4. Final Diagnosis & Prescription");
        pharmBtn = new JButton("5. Pharmacy");
        treatBtn = new JButton("6. Treatment/Nurse");
        billBtn = new JButton("7. Billing & Payment");

        adminPanelBtn = new JButton("Admin Panel");

        // Initially disable all buttons
        regBtn.setEnabled(false);
        doc1Btn.setEnabled(false);
        labBtn.setEnabled(false);
        doc2Btn.setEnabled(false);
        pharmBtn.setEnabled(false);
        treatBtn.setEnabled(false);
        billBtn.setEnabled(false);
        adminPanelBtn.setEnabled(false);

        // Add buttons to panel (including admin panel last)
        centerPanel.add(regBtn);
        centerPanel.add(doc1Btn);
        centerPanel.add(labBtn);
        centerPanel.add(doc2Btn);
        centerPanel.add(pharmBtn);
        centerPanel.add(treatBtn);
        centerPanel.add(billBtn);
        centerPanel.add(adminPanelBtn);

        loginBtn = new JButton("Login");
        logoutBtn = new JButton("Logout");
        logoutBtn.setEnabled(false);

        JPanel southPanel = new JPanel();
        southPanel.add(loginBtn);
        southPanel.add(logoutBtn);

        add(welcomeLabel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        // Login button action
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final LoginForm login = new LoginForm();
                login.setVisible(true);

                login.addWindowListener(new WindowAdapter() {
                    public void windowClosed(WindowEvent we) {
                        User user = UserSession.getLoggedInUser();
                        if (user != null) {
                            welcomeLabel.setText("Welcome, " + user.getUsername() + " (" + user.getRole() + ")");
                            enableButtonsForRole(user.getRole());
                            loginBtn.setEnabled(false);
                            logoutBtn.setEnabled(true);
                        }
                    }
                });
            }
        });

        // Logout button action
        logoutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserSession.setLoggedInUser(null);
                welcomeLabel.setText("Please log in");
                loginBtn.setEnabled(true);
                logoutBtn.setEnabled(false);

                // Disable all buttons on logout
                regBtn.setEnabled(false);
                doc1Btn.setEnabled(false);
                labBtn.setEnabled(false);
                doc2Btn.setEnabled(false);
                pharmBtn.setEnabled(false);
                treatBtn.setEnabled(false);
                billBtn.setEnabled(false);
                adminPanelBtn.setEnabled(false);
            }
        });

        // Button listeners for normal workflow
        regBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new patient.RegisterPatientForm().setVisible(true);
            }
        });

        doc1Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openWithId("Doctor Consultation", new PatientIdHandler() {
                    public void handle(int id) {
                        new menu.DoctorDashboard(id).setVisible(true);
                    }
                });
            }
        });

        labBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openWithId("Lab Tests", new PatientIdHandler() {
                    public void handle(int id) {
                        new lab.LabForm(id).setVisible(true);
                    }
                });
            }
        });

        doc2Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openWithId("Final Diagnosis", new PatientIdHandler() {
                    public void handle(int id) {
                        new prescriptions.FinalConsultationForm(id).setVisible(true);
                    }
                });
            }
        });

        pharmBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openWithId("Pharmacy", new PatientIdHandler() {
                    public void handle(int id) {
                        new pharmacy.PharmacyForm(id).setVisible(true);
                    }
                });
            }
        });

        treatBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openWithId("Treatment", new PatientIdHandler() {
                    public void handle(int id) {
                        new treatment.TreatmentForm(id).setVisible(true);
                    }
                });
            }
        });

        billBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openWithId("Billing", new PatientIdHandler() {
                    public void handle(int id) {
                        new billing.BillingForm(id).setVisible(true);
                    }
                });
            }
        });

        // Admin panel button opens admin dashboard
        adminPanelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new admin.AdminDashboard().setVisible(true);
            }
        });
    }

    private void enableButtonsForRole(String role) {
        // Disable all first
        regBtn.setEnabled(false);
        doc1Btn.setEnabled(false);
        labBtn.setEnabled(false);
        doc2Btn.setEnabled(false);
        pharmBtn.setEnabled(false);
        treatBtn.setEnabled(false);
        billBtn.setEnabled(false);
        adminPanelBtn.setEnabled(false);

        if (role == null) return;

        String r = role.toLowerCase();
        if (r.equals("admin")) {
            // Admin only gets Admin Panel button
            adminPanelBtn.setEnabled(true);
        } else if (r.equals("doctor")) {
            doc1Btn.setEnabled(true);
            doc2Btn.setEnabled(true);
        } else if (r.equals("nurse")) {
            treatBtn.setEnabled(true);
        } else if (r.equals("receptionist")) {
            regBtn.setEnabled(true);
            billBtn.setEnabled(true);
        } else if (r.equals("pharmacist")) {
            pharmBtn.setEnabled(true);
        } else if (r.equals("lab technician")) {
            labBtn.setEnabled(true);
        }
    }

    private void openWithId(String title, PatientIdHandler handler) {
        String input = JOptionPane.showInputDialog(this, "Enter Patient ID for " + title + ":");
        if (input == null) return;
        try {
            int id = Integer.parseInt(input);
            handler.handle(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Patient ID");
        }
    }

    // Interface to simulate functional interface for Java 7
    private interface PatientIdHandler {
        void handle(int id);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }
}
