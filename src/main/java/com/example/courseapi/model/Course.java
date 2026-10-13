package com.example.courseapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "courses", uniqueConstraints = @UniqueConstraint(name = "uk_course_code", columnNames = "code"))
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max = 120)
    @Column(nullable = false, length = 120)
    private String title;

    @NotBlank @Size(max = 20)
    @Column(nullable = false, unique = true, length = 20)
    private String code;

    @Min(1)
    @Column(nullable = false)
    private int capacity;

    public Course() {}
    public Course(String title, String code, int capacity) { this.title = title; this.code = code; this.capacity = capacity; }
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
}
