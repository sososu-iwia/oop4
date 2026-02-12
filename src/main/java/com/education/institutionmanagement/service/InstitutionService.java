package com.education.institutionmanagement.service;

import com.education.institutionmanagement.entity.Institution;
import com.education.institutionmanagement.repository.InstitutionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InstitutionService {

    private final InstitutionRepository repository;

    public InstitutionService(InstitutionRepository repository) {
        this.repository = repository;
    }

    public List<Institution> findAll() {
        return repository.findAll();
    }

    public Optional<Institution> findById(Long id) {
        return repository.findById(id);
    }

    public Institution save(Institution institution) {
        return repository.save(institution);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
