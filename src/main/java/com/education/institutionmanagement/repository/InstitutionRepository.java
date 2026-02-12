package com.education.institutionmanagement.repository;

import com.education.institutionmanagement.entity.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {
}
