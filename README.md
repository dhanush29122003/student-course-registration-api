# Student Course Registration & Management API

A Java backend learning project built with Spring Boot, REST APIs, Spring Data JPA, Hibernate, and MySQL.

The API supports student and course management, course registration, duplicate-enrollment prevention, and course-capacity validation.

## Technologies

- Java 17+
- Spring Boot
- Maven
- Spring Data JPA / Hibernate
- MySQL 8+
- REST APIs
- PowerShell for local API testing

## Requirements

- Java 17 or newer
- Maven
- MySQL Server 8 or newer

## Setup

### 1. Create the database

Create the database in MySQL:

```sql
CREATE DATABASE IF NOT EXISTS course_registration;
```

### 2. Configure database credentials

The application reads its database settings from environment variables:

- `DB_URL` — defaults to the local `course_registration` database.
- `DB_USERNAME` — defaults to `root`.
- `DB_PASSWORD` — must be set to your local MySQL password.

In Windows PowerShell, set the variables before starting the application:

```powershell
$env:DB_USERNAME = "root"
$env:DB_PASSWORD = Read-Host "Enter your MySQL root password"
mvn spring-boot:run
```

Do not commit real passwords or other secrets to source control.

### 3. Run the application

From the folder containing `pom.xml`, run:

```powershell
mvn spring-boot:run
```

The API runs at `http://localhost:8080`.

## API Endpoints

### Students

| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/api/students` | List students |
| GET | `/api/students/{id}` | Get one student |
| POST | `/api/students` | Create a student |
| PUT | `/api/students/{id}` | Update a student |
| DELETE | `/api/students/{id}` | Delete a student |

Example request body:

```json
{
  "fullName": "Asha Rao",
  "email": "asha@example.com"
}
```

### Courses

| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/api/courses` | List courses |
| GET | `/api/courses/{id}` | Get one course |
| POST | `/api/courses` | Create a course |
| PUT | `/api/courses/{id}` | Update a course |
| DELETE | `/api/courses/{id}` | Delete a course |

Example request body:

```json
{
  "title": "Java Backend Development",
  "code": "JAVA-101",
  "capacity": 30
}
```

### Registrations

| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/api/registrations` | List registrations |
| POST | `/api/registrations?studentId=1&courseId=1` | Register a student |
| DELETE | `/api/registrations/{id}` | Cancel a registration |

Use the actual student and course IDs returned by the API.

## Business Rules Tested

- Duplicate course codes are rejected.
- A student cannot register for the same course twice.
- Registrations are rejected when a course reaches its capacity.
- Student, course, and registration data are persisted in MySQL.

## Future Improvements

- Add automated integration tests.
- Introduce DTOs and a service layer.
- Add pagination and sorting.
- Improve transaction and concurrency handling for course capacity.
- Add Swagger/OpenAPI documentation.
- Add authentication and authorization if required.

## Project Scope

This project currently focuses on backend REST API development. A frontend, authentication system, JWT security, and production deployment are not included in the current implementation.
