package com.education.institutionmanagement.service;

import com.education.institutionmanagement.entity.Teacher;
import com.education.institutionmanagement.repository.InstitutionRepository;
import com.education.institutionmanagement.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository repository;
    private final InstitutionRepository institutionRepository;

    public TeacherService(TeacherRepository repository, InstitutionRepository institutionRepository) {
        this.repository = repository;
        this.institutionRepository = institutionRepository;
    }

    public List<Teacher> findAll() {
        return repository.findAll();
    }

    public List<Teacher> findByInstitutionId(Long institutionId) {
        return repository.findByInstitutionId(institutionId);
    }

    public Optional<Teacher> findById(Long id) {
        return repository.findById(id);
    }

    public Teacher save(Teacher teacher) {
        return repository.save(teacher);
    }

    public Optional<Teacher> saveWithInstitution(Long institutionId, Teacher teacher) {
        return institutionRepository.findById(institutionId)
                .map(inst -> {
                    teacher.setInstitution(inst);
                    return repository.save(teacher);
                });
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
