package com.education.institutionmanagement.service;

import com.education.institutionmanagement.entity.Student;
import com.education.institutionmanagement.entity.Teacher;
import org.springframework.stereotype.Service;

@Service
public class PersonFactoryImpl implements PersonFactory {

    @Override
    public Student createStudent(String name, Integer grade) {
        logCreation("Student");
        return new Student.Builder()
                .name(name)
                .grade(grade)
                .institution(null)
                .build();
    }

    @Override
    public Teacher createTeacher(String name, String subject) {
        logCreation("Teacher");
        return new Teacher(name, subject, null);
    }
}
