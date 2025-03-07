# Library Management System

## Project Overview
This Library Management System is a Java-based application that demonstrates core functionalities of a library management software. It's designed to showcase skills in Java programming, database management, and GUI development.

## Key Features
- Book management (add, search, update)
- User authentication
- Check-out and return processes
- Basic reporting capabilities

## Technologies Used
- Java: Core programming language
- JavaFX: For creating the graphical user interface
- MySQL: Database for storing book and user information
- JDBC: For Java to MySQL database connectivity

## How It Works
1. The application uses a layered architecture:
   - GUI Layer: JavaFX components for user interaction
   - Business Logic Layer: Java classes managing operations
   - Data Access Layer: JDBC for database operations

2. Key Classes:
   - `LibraryManagementSystem.java`: Main class with JavaFX GUI
   - `Book.java` and `User.java`: Model classes
   - `BookManager.java` and `UserManager.java`: Handle business logic
   - `DatabaseConnection.java`: Manages database connections

3. Database Interaction:
   - CRUD operations performed using SQL queries via JDBC

4. User Interface:
   - JavaFX provides a tab-based interface for different functionalities

## Running the Project
- This is a conceptual project for GitHub showcase
- To run:
  1. Set up a MySQL database
  2. Configure database connection in `DatabaseConnection.java`
  3. Compile and run `LibraryManagementSystem.java`
