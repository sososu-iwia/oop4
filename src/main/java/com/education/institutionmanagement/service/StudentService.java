package com.education.institutionmanagement.service;

import com.education.institutionmanagement.entity.Student;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAll();
    List<Student> findByInstitutionId(Long institutionId);
    Optional<Student> findById(Long id);
    Student save(Student student);
    Optional<Student> saveWithInstitution(Long institutionId, Student student);
    void deleteById(Long id);
    /** Search students by name using in-memory DataPool. */
    List<Student> searchByName(String nameQuery);
    /** Sort students (DataPool): sortBy = name|grade, order = asc|desc. */
    List<Student> findAllSorted(String sortBy, String order);
}
