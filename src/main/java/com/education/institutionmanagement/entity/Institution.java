package com.education.institutionmanagement.entity;

import jakarta.persistence.*;
import com.education.institutionmanagement.exception.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "institutions")
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL)
    private List<Teacher> teachers = new ArrayList<>();

    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<>();

    public Institution() {}

    public Institution(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Institution name cannot be empty");
        }
        this.name = name.trim();
    }

    public String getAddress() { return address; }
    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new ValidationException("Address cannot be empty");
        }
        this.address = address.trim();
    }

    public List<Teacher> getTeachers() { return teachers; }
    public List<Student> getStudents() { return students; }

    @Override
    public String toString() {
        return "Institution{id=" + id + ", name='" + name + "', address='" + address + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Institution that = (Institution) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
