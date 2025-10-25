🏥 Hospital Management System (JDBC + MySQL)
📘 Overview

The Hospital Management System is a simple Java-based console application designed to manage hospital operations efficiently.
It uses JDBC to connect with a MySQL database for performing CRUD (Create, Read, Update, Delete) operations on patients, doctors, and appointments.

This project demonstrates Java–MySQL integration and basic object-oriented programming principles such as classes, methods, and encapsulation.

⚙️ Features

✅ Add new patients and doctors
✅ View all patient and doctor records
✅ Book appointments between patients and doctors
✅ Validate input and prevent duplicate entries
✅ Store and retrieve data from MySQL using JDBC

🧩 Project Structure
HospitalManagementSystem/
│
├── src/hms/
│   ├── DatabaseService.java     # Handles DB connection setup
│   ├── Patient.java             # Manages patient data
│   ├── Doctor.java              # Manages doctor data
│   ├── BookAppointment.java     # Handles booking logic
│   └── HospManagementSys.java   # Main class with menu-driven UI
│
└── README.md

🗄️ Database Setup

Create a new database named Hospital in MySQL:

CREATE DATABASE Hospital;
USE Hospital;


Create required tables:

CREATE TABLE patients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    age INT,
    gender VARCHAR(10)
);

CREATE TABLE doctors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    department VARCHAR(50)
);

CREATE TABLE appointments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT,
    doctor_id INT,
    appointment_date DATE,
    FOREIGN KEY (patient_id) REFERENCES patients(id),
    FOREIGN KEY (doctor_id) REFERENCES doctors(id)
);

🔧 Technologies Used

Java (JDK 21)

Eclipse IDE

MySQL Database

JDBC Connector (MySQL Driver)

Git & GitHub for version control

🚀 How to Run the Project

Clone this repository:

git clone https://github.com/Yamuna2529/HospitalManagementSystem.git


Open it in Eclipse IDE.

Add the MySQL JDBC driver (mysql-connector-j.jar) to your project’s classpath.

Update your MySQL credentials inside DatabaseService.java:

DriverManager.getConnection("jdbc:mysql://localhost:3306/Hospital", "root", "your_password");


Run the program:

HospManagementSys.java


Use the console menu to add/view patients, doctors, and appointments.

📊 Sample Output
=== Hospital Management System ===
1. Add Patient
2. Add Doctor
3. View Patients
4. View Doctors
5. Book Appointment
6. Exit
Enter your choice: 1
Enter Patient Name: Yamuna
Enter Patient Age: 25
Enter Patient Gender: Female
Patient details added successfully.

🤝 Contributing

Feel free to fork this project and make improvements!
Pull requests are welcome for bug fixes, new features, or database enhancements.
