package com.inicions.tasks.domain.ports.out;

import com.inicions.tasks.domain.model.Role;

import java.util.Optional;

public interface RoleRepositoryPort {
    Optional<Role> findById(Long id);
}
