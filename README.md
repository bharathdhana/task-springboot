# Task Management REST API

A Spring Boot backend application for managing users and tasks with JWT-based authentication, full CRUD operations, input validation, pagination, global exception handling, and interactive API documentation via Swagger UI.

---

## 📌 Overview

This project demonstrates a production-ready REST API built with Spring Boot, covering:

- **JWT Authentication** — Stateless authentication using JSON Web Tokens
- **Spring Security** — Secured endpoints with role-based access control
- **REST API Design** — Clean, resource-oriented endpoints following REST conventions
- **CRUD Operations** — Full Create, Read, Update, Delete for Users and Tasks
- **DTO Pattern** — Data Transfer Objects to decouple API layer from entity model
- **Bean Validation** — Request validation using Jakarta Validation API
- **Global Exception Handling** — Centralized error responses via `@ControllerAdvice`
- **Pagination** — Paginated task listing support
- **JPA / Hibernate** — ORM-based persistence with PostgreSQL
- **Swagger / OpenAPI** — Auto-generated, interactive API documentation

---

## 🛠️ Tech Stack

| Layer          | Technology                                      |
|----------------|-------------------------------------------------|
| Framework      | Spring Boot 4.0.5                               |
| Language       | Java 25                                         |
| Security       | Spring Security + JWT (JJWT 0.13.0)             |
| ORM            | Spring Data JPA + Hibernate                     |
| Database       | PostgreSQL (primary), MySQL (commented config)  |
| Validation     | Jakarta Validation API                          |
| Documentation  | SpringDoc OpenAPI (Swagger UI) 3.0.2            |
| Build Tool     | Maven                                           |
| Dev Tools      | Spring Boot DevTools                            |
| Testing        | Spring Security Test, Spring Data JPA Test      |

---

## 📁 Project Structure

```
src/main/java/com/bharath/task/
├── TaskApplication.java          # Main entry point
├── config/
│   └── SecurityConfig.java       # Spring Security & JWT filter configuration
├── controller/
│   ├── AuthController.java       # Login & Register endpoints
│   ├── UserController.java       # User CRUD endpoints
│   └── TaskController.java       # Task CRUD + Pagination endpoints
├── dto/
│   ├── UserDTO.java              # User Data Transfer Object
│   └── TaskDTO.java              # Task Data Transfer Object
├── entity/
│   ├── User.java                 # User JPA entity
│   └── Task.java                 # Task JPA entity
├── exception/
│   ├── ResourceNotFoundException.java   # Custom 404 exception
│   └── GlobalExceptionHandler.java      # Centralized exception handler
├── repository/
│   └── (UserRepository, TaskRepository) # Spring Data JPA repositories
├── service/
│   └── (UserService, TaskService)       # Business logic layer
└── util/
    ├── JwtUtil.java              # JWT generation & validation
    └── JwtFilter.java            # JWT request filter
```

---

## 🗄️ Database Configuration

The application is configured to use **PostgreSQL** by default.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/todo
spring.datasource.username=postgres
spring.datasource.password=<your-password>
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> MySQL configuration is available as commented-out properties in `application.properties` for easy switching.

---

## 🔐 Authentication

Authentication uses **JWT (JSON Web Tokens)**.

1. **Register** a user via `POST /auth/register`
2. **Login** via `POST /auth/login` to receive a JWT token
3. Include the token in the `Authorization` header for protected routes:
   ```
   Authorization: Bearer <token>
   ```

---

## 📡 REST API Reference

### Auth APIs

| Method | Endpoint          | Description        | Auth Required |
|--------|-------------------|--------------------|---------------|
| POST   | `/auth/register`  | Register a user    | ❌            |
| POST   | `/auth/login`     | Login, get JWT     | ❌            |

**Register Request Body:**
```json
{
  "email": "user@example.com",
  "password": "yourpassword"
}
```

**Login Response:**
```json
{
  "token": "<jwt-token>"
}
```

---

### User APIs

| Method | Endpoint          | Description        | Auth Required |
|--------|-------------------|--------------------|---------------|
| GET    | `/api/users`      | Get all users      | ✅            |
| GET    | `/api/users/{id}` | Get user by ID     | ✅            |
| POST   | `/api/users`      | Create a user      | ✅            |
| PUT    | `/api/users/{id}` | Update a user      | ✅            |
| DELETE | `/api/users/{id}` | Delete a user      | ✅            |

**User Request Body:**
```json
{
  "name": "Bharath",
  "email": "bharath@example.com"
}
```

---

### Task APIs

| Method | Endpoint              | Description               | Auth Required |
|--------|-----------------------|---------------------------|---------------|
| GET    | `/api/tasks`          | Get all tasks             | ✅            |
| GET    | `/api/tasks/{id}`     | Get task by ID            | ✅            |
| GET    | `/api/tasks/page`     | Get paginated tasks       | ✅            |
| POST   | `/api/tasks`          | Create a task             | ✅            |
| PUT    | `/api/tasks/{id}`     | Update a task             | ✅            |
| DELETE | `/api/tasks/{id}`     | Delete a task             | ✅            |

**Task Request Body:**
```json
{
  "title": "Complete project",
  "description": "Finish the Spring Boot REST API",
  "status": "IN_PROGRESS"
}
```

**Pagination Query Params:**
```
GET /api/tasks/page?page=0&size=10
```

---

## 📖 API Documentation (Swagger UI)

Once the application is running, access the interactive Swagger UI at:

```
http://localhost:8080/swagger-ui/index.html
```

The OpenAPI specification is available at:

```
http://localhost:8080/v3/api-docs
```

---

## 🚀 Getting Started

### Prerequisites

- Java 25+
- Maven 3.6+
- PostgreSQL running on `localhost:5432`
- A database named `todo` created in PostgreSQL

### Run the Application

```bash
./mvnw spring-boot:run
```

Or on Windows:

```cmd
mvnw.cmd spring-boot:run
```

### Build

```bash
./mvnw clean package
```

---

## 📦 Key Dependencies

| Dependency                          | Purpose                          |
|-------------------------------------|----------------------------------|
| `spring-boot-starter-web`           | REST API support                 |
| `spring-boot-starter-data-jpa`      | JPA / Hibernate ORM              |
| `spring-boot-starter-security`      | Spring Security                  |
| `spring-boot-devtools`              | Hot reload during development    |
| `postgresql`                        | PostgreSQL JDBC driver           |
| `jakarta.validation-api`            | Bean validation                  |
| `jjwt-api`, `jjwt-impl`, `jjwt-jackson` | JWT token generation & parsing |
| `springdoc-openapi-starter-webmvc-ui` | Swagger UI / OpenAPI docs      |
| `spring-security-test`              | Security integration tests       |
