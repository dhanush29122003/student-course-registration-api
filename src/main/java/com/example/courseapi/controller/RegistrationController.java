package com.example.courseapi.controller;

import com.example.courseapi.exception.ResourceNotFoundException;
import com.example.courseapi.model.Course;
import com.example.courseapi.model.Registration;
import com.example.courseapi.model.Student;
import com.example.courseapi.repository.CourseRepository;
import com.example.courseapi.repository.RegistrationRepository;
import com.example.courseapi.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {
    private final RegistrationRepository registrations;
    private final StudentRepository students;
    private final CourseRepository courses;

    public RegistrationController(RegistrationRepository registrations, StudentRepository students, CourseRepository courses) {
        this.registrations = registrations; this.students = students; this.courses = courses;
    }

    @GetMapping public List<Registration> all() { return registrations.findAll(); }

    @PostMapping @ResponseStatus(HttpStatus.CREATED) @Transactional
    public Registration register(@RequestParam Long studentId, @RequestParam Long courseId) {
        Student student = students.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found: " + studentId));
        Course course = courses.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found: " + courseId));
        if (registrations.existsByStudentIdAndCourseId(studentId, courseId))
            throw new IllegalArgumentException("Student is already registered for this course.");
        if (registrations.countByCourseId(courseId) >= course.getCapacity())
            throw new IllegalArgumentException("Course capacity has been reached.");
        return registrations.save(new Registration(student, course));
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable Long id) {
        Registration registration = registrations.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found: " + id));
        registrations.delete(registration);
    }
}
