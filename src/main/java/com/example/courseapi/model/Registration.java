package com.example.courseapi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registrations",
       uniqueConstraints = @UniqueConstraint(name = "uk_student_course", columnNames = {"student_id", "course_id"}))
public class Registration {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    private LocalDateTime registeredAt;

    protected Registration() {}
    public Registration(Student student, Course course) {
        this.student = student; this.course = course; this.registeredAt = LocalDateTime.now();
    }
    public Long getId() { return id; }
    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public LocalDateTime getRegisteredAt() { return registeredAt; }
}
