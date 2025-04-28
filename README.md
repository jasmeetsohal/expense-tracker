# Expense Tracker API

A Spring Boot-based REST API for tracking and managing personal expenses with user authentication, expense management, and reporting capabilities.

## Features

### Core Features
- **User Management**
  - User registration and login
  - Secure authentication using JWT
  - Role-based access control

- **Expense Management**
  - Create, read, update, and delete expenses
  - Categorize expenses (Food, Travel, Entertainment, etc.)
  - Track expense details including amount, description, and date

- **Reporting**
  - Monthly expense reports
  - Category-wise expense breakdown
  - Total expenses calculation within date ranges

### Technical Features
- Spring Boot REST API
- JWT-based authentication
- MySQL/PostgreSQL/H2 database support
- Spring Data JPA for data persistence
- Global exception handling
- Comprehensive unit testing
- Request logging
- Pagination support
- Input validation

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - User login

### Expenses
- `GET /api/expenses` - Get all expenses (with pagination)
- `POST /api/expenses` - Create a new expense
- `GET /api/expenses/{id}` - Get expense by ID
- `PUT /api/expenses/{id}` - Update expense
- `DELETE /api/expenses/{id}` - Delete expense

### Reports
- `GET /api/reports/monthly` - Generate monthly expense report
- `GET /api/reports/categories` - Get category-wise expense breakdown

## Data Models

### User
- id (auto-generated)
- name
- email
- active

### Expense
- id (auto-generated)
- amount (double, required)
- description (String, optional)
- category (String, required)
- date (LocalDate, default: current date)
- userId

## Security
- JWT-based authentication
- BCrypt password hashing
- Role-based access control
- User-specific data access restrictions

## Prerequisites
- Java 11 or higher
- Maven
- MySQL/PostgreSQL/H2 database

## Getting Started

1. Clone the repository
2. Configure database properties in `application.properties`
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## Testing
- Unit tests implemented using JUnit and Mockito
- Run tests using:
  ```bash
  mvn test
  ```

## License
This project is private and confidential.

## Collaborators
- vinay.nittali@xalts.io
- vineet.gupta@xalts.io
- sharuk.shaik@xalts.io