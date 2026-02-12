package com.education.institutionmanagement.repository;

import com.education.institutionmanagement.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findByInstitutionId(Long institutionId);
}
