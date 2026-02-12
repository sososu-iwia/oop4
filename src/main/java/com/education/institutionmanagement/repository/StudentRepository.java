package com.education.institutionmanagement.repository;

import com.education.institutionmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByInstitutionId(Long institutionId);
}
