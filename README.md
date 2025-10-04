# Java-Based Visitor Tracking and Management System

## 🎯 Project Goal

This project was developed as a final capstone for TESDA cert, demonstrating a full-stack solution for managing visitor check-ins, employee data, and user authentication within a secure system.

## 🛠️ Key Technologies Used

* **Language:** Java
* **Database:** MySQL (Local XAMPP Server)
* **IDE:** Apache NetBeans IDE
* **Dependencies:** jbcrypt (for password hashing), MySQL Connector/J (JDBC)

## ✨ Core Features

* **Secure User Management:** Implements secure administrator and receptionist login with a **3 failed login attempt lock system**.
* **Authentication Security:** Utilizes the **jbcrypt library for robust password hashing** before storing credentials in the database.
* **Visitor Tracking:** Records detailed visitor entry/exit logs with timestamps and status tracking.
* **Employee Directory:** Full **CRUD (Create, Read, Update, Delete) operations** for managing employee details within the system.
* **Advanced Architecture:** Designed with a three-layer architecture utilizing:
* **Generic Data Access Object (DAO):** Ensures efficient, reusable database communication across multiple data models.
* **Plain Old Java Object (POJO):** Models database entities for clean data transfer.
* **Input/Output (I/O) Layer:** Handles user interaction and application control flow, cleanly separated from business logic.

## 📁 Repository Structure

| Folder/File | Purpose |
| :--- | :--- |
| `src/` | Contains all primary Java source code. |
| `Visitrack/lib/` | Essential JAR dependencies (MySQL Connector, jbcrypt). |
| `visitorloginxammp.sql` | The complete MySQL schema and initial seed data. |
| `.gitignore` | Ensures all build artifacts, temporary files, and local scripts are excluded. |

## 🚀 How to Run Locally

1.  Clone this repository: `git clone https://github.com/markanth26/Java_Tesda_ProjectandLabs.git`
2.  Set up your local MySQL database using the `visitorloginxammp.sql` file.
3.  Open the project in NetBeans and run the main class (in `src/com/visitrack/main/RevisionMain.java`).

---
