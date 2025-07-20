
<div align="center">
  <h1>🏥 Hospital Management System</h1>
  <p><em>Comprehensive Java application for managing hospital operations</em></p>
</div>

---

The **Hospital Management System** is a user-friendly, modular Java application designed to streamline and manage a wide range of hospital operations. It supports patient registration, staff management, billing, consultations, lab services, pharmacy inventory, and much more. The system is built with scalability and maintainability in mind, making it suitable for both educational and practical use cases.


## ✨ Features

- **User Authentication:** Secure login for multiple user roles:
  - Admin
  - Doctor
  - Nurse
  - Pharmacist
  - Receptionist
- **Patient Management:** Register, update, search, and manage patient records with ease.
- **Staff Management:** Add, update, assign, and manage hospital staff and their roles.
- **Consultations:** Schedule, track, and manage patient consultations and medical notes.
- **Billing:** Generate, view, and manage patient bills and payment history.
- **Lab Services:** Request, track, and manage lab tests and results for patients.
- **Pharmacy:** Manage pharmacy inventory, prescriptions, and medication dispensing.
- **Assignments:** Assign staff to patients, wards, or specific tasks.
- **Role-Based Dashboards:** Custom dashboards for each user type for streamlined workflows.
- **Database Integration:** MySQL backend for persistent data storage.


## 📁 Project Structure

```
HospitalManagementSystem/
├── src/                # Java source code (organized by feature)
│   ├── auth/           # Authentication and user session
│   ├── billing/        # Billing forms and services
│   ├── consultations/  # Consultations management
│   ├── db/             # Database connection
│   ├── lab/            # Lab forms and services
│   ├── menu/           # Dashboards for each role
│   ├── model/          # Data models (Patient, User, etc.)
│   ├── patient/        # Patient management
│   ├── pharmacy/       # Pharmacy management
│   ├── prescriptions/  # Prescription management
│   ├── staff/          # Staff management
│   └── treatment/      # Treatment management
├── bin/                # Compiled Java class files
├── hospital_db (2).sql # SQL script for database setup
└── README.md           # Project documentation
```


## 🚀 Getting Started

### Prerequisites

- [Java Development Kit (JDK) 8+](https://adoptopenjdk.net/)
- [MySQL Server](https://dev.mysql.com/downloads/mysql/) or compatible database

### Setup Instructions

1. **Clone the Repository**
   ```sh
   git clone <repository-url>
   cd <project-directory>
   ```
2. **Database Setup**
   - Open MySQL Workbench or your preferred SQL client.
   - Import the `hospital_db (2).sql` file to create the database and tables.
3. **Configure Database Connection**
   - Edit `src/db/DB.java` to match your MySQL username, password, and database URL.
4. **Compile the Project**
   - Open a terminal in the `HospitalManagementSystem` directory and run:
     ```sh
     javac -d bin src/**/*.java
     ```
5. **Run the Application**
   - From the `bin` directory, start the main menu:
     ```sh
     java MainMenu
     ```


## 🖥️ Usage

1. **Login:**
   - Enter your credentials on the login screen. Each user role will see a custom dashboard.
2. **Navigation:**
   - Use the sidebar or main menu to access different modules (Patients, Staff, Billing, Lab, Pharmacy, etc.).
3. **Forms & Actions:**
   - Add, update, or search for records using the provided forms.
   - Assign staff, generate bills, request lab tests, and manage prescriptions as needed.
4. **Logout:**
   - Always log out after your session for security.

> **Tip:** For best results, ensure your database server is running before launching the application.


## 🤝 Contributing

Contributions are welcome! If you have suggestions, bug reports, or want to add features:

1. Fork the repository
2. Create a new branch (`git checkout -b feature/your-feature`)
3. Commit your changes
4. Push to your branch and open a Pull Request

For major changes, please open an issue first to discuss your ideas.


## 📄 License
This project is for educational purposes only.

---

## 📬 Support & Contact

- For questions or support, please open an issue in the repository.
- For database or setup issues, check the comments in `hospital_db (2).sql` and `src/db/DB.java`.
- For feature requests, use the Issues or Discussions tab.


