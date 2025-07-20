# Hospital Management System

This project is a Java-based Hospital Management System designed to streamline and manage various hospital operations, including patient registration, staff management, billing, consultations, lab services, pharmacy, and more.

## Features
- **User Authentication:** Secure login for different user roles (Admin, Doctor, Nurse, Pharmacist, Receptionist).
- **Patient Management:** Register, update, and manage patient records.
- **Staff Management:** Add, update, and manage hospital staff information.
- **Consultations:** Schedule and manage patient consultations.
- **Billing:** Generate and manage patient bills.
- **Lab Services:** Request and manage lab tests and results.
- **Pharmacy:** Manage inventory and prescriptions.
- **Assignments:** Assign staff to patients or tasks.

## Project Structure
- `src/` - Java source code organized by feature (auth, billing, consultations, lab, menu, model, patient, pharmacy, prescriptions, staff, treatment).
- `bin/` - Compiled Java class files.
- `hospital_db (2).sql` - SQL script for setting up the hospital database.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- MySQL or compatible database

### Setup
1. **Clone the repository**
2. **Database Setup:**
   - Import `hospital_db (2).sql` into your MySQL server to create the required database and tables.
3. **Configure Database Connection:**
   - Update the database connection settings in `src/db/DB.java` if needed.
4. **Compile the Project:**
   - Navigate to the `HospitalManagementSystem` directory and compile the Java files:
     ```sh
     javac -d bin src/**/*.java
     ```
5. **Run the Application:**
   - Start the application from the `bin` directory:
     ```sh
     java MainMenu
     ```

## Usage
- Log in with your credentials.
- Navigate through the menu based on your role.
- Use the provided forms to manage patients, staff, billing, lab, pharmacy, and more.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
This project is for educational purposes.
