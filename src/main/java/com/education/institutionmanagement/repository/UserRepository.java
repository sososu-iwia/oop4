package com.education.institutionmanagement.repository;

import com.education.institutionmanagement.entity.User;
import java.util.Optional;

public interface UserRepository {

    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    void deleteById(Long id);
}
