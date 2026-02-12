package com.education.institutionmanagement.controller;

import com.education.institutionmanagement.dto.StudentRequest;
import com.education.institutionmanagement.entity.Institution;
import com.education.institutionmanagement.entity.Student;
import com.education.institutionmanagement.repository.InstitutionRepository;
import com.education.institutionmanagement.service.PersonFactory;
import com.education.institutionmanagement.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentService service;
    private final PersonFactory personFactory;
    private final InstitutionRepository institutionRepository;

    public StudentController(StudentService service, PersonFactory personFactory, InstitutionRepository institutionRepository) {
        this.service = service;
        this.personFactory = personFactory;
        this.institutionRepository = institutionRepository;
    }

    @GetMapping
    public List<Student> getAll(@RequestParam(required = false) Long institutionId,
                                @RequestParam(required = false) String search,
                                @RequestParam(required = false) String sortBy,
                                @RequestParam(required = false, defaultValue = "asc") String order) {
        if (search != null && !search.trim().isEmpty()) {
            return service.searchByName(search);
        }
        if (institutionId != null) {
            return service.findByInstitutionId(institutionId);
        }
        if (sortBy != null && !sortBy.trim().isEmpty()) {
            return service.findAllSorted(sortBy, order);
        }
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody StudentRequest request) {
        // Factory + Builder: PersonFactory creates Student via Builder
        Student student = personFactory.createStudent(request.getName(), request.getGrade());
        return institutionRepository.findById(request.getInstitutionId())
                .map(inst -> {
                    student.setInstitution(inst);
                    return ResponseEntity.ok(service.save(student));
                })
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody StudentRequest request) {
        return service.findById(id)
                .map(existing -> {
                    existing.setName(request.getName());
                    existing.setGrade(request.getGrade());
                    if (request.getInstitutionId() != null) {
                        return service.saveWithInstitution(request.getInstitutionId(), existing)
                                .map(ResponseEntity::ok)
                                .orElse(ResponseEntity.badRequest().build());
                    }
                    return ResponseEntity.ok(service.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
