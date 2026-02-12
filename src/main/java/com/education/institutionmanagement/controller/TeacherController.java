package com.education.institutionmanagement.controller;

import com.education.institutionmanagement.dto.TeacherRequest;
import com.education.institutionmanagement.entity.Teacher;
import com.education.institutionmanagement.repository.InstitutionRepository;
import com.education.institutionmanagement.service.PersonFactory;
import com.education.institutionmanagement.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@CrossOrigin(origins = "*")
public class TeacherController {

    private final TeacherService service;
    private final PersonFactory personFactory;
    private final InstitutionRepository institutionRepository;

    public TeacherController(TeacherService service, PersonFactory personFactory, InstitutionRepository institutionRepository) {
        this.service = service;
        this.personFactory = personFactory;
        this.institutionRepository = institutionRepository;
    }

    @GetMapping
    public List<Teacher> getAll(@RequestParam(required = false) Long institutionId) {
        if (institutionId != null) {
            return service.findByInstitutionId(institutionId);
        }
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TeacherRequest request) {
        Teacher teacher = personFactory.createTeacher(request.getName(), request.getSubject());
        return service.saveWithInstitution(request.getInstitutionId(), teacher)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TeacherRequest request) {
        return service.findById(id)
                .map(existing -> {
                    existing.setName(request.getName());
                    existing.setSubject(request.getSubject());
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
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
