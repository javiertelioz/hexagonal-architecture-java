package com.inicions.tasks.domain.ports.out;

import com.inicions.tasks.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    User save(User user);
    Optional<User> findById(String id);
    List<User> findAll();
    Optional<User> update(User task);
    boolean deleteById(String id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail( String email);
}
