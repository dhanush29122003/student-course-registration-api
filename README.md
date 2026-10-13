# Student Course Registration & Management API

A learning project for practicing Java, Spring Boot, REST APIs, Hibernate/JPA, MySQL, validation, and exception handling.

> Status: starter implementation generated for learning. Run it locally, test the endpoints, and verify each feature before describing it as completed on a public profile.

## Requirements
- Java 17+
- Maven 3.9+
- MySQL 8+

## 1. Create the database
You can let the configured JDBC URL create the `course_registration` database automatically if your MySQL user has permission. Otherwise run:
```sql
CREATE DATABASE course_registration;
```

## 2. Configure database credentials
Set environment variables before running:
- `DB_URL` (default: `jdbc:mysql://localhost:3306/course_registration?createDatabaseIfNotExist=true`)
- `DB_USERNAME` (default: `root`)
- `DB_PASSWORD` (default is a placeholder; set your real local password)

Windows PowerShell example:
```powershell
$env:DB_USERNAME="root"
$env:DB_PASSWORD="your-local-mysql-password"
mvn spring-boot:run
```

Do not commit passwords to GitHub. For a local setup, you can also edit `application.properties`, but keep real credentials out of version control.

## 3. Run
```bash
mvn spring-boot:run
```
The API listens on `http://localhost:8080`.

## Endpoints

### Students
- `GET /api/students` — list students
- `GET /api/students/{id}` — get one student
- `POST /api/students` — create student
- `PUT /api/students/{id}` — update student
- `DELETE /api/students/{id}` — delete student

Example `POST /api/students`:
```json
{
  "fullName": "Asha Rao",
  "email": "asha@example.com"
}
```

### Courses
- `GET /api/courses` — list courses
- `GET /api/courses/{id}` — get one course
- `POST /api/courses` — create course
- `PUT /api/courses/{id}` — update course
- `DELETE /api/courses/{id}` — delete course

Example `POST /api/courses`:
```json
{
  "title": "Java Backend Development",
  "code": "JAVA-101",
  "capacity": 30
}
```

### Registrations
- `GET /api/registrations` — list registrations
- `POST /api/registrations?studentId=1&courseId=1` — register a student
- `DELETE /api/registrations/{id}` — cancel a registration

The API checks that the student and course exist, blocks duplicate student-course registrations, and checks course capacity before saving.

## Quick Postman walkthrough
1. Create a student with `POST /api/students`.
2. Create a course with `POST /api/courses`.
3. Register using `POST /api/registrations?studentId=1&courseId=1`.
4. Try the same registration again; expect a `400 Bad Request`.
5. Try an unknown student or course ID; expect a `404 Not Found`.
6. Try invalid email or missing fields; expect a `400 Bad Request`.

## Current scope and next improvements
Included in the starter implementation:
- CRUD for students and courses
- JPA entity relationships and MySQL persistence
- request validation
- duplicate email/course-code and duplicate registration checks
- course-capacity check
- centralized API error responses

Good next steps:
- add DTOs so entities are not exposed directly
- add pagination and sorting
- add service layer and transactional/concurrency-safe capacity enforcement
- add integration tests using Testcontainers or a test database
- add OpenAPI/Swagger documentation
- add authentication/authorization if needed

This project currently has no frontend, login system, JWT, or production deployment. Do not claim those features until implemented and tested.
