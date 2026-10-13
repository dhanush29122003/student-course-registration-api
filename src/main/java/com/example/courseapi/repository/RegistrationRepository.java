package com.example.courseapi.repository;
import com.example.courseapi.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);
    long countByCourseId(Long courseId);
}
