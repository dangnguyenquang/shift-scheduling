# Restaurant Staff Management System

## Overview

This project implements a comprehensive system for managing restaurant staff, including shift scheduling and salary calculation. The system provides tools to efficiently:

- Manage employee information
- Automatically assign shifts based on employee roles and shift types (morning, afternoon, evening)
- Calculate salaries based on number of shifts, night shift bonuses, buffet allowances for chefs, and event allowances for receptionists and servers

## Technologies Used

- **Java 21** – Programming language
- **Spring Boot** – Application framework for building RESTful services
- **Hibernate (JPA)** – ORM framework for data persistence
- **MySQL** – Relational database for storing structured data
- **JWT (JSON Web Token)** – Authentication and role-based authorization

## Key Features

### Employee Management

Store and update essential employee details including roles, availability, and salary configurations.

### Automated Shift Scheduling

Dynamically assign suitable shifts to employees based on role-specific constraints and shift availability.

### Salary Calculation

Compute monthly salaries factoring in:

- Number of shifts worked
- Night shift allowances
- Buffet preparation bonuses (for chefs)
- Event handling bonuses (for receptionists and servers)

### Role-Based Access Control (RBAC)

Ensure secure access using JWT, allowing only authenticated users to perform actions according to their roles (e.g., Admin, Manager, Staff).

### Design Pattern Integration

- **Singleton**: Used for managing system-wide configurations and resources  
- **Strategy**: Employed for flexible salary calculation and shift assignment logic

## Project Objectives

- Build a reliable and scalable backend system to support human resource operations in restaurant environments.
- Automate error-prone tasks such as manual shift assignment and salary computation.
- Ensure a modular and maintainable codebase through best practices and design patterns.

## Getting Started

### Prerequisites

- JDK 21
- Maven 3.9+
- MySQL Server 8+
- Git

### Setup Instructions

1. **Clone the repository**

   ```
   git clone https://github.com/your-username/restaurant-staff-management.git
   cd restaurant-staff-management
   ```

2. **Configure the database**
   Create a MySQL database and update the application.properties file:
   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/restaurant_db
   spring.datasource.username=your_db_user
   spring.datasource.password=your_db_password
   ```
3. **Build and run the application**
   Run with Maven:
   ```
   ./mvnw spring-boot:run
   ```
4. **Configure the database**
   If Swagger is integrated, access API docs at:
   ```
   http://localhost:8080/swagger-ui.html
   ```
   
### Future Improvements

- Add unit and integration tests
- Develop a frontend interface (e.g., using React or Angular)
- Integrate advanced scheduling algorithms for optimal resource planning
- Support employee leave requests and conflict resolution
