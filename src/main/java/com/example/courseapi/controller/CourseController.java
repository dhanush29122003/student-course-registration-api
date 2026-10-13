package com.example.courseapi.controller;

import com.example.courseapi.exception.ResourceNotFoundException;
import com.example.courseapi.model.Course;
import com.example.courseapi.repository.CourseRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseRepository courses;
    public CourseController(CourseRepository courses) { this.courses = courses; }

    @GetMapping public List<Course> all() { return courses.findAll(); }
    @GetMapping("/{id}") public Course get(@PathVariable Long id) {
        return courses.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found: " + id));
    }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Course create(@Valid @RequestBody Course course) {
        if (courses.existsByCodeIgnoreCase(course.getCode())) throw new IllegalArgumentException("Course code already exists.");
        return courses.save(course);
    }
    @PutMapping("/{id}") public Course update(@PathVariable Long id, @Valid @RequestBody Course input) {
        Course existing = get(id);
        if (!existing.getCode().equalsIgnoreCase(input.getCode()) && courses.existsByCodeIgnoreCase(input.getCode()))
            throw new IllegalArgumentException("Course code already exists.");
        existing.setTitle(input.getTitle()); existing.setCode(input.getCode()); existing.setCapacity(input.getCapacity());
        return courses.save(existing);
    }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) {
        courses.delete(get(id));
    }
}
