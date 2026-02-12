package com.education.institutionmanagement.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.education.institutionmanagement.exception.ValidationException;

import java.util.Objects;

@Entity
@Table(name = "students")
public class Student implements Person {

    private static final int MIN_GRADE = 1;
    private static final int MAX_GRADE = 12;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer grade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "institution_id", nullable = false)
    @JsonIgnoreProperties({ "teachers", "students" })
    private Institution institution;

    public Student() {
    }

    public Student(String name, Integer grade, Institution institution) {
        setName(name);
        setGrade(grade);
        this.institution = institution;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Student name cannot be empty");
        }
        this.name = name.trim();
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        if (grade == null || grade < MIN_GRADE || grade > MAX_GRADE) {
            throw new ValidationException("Grade must be between " + MIN_GRADE + " and " + MAX_GRADE);
        }
        this.grade = grade;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', grade=" + grade +
                ", institutionId=" + (institution != null ? institution.getId() : null) + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class Builder {
        private String name;
        private Integer grade;
        private Institution institution;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder grade(Integer grade) {
            this.grade = grade;
            return this;
        }

        public Builder institution(Institution institution) {
            this.institution = institution;
            return this;
        }

        public Student build() {
            return new Student(name, grade, institution);
        }
    }
}
