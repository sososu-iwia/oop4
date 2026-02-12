package com.education.institutionmanagement.service;

import com.education.institutionmanagement.entity.Student;
import com.education.institutionmanagement.entity.Teacher;

/**
 * Factory pattern: creates Student and Teacher.
 * Default + static interface methods (Java language features).
 */
public interface PersonFactory {

    /** Default interface method (Java 8+). */
    default void logCreation(String type) {
        System.out.println("Creating new " + type);
    }

    /** Static interface method (Java 8+). */
    static String getFactoryType() {
        return "PersonFactory";
    }

    Student createStudent(String name, Integer grade);

    Teacher createTeacher(String name, String subject);
}
