package com.education.institutionmanagement.service;

import com.education.institutionmanagement.datapool.DataPoolService;
import com.education.institutionmanagement.entity.Student;
import com.education.institutionmanagement.exception.EntityNotFoundException;
import com.education.institutionmanagement.repository.InstitutionRepository;
import com.education.institutionmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    private final InstitutionRepository institutionRepository;
    private final DataPoolService<Student> dataPoolService;

    public StudentServiceImpl(StudentRepository repository,
                              InstitutionRepository institutionRepository,
                              DataPoolService<Student> dataPoolService) {
        this.repository = repository;
        this.institutionRepository = institutionRepository;
        this.dataPoolService = dataPoolService;
    }

    @Override
    public List<Student> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Student> findByInstitutionId(Long institutionId) {
        return repository.findByInstitutionId(institutionId);
    }

    @Override
    public Optional<Student> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Student save(Student student) {
        return repository.save(student);
    }

    @Override
    public Optional<Student> saveWithInstitution(Long institutionId, Student student) {
        return institutionRepository.findById(institutionId)
                .map(inst -> {
                    student.setInstitution(inst);
                    return repository.save(student);
                });
    }

    @Override
    public void deleteById(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Student not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public List<Student> searchByName(String nameQuery) {
        List<Student> all = repository.findAll();
        return dataPoolService.search(all, s -> s.getName().toLowerCase().contains(nameQuery.toLowerCase()));
    }

    @Override
    public List<Student> findAllSorted(String sortBy, String order) {
        List<Student> all = repository.findAll();
        boolean ascending = !"desc".equalsIgnoreCase(order);
        Comparator<Student> cmp = "grade".equalsIgnoreCase(sortBy)
                ? Comparator.comparing(Student::getGrade, Comparator.nullsLast(Integer::compareTo))
                : Comparator.comparing(Student::getName, String.CASE_INSENSITIVE_ORDER);
        if (!ascending) cmp = cmp.reversed();
        return dataPoolService.sort(all, cmp);
    }
}
