package com.education.institutionmanagement.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.education.institutionmanagement.exception.ValidationException;

import java.util.Objects;

@Entity
@Table(name = "teachers")
public class Teacher implements Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String subject;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "institution_id", nullable = false)
    @JsonIgnoreProperties({"teachers", "students"})
    private Institution institution;

    public Teacher() {}

    public Teacher(String name, String subject, Institution institution) {
        this.name = name;
        this.subject = subject;
        this.institution = institution;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Teacher name cannot be empty");
        }
        this.name = name.trim();
    }

    public String getSubject() { return subject; }
    public void setSubject(String subject) {
        if (subject == null || subject.trim().isEmpty()) {
            throw new ValidationException("Subject cannot be empty");
        }
        this.subject = subject.trim();
    }

    public Institution getInstitution() { return institution; }
    public void setInstitution(Institution institution) { this.institution = institution; }

    @Override
    public String toString() {
        return "Teacher{id=" + id + ", name='" + name + "', subject='" + subject +
                "', institutionId=" + (institution != null ? institution.getId() : null) + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(id, teacher.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
