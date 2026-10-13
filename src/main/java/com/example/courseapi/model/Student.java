package com.example.courseapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "students", uniqueConstraints = @UniqueConstraint(name = "uk_student_email", columnNames = "email"))
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String fullName;

    @NotBlank @Email @Size(max = 150)
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    public Student() {}
    public Student(String fullName, String email) { this.fullName = fullName; this.email = email; }
    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
