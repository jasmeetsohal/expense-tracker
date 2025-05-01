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

## Swagger Documentation

### Local Development

1. Clone the repository:
```bash
git clone <repository-url>
cd expense-tracker
```

2. Build the project:
```bash
mvn clean package
```

3. Run the application:
```bash
mvn spring-boot:run
```

### Docker Deployment

1. Build and start the containers:
```bash
docker-compose up --build
```

2. The application will be available at:
- API: `http://localhost:8080/api`
- Swagger UI: `http://localhost:8080/api/swagger-ui.html`
- API Documentation: `http://localhost:8080/api/v3/api-docs`

### API Documentation

The API is documented using Swagger UI. You can access the interactive documentation at:
- Swagger UI: `http://localhost:8080/api/swagger-ui.html`

### Using Swagger UI

1. Open `http://localhost:8080/api/swagger-ui.html` in your browser
2. You'll see all API endpoints grouped by controllers
3. Click on any endpoint to expand its details
4. For endpoints requiring authentication:
   - Click the "Authorize" button at the top
   - Enter your JWT token in the format: `Bearer your-jwt-token`
   - Click "Authorize"

### Available Endpoints

- Authentication:
  - POST `/api/auth/register` - Register a new user
  - POST `/api/auth/login` - Login and get JWT token

- Expenses:
  - GET `/api/expenses` - Get all expenses
  - POST `/api/expenses` - Create a new expense
  - GET `/api/expenses/{id}` - Get expense by ID
  - PUT `/api/expenses/{id}` - Update expense
  - DELETE `/api/expenses/{id}` - Delete expense

## Database

The application uses PostgreSQL as the database. When running with Docker, a PostgreSQL container is automatically created and configured.

### Database Configuration

- Host: `localhost` (or `db` when using Docker)
- Port: `5432`
- Database: `expense_tracker`
- Username: `postgres`
- Password: `postgres`

## Environment Variables

The following environment variables can be configured:

- `SPRING_DATASOURCE_URL`: Database connection URL
- `SPRING_DATASOURCE_USERNAME`: Database username
- `SPRING_DATASOURCE_PASSWORD`: Database password
- `JWT_SECRET`: Secret key for JWT token generation
- `JWT_EXPIRATION`: JWT token expiration time in milliseconds

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.