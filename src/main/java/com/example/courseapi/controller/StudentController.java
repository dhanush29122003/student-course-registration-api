package com.example.courseapi.controller;

import com.example.courseapi.exception.ResourceNotFoundException;
import com.example.courseapi.model.Student;
import com.example.courseapi.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentRepository students;
    public StudentController(StudentRepository students) { this.students = students; }

    @GetMapping public List<Student> all() { return students.findAll(); }
    @GetMapping("/{id}") public Student get(@PathVariable Long id) {
        return students.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found: " + id));
    }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Student create(@Valid @RequestBody Student student) {
        if (students.existsByEmailIgnoreCase(student.getEmail())) throw new IllegalArgumentException("Email is already registered.");
        return students.save(student);
    }
    @PutMapping("/{id}") public Student update(@PathVariable Long id, @Valid @RequestBody Student input) {
        Student existing = get(id);
        if (!existing.getEmail().equalsIgnoreCase(input.getEmail()) && students.existsByEmailIgnoreCase(input.getEmail()))
            throw new IllegalArgumentException("Email is already registered.");
        existing.setFullName(input.getFullName()); existing.setEmail(input.getEmail());
        return students.save(existing);
    }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) {
        students.delete(get(id));
    }
}
